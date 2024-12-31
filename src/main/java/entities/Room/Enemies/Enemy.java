package entities.Room.Enemies;

import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import javafx.scene.image.Image;

public abstract class Enemy extends Entity {
    protected Image spritesheet;
    private double health;

    public Enemy(double startX, double startY, double width, double height, Image spritesheet, double health) {
        super(startX, startY, width, height);

        this.spritesheet = spritesheet;
        this.health = health;
    }

    abstract public void update(double deltaTime, Player player);

    protected double getGoToPlayerAngle(Player player) {
        Vector2D playerPosition = player.getPosition();

        double angle = Math.atan2(playerPosition.getX() - this.getX(), playerPosition.getY() - this.getY());

        return angle;
    }

    public boolean receiveDamage(double amount) {
        this.health -= amount;

        if (this.health < 0) {
            return true;
        }

        return false;
    }
}
