package entities.Room.Obstacles;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.MushroomTexture;
import entities.Room.Textures.RockTexture;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Mushroom extends Entity {
    private MushroomTexture mushroomTexture;
    public Mushroom(double x, double y, MushroomTexture texture) {
        super(x, y, GameApplication.GRID_WIDTH, GameApplication.GRID_WIDTH);
        this.mushroomTexture = texture;
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(this.getX() + 15, this.getY() + 15, this.width - 30, this.height - 30);
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.mushroomTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
