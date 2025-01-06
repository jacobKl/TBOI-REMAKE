package entities.Room.Obstacles;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.VaseTexture;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Vase extends Entity {    public Vase(double x, double y, VaseTexture texture) {
    super(x, y, GameApplication.GRID_WIDTH, GameApplication.GRID_WIDTH);
    this.vaseTexture = texture;
}
    private VaseTexture vaseTexture;

    public Bounds getBounds() {
        return new BoundingBox(this.getX() + 15, this.getY() + 15, this.width - 30, this.height - 30);
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.vaseTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
