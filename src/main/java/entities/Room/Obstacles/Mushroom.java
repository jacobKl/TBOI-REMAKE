package entities.Room.Obstacles;

import entities.Entity;
import entities.Room.Textures.MushroomTexture;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Mushroom extends Entity {
    private MushroomTexture mushroomTexture;
    public Mushroom(double x, double y, MushroomTexture texture) {
        super(x, y, 70, 70);
        this.mushroomTexture = texture;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.mushroomTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }
}
