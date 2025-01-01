package entities.Room.Obstacles;

import entities.Entity;
import entities.Room.Textures.PedestalTexture;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Pedestal extends Entity {
    private PedestalTexture pedestalTexture;
    public Pedestal(double x, double y, PedestalTexture texture) {
        super(x, y, 70, 50);
        this.pedestalTexture = texture;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.pedestalTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
