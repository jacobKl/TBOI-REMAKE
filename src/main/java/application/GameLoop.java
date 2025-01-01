package application;

import GUI.GUI;
import entities.Entity;
import entities.Player.Player;
import entities.Projectile;
import entities.Room.Enemies.Enemy;
import entities.Room.Room;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
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


    public GameLoop(GraphicsContext graphicsContext, Scene scene) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;

        this.player = new Player(200, 200, this.projectiles);
        this.currentRoom = new Room(this.player, this.entities);
        this.gui = new GUI();

        this.init();
    }

    private void init() {
        this.scene.setOnKeyPressed(key -> this.player.handleInput(key, true));
        this.scene.setOnKeyReleased(key -> this.player.handleInput(key, false));
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

        for (Entity entity : this.entities) {
            entity.update(deltaTime, this.player);
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
        this.player.render(this.graphicsContext, deltaTime);
        this.gui.render(this.graphicsContext, deltaTime, this.player);

        for (Projectile projectile : this.projectiles) {
            if (Room.isWithinBounds(projectile))
                projectile.render(this.graphicsContext);
            else
                this.projectiles.remove(projectile);
        }
    }
}
