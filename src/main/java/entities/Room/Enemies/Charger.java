package entities.Room.Enemies;

import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Room;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class Charger extends Enemy {
    private Vector2D direction = new Vector2D(0, 0);
    private double speed = 3;
    private double directionCounter = 2.5;
    private double deltaBody = 0.5;
    private Integer columnIndex = 0;
    private Integer rowIndex = 0;
    private boolean charging = false;
    private boolean flip;

    private Entity projectedDirection;

    public Charger(double startX, double startY, Image spritesheet) {
        super(startX, startY, 70, 70, spritesheet, 12);
        this.direction = this.getRandomDirection();
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        Entity futurePosition = new Entity(this.getX() + this.direction.getX() * speed, this.getY() + this.direction.getY() * speed, 70, 70);

        this.directionCounter -= deltaTime;
        this.deltaBody -= deltaTime;

        Entity direction = this.getProjectedDirection();

        this.projectedDirection = direction;

        this.setSprites(this.getDirectionFromVector());

        if (player.intersects(direction) && !this.charging) {
            this.charging = true;
            this.speed = 6;
            this.audioManager.playSound("gulp.mp3");
        } else if (!player.intersects(direction)) {
            this.charging = false;
            this.speed = 3;
        }

        if (this.charging) {
            String dir = this.getDirectionFromVector();

            if (dir.equals("bottom")) {
                this.columnIndex = 0;
                this.rowIndex = 3;
            } else if (dir.equals("right") || dir.equals("left")){
                this.columnIndex = 1;
                this.rowIndex = 3;
            }
        }

        if (this.deltaBody < 0 && !this.charging) {
            this.columnIndex = (this.columnIndex + 1) % 3;
            this.deltaBody = 0.5;
        }

        if ((!Room.isWithinBounds(futurePosition) || this.directionCounter < 0) && !this.charging) {
            this.direction = this.getRandomDirection();

            this.directionCounter = Math.random() * 2.5;
        } else {
            this.setX(futurePosition.getX());
            this.setY(futurePosition.getY());
        }
    }

    public void render(GraphicsContext gc, double deltaTime) {
        if (this.flip) {
            gc.drawImage(this.spritesheet, this.columnIndex * 32, this.rowIndex * 32, 32, 32, this.getX() + this.width, this.getY(), -this.getWidth(), this.getHeight());
        } else {
            gc.drawImage(this.spritesheet, this.columnIndex * 32, this.rowIndex * 32, 32, 32, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        }

//        if (this.projectedDirection != null) {
//            gc.fillRect(this.projectedDirection.getX(), this.projectedDirection.getY(), this.projectedDirection.getWidth(), this.projectedDirection.getHeight());
//        }
    }

    private Vector2D getRandomDirection() {
        Integer direction = Math.random() > 0.495 ? 1 : 0;
        Vector2D newDirection;

        if (direction == 1) {
            newDirection = new Vector2D(Math.random() > 0.495 ? 1 : -1, 0);
        } else {
            newDirection = new Vector2D(0, Math.random() > 0.495 ? 1 : -1);
        }

        if (newDirection.getX() == this.direction.getX() && newDirection.getY() == this.direction.getY()) {
            return this.getRandomDirection();
        } else {
            return newDirection;
        }
    }

    private String getDirectionFromVector() {
        if (this.direction.getX() == 1) {
            return "right";
        } else if (this.direction.getX() == -1) {
            return "left";
        } else if (this.direction.getY() == -1) {
            return "top";
        } else if (this.direction.getY() == 1) {
            return "bottom";
        }

        return "";
    }

    private void setSprites(String direction) {
        if (Objects.equals(direction, "top")) {
            this.rowIndex = 1;
        }

        if (Objects.equals(direction, "bottom")) {
            this.rowIndex = 2;
        }

        if (Objects.equals(direction, "right")) {
            this.rowIndex = 0;
        }

        this.flip = false;

        if (Objects.equals(direction, "left")) {
            this.rowIndex = 0;
            this.flip = true;
        }
    }

    private Entity getProjectedDirection() {
        if (this.direction.getX() == 1) {
            return new Entity(this.getX() + this.width, this.getY(), 400, 70);
        } else if (this.direction.getX() == -1) {
            return new Entity(this.getX() - 400, this.getY(), 400, 70);
        } else if (this.direction.getY() == 1) {
            return new Entity(this.getX(), this.getY() + this.height, 70, 400);
        } else {
            return new Entity(this.getX(), this.getY() - 400, 70, 400);
        }
    }
}
