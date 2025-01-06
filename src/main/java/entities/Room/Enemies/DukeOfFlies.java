package entities.Room.Enemies;

import Utils.Clock;
import Utils.OscillatingValue;
import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Enemies.Enemy;
import entities.Room.Enemies.EnemyFactory;
import entities.Room.Enemies.Fly;
import entities.Room.Room;
import entities.Room.Textures.FlyTexture;
import entities.Room.Textures.Texture;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DukeOfFlies extends Enemy {
    private Image spritesheet;
    private Vector2D direction;
    private double speed = 2;
    private boolean coughing = false;
    private Clock clock = new Clock(0.25);
    private OscillatingValue value = new OscillatingValue(0, 1, -0.5, 0.5);
    private double fliesCountdown = 1;
    private Integer column = 0;
    private Image bossBar = new Image(getClass().getResource("/boss_bar.png").toExternalForm());
    private Texture fly = new FlyTexture();

    public DukeOfFlies(double startX, double startY, Image spritesheet) {
        super(startX, startY, 200, 200, spritesheet, 300);

        this.collidable = false;
        this.spritesheet = spritesheet;

        this.direction = this.getRandomDirection();
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spritesheet, this.column * 80, 0, 80, 64, this.getX(), this.getY() + this.value.update() * 10, this.getWidth(), this.getHeight());

        gc.setFill(Color.RED);
        gc.fillRect(450, 70, this.getHealth() / 300 * 348, 26);
        gc.drawImage(this.bossBar, 600 - 200, 50, 400, 56);
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        this.followDirection(player);
        this.fliesCountdown -= deltaTime;

        if (this.fliesCountdown < 0) {
            this.coughing = true;
            this.audioManager.playSound("cough.mp3");
            this.fliesCountdown = Math.random() * 5 + 5;
        }

        if (this.coughing) {
            if (this.clock.update(deltaTime)) {
                this.column += 1;

                if (this.column >= 3) {
                    this.column = 0;
                    this.coughing = false;
                    this.spawnFlies(entities);
                }
            }
        }
    }

    private void followDirection(Player player) {
        Entity futurePosition = new Entity(this.getX() + this.direction.getX() * this.speed, this.getY() + this.direction.getY() * this.speed, this.width, this.height);

        if (!Room.isWithinBounds(futurePosition) || this.intersects(player)) {
            this.direction = this.getRandomDirection();
        } else {
            this.setX(this.getX() + this.direction.getX() * this.speed);
            this.setY(this.getY() + this.direction.getY() * this.speed);
        }
    }

    private Vector2D getRandomDirection() {
        return new Vector2D(Math.random() > 0.495 ? 1 : -1, Math.random() > 0.495 ? 1 : -1);
    }

    private void spawnFlies(ArrayList<Entity> entities) {
        int amountOfFlies = (int) (Math.random() * 3 + 2);
        Vector2D center = this.getEntityCenter();

        for (int i = 0; i < amountOfFlies; i++) {
            entities.add(new Fly(center.getX() - Math.random() * 50, center.getY() - Math.random() * 50, this.fly.get()));
        }
    }

    public void contactWithPlayer(Player player) {
        player.receiveDamage(1);
    }
}
