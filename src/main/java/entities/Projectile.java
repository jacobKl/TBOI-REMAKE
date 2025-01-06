package entities;

import application.Vector2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;

public class Projectile extends Entity {
    public static double CURVE_FACTOR = 0.2;
    public static double SPEED = 500;

    private Vector2D walkingVector, shootingVector;
    private boolean perpendicular;
    private Image projectileImage;
    private Image projectileShadow;

    public Projectile(double startX, double startY, Vector2D walkingVector, Vector2D shootingVector, boolean perpendicular, Image texture, Image projectileShadow) {
        super(startX - 15 , startY - 15, 30, 30);
        this.walkingVector = walkingVector;
        this.shootingVector = shootingVector;
        this.perpendicular = perpendicular;
        this.collidable = false;

        if (this.perpendicular) {
            this.shootingVector.setX(this.shootingVector.getX() + this.walkingVector.getX() * Projectile.CURVE_FACTOR);
            this.shootingVector.setY(this.shootingVector.getY() + this.walkingVector.getY() * Projectile.CURVE_FACTOR);
        }

        this.projectileImage = texture;
        this.projectileShadow = projectileShadow;
    }

    public void update(double deltaTime) {
        this.setX(this.getX() + this.shootingVector.getX() * Projectile.SPEED * deltaTime);
        this.setY(this.getY() + this.shootingVector.getY() * Projectile.SPEED * deltaTime);
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(this.projectileImage, this.getX(), this.getY(), this.width, this.height);

        gc.setGlobalAlpha(0.3);
        gc.drawImage(this.projectileShadow, this.getX() + 5, this.getY() + 40, 20, 20);
        gc.setGlobalAlpha(1);
    }
}
