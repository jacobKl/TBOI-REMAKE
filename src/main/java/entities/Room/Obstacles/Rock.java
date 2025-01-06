package entities.Room.Obstacles;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Rock extends Entity {
    private RockTexture rockTexture;
    public Rock(double x, double y, RockTexture texture) {
        super(x, y, GameApplication.GRID_WIDTH, GameApplication.GRID_WIDTH);
        this.rockTexture = texture;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.rockTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
