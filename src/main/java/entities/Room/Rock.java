package entities.Room;

import entities.Entity;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Rock extends Entity {
    private RockTexture rockTexture;
    public Rock(double x, double y, RockTexture texture) {
        super(x, y, 50, 50);
        this.rockTexture = texture;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.rockTexture.get(), this.x, this.y, this.width, this.height);
    }
}
