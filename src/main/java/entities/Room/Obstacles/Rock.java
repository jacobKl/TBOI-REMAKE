package entities.Room.Obstacles;

import entities.Entity;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Rock extends Entity {
    private RockTexture rockTexture;
    public Rock(double x, double y, RockTexture texture) {
        super(x, y, 70, 70);
        this.rockTexture = texture;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.rockTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
