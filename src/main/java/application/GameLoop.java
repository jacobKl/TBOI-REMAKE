package application;

import GUI.GUI;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import entities.Entity;
import entities.Player.Player;
import entities.Projectile;
import entities.Room.Enemies.Enemy;
import entities.Room.Enemies.EnemyFactory;
import entities.Room.Obstacles.Door;
import entities.Room.Obstacles.ObstacleFactory;
import entities.Room.Room;
import javafx.animation.AnimationTimer;
import javafx.animation.FadeTransition;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

public class GameLoop extends AnimationTimer {
    private final GraphicsContext graphicsContext;
    private final Scene scene;
    private long lastTime = 0;
    private Player player;
    private Room currentRoom;
    private GUI gui;
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    private ArrayList<Entity> entities = new ArrayList<>();
    private JsonArray roomsArray;
    private boolean playerTouchesDoors = false;
    private StackPane root;
    private RoomLoader roomLoader;

    public GameLoop(GraphicsContext graphicsContext, Scene scene, StackPane root) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;
        this.root = root;
        this.roomLoader = new RoomLoader();

        this.player = new Player(550, 350, this.projectiles);
        this.currentRoom = new Room(this.entities);
        this.gui = new GUI();

        this.init();
    }

    private void init() {
        this.scene.setOnKeyPressed(key -> this.player.handleInput(key, true));
        this.scene.setOnKeyReleased(key -> this.player.handleInput(key, false));
        this.entities = this.roomLoader.loadRoomEntities(0);
    }

    @Override
    public void handle(long now) {
        if (this.lastTime == 0) {
            this.lastTime = now;
            return;
        }
        double deltaTime = (now - this.lastTime) / 1_000_000_000.0;
        this.update(deltaTime);

        this.render(deltaTime);
        this.lastTime = now;
    }

    private void update(double deltaTime) {
        this.player.update(this.entities, deltaTime);
        this.checkIfPlayerIsTouchingDoors();

        for (Entity entity : this.entities) {
            entity.update(deltaTime, this.player, this.entities);
        }

        for (Projectile projectile : this.projectiles) {
            projectile.update(deltaTime);

            for (Entity entity : this.entities) {
                if (entity instanceof Enemy && projectile.intersects(entity)) {
                    this.projectiles.remove(projectile);
                    boolean killed = ((Enemy) entity).receiveDamage(this.player.getPlayerAttributes().getDamage());

                    if (killed) {
                        this.entities.remove(entity);
                    }
                }
            }
        }
    }

    private void render(double deltaTime) {
        this.currentRoom.render(this.graphicsContext, deltaTime);
        this.gui.render(this.graphicsContext, deltaTime, this.player);

        for (Entity entity : this.entities) {
            entity.render(this.graphicsContext, deltaTime);
        }

        for (Projectile projectile : this.projectiles) {
            if (Room.isWithinBounds(projectile))
                projectile.render(this.graphicsContext);
            else {
                (AudioManager.getInstance()).playSound("splatter.mp3");
                this.projectiles.remove(projectile);
            }
        }

        this.player.render(this.graphicsContext, deltaTime);
    }


    private void checkIfPlayerIsTouchingDoors() {
        for (Entity entity : this.entities) {
            if (entity instanceof Door) {
                if (this.player.intersects(entity)) {
                    if (!this.playerTouchesDoors) {
                        this.switchToRoom((Door) entity);
                    }
                    this.playerTouchesDoors = true;
                    break;
                } else {
                    this.playerTouchesDoors = false;
                }
            }
        }
    }

    private void switchToRoom(Door entity) {
        if (entity.isClosed()) return;

        FadeTransition fadeOut = new FadeTransition(Duration.seconds(.3), root);
        fadeOut.setFromValue(1.0);
        fadeOut.setToValue(0.0);

        fadeOut.setOnFinished(event -> {
            this.entities = this.roomLoader.loadRoomEntities(((Door) entity).getToRoomId());
            Vector2D walkOutPosition = entity.getWalkOutPosition();
            this.player.setX(walkOutPosition.getX());
            this.player.setY(walkOutPosition.getY());

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(.3), root);
            fadeIn.setFromValue(0.0);
            fadeIn.setToValue(1.0);
            fadeIn.play();
        });

        fadeOut.play();
    }
}
