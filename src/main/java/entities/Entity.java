package entities;

import application.AudioManager;
import application.Vector2D;
import entities.Player.Player;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Entity implements EntityInterface {
    protected double width, height;
    private Vector2D position;
    protected boolean collidable = true;
    protected AudioManager audioManager;

    public Entity(double x, double y, double width, double height) {
        this.position = new Vector2D(x, y);
        this.width = width;
        this.height = height;
        this.audioManager = AudioManager.getInstance();
    }

    public double getX() { return this.position.getX(); }
    public double getY() { return this.position.getY(); }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public boolean isCollidable() {
        return this.collidable;
    }

    public Bounds getBounds() {
        return new BoundingBox(this.position.getX(), this.position.getY(), this.width, this.height);
    }

    public boolean intersects(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }

    @Override
    public void render(GraphicsContext gc, double deltaTime) {}

    @Override
    public void contactWithPlayer(Player player) {}

    public Vector2D getPosition() {
        return this.position;
    }

    public void setX(double x) {
        this.position.setX(x);
    }

    public void setY(double y) {
        this.position.setY(y);
    }

    public Vector2D getEntityCenter() {
        double startX = this.getPosition().getX() + this.width / 2;
        double startY = this.getPosition().getY() + this.height / 2;

        return new Vector2D(startX, startY);
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {

    }
}
