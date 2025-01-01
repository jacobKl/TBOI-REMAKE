package entities.Room.Enemies.Duke;

import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Enemies.Enemy;
import entities.Room.Enemies.EnemyFactory;
import entities.Room.Enemies.Fly;
import entities.Room.Room;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class DukeOfFlies extends Enemy {
    private Image spritesheet;
    private Vector2D direction;
    private double speed = 2;
    private boolean coughing = false;

    private double deltaSum = 0.5;

    private double fliesCountdown = 10;
    private Integer column = 0;

    public DukeOfFlies(double startX, double startY, Image spritesheet) {
        super(startX, startY, 200, 200, spritesheet, 300);

        this.collidable = false;
        this.spritesheet = spritesheet;

        this.direction = this.getRandomDirection();
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spritesheet, this.column * 80, 0, 80, 64, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        this.followDirection(player);
        this.fliesCountdown -= deltaTime;

        if (this.fliesCountdown < 0) {
            this.coughing = true;
            this.fliesCountdown = Math.random() * 5 + 5;
        }

        if (this.coughing) {
            this.deltaSum -= deltaTime;

            if (this.deltaSum < 0) {
                this.column += 1;

                if (this.column >= 3) {
                    this.column = 0;
                    this.coughing = false;
                    this.spawnFlies(entities);
                }

                this.deltaSum = 0.5;
            }
        }
    }

    private void followDirection(Player player) {
        if (!Room.isWithinBounds(this) || this.intersects(player)) {
            this.direction = this.getRandomDirection();
        }

        this.setX(this.getX() + this.direction.getX() * this.speed);
        this.setY(this.getY() + this.direction.getY() * this.speed);
    }

    private Vector2D getRandomDirection() {
        return new Vector2D(Math.random() * 2 - 1, Math.random() * 2 - 1);
    }

    private void spawnFlies(ArrayList<Entity> entities) {
        EnemyFactory enemyFactory = new EnemyFactory();
        int amountOfFlies = (int) (Math.random() * 5);
        Vector2D center = this.getEntityCenter();

        for (int i = 0; i < amountOfFlies; i++) {
            entities.add(enemyFactory.createEntity("fly", center.getX() - Math.random() * 50, center.getY() - Math.random() * 50));
        }
    }
}
