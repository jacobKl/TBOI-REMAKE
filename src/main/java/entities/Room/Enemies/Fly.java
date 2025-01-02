package entities.Room.Enemies;

import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Fly extends Enemy {
    private Integer column = 0;
    private double clock = 0.1;
    private double speed = 1;

    public Fly(double startX, double startY, Image spritesheet) {
        super(startX, startY, 48, 48, spritesheet, 6);
        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spritesheet, this.column * 32, 0, 32, 32, this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        this.clock -= deltaTime;

        if (this.clock < 0) {
            this.column += 1;
            this.clock = 0.1;
        }

        if (this.column > 1)
            this.column = 0;

        double angle = this.getGoToPlayerAngle(player);

        this.setX(this.getX() + Math.sin(angle) * speed);
        this.setY(this.getY() + Math.cos(angle) * speed);
    }

    public void contactWithPlayer(Player player) {
        player.receiveDamage(1);
    }
}
