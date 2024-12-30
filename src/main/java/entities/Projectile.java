package entities;

import application.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Projectile extends Entity {
    public static double CURVE_FACTOR = 0.2;
    public static double SPEED = 300;

    private Vector2D walkingVector, shootingVector;
    private boolean perpendicular;
    private Image projectileImage;

    public Projectile(double startX, double startY, Vector2D walkingVector, Vector2D shootingVector, boolean perpendicular) {
        super(startX, startY, 20, 20);
        this.walkingVector = walkingVector;
        this.shootingVector = shootingVector;
        this.perpendicular = perpendicular;

        if (this.perpendicular) {
            this.shootingVector.setX(this.shootingVector.getX() + this.walkingVector.getX() * Projectile.CURVE_FACTOR);
            this.shootingVector.setY(this.shootingVector.getY() + this.walkingVector.getY() * Projectile.CURVE_FACTOR);
        }

        this.projectileImage = new Image(getClass().getResource("/tear.png").toExternalForm());
    }

    public void update(double deltaTime) {
        this.setX(this.getX() + this.shootingVector.getX() * Projectile.SPEED * deltaTime);
        this.setY(this.getY() + this.shootingVector.getY() * Projectile.SPEED * deltaTime);
    }

    public void render(GraphicsContext gc) {
        gc.setFill(Color.RED);
        gc.drawImage(this.projectileImage, this.getX(), this.getY(), this.width, this.height);
    }
}
