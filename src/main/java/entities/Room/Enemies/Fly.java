package entities.Room.Enemies;

import Utils.Clock;
import Utils.OscillatingValue;
import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Fly extends Enemy {
    private Integer column = 0;
    private Clock clock = new Clock(0.1);

    private OscillatingValue value = new OscillatingValue(0, 1, -0.5, 0.5);
    private double speed = 1;

    public Fly(double startX, double startY, Image spritesheet) {
        super(startX, startY, 48, 48, spritesheet, 6);
        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spritesheet, this.column * 32, 0, 32, 32, this.getX(), this.getY() + this.value.update() * 10, this.getWidth(), this.getHeight());
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {

        if (this.clock.update(deltaTime)) {
            this.column = (this.column + 1) % 2;
        }

        double angle = this.getGoToPlayerAngle(player);

        this.setX(this.getX() + Math.sin(angle) * speed);
        this.setY(this.getY() + Math.cos(angle) * speed);
    }

    public void contactWithPlayer(Player player) {
        player.receiveDamage(1);
    }
}
