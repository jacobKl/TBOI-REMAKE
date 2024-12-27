package entities;

import entities.Player.Player;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Entity implements EntityInterface {
    protected double x, y, width, height;
    protected boolean collidable = true;

    public Entity(double x, double y, double width, double height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public double getX() { return x; }
    public double getY() { return y; }
    public double getWidth() { return width; }
    public double getHeight() { return height; }

    public boolean isCollidable() {
        return this.collidable;
    }

    public Bounds getBounds() {

        return new BoundingBox(this.x, this.y, this.width, this.height);
    }

    public boolean intersects(Entity other) {
        return this.getBounds().intersects(other.getBounds());
    }

    @Override
    public void render(GraphicsContext gc, double deltaTime) {}

    @Override
    public void contactWithPlayer(Player player) {}
}
