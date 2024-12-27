package entities.Room;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.RockTexture;
import entities.Room.Textures.SpikesTexture;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Room {
    private Image roomBackground;

    private Entity[] entities;

    public Room() {
        this.roomBackground = new Image(getClass().getResource("/basement.png").toExternalForm());
        this.entities = new Entity[3];

        RockTexture rockTexture = new RockTexture();
        SpikesTexture spikesTexture = new SpikesTexture();

        this.entities[0] = new Rock(350, 350, rockTexture);
        this.entities[1] = new Rock(160, 160, rockTexture);
        this.entities[2] = new Spikes(350, 120, spikesTexture);
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.roomBackground, 0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);

        for (Entity entity : this.entities) {
            entity.render(gc, deltaTime);
        }
    }

    public Entity[] getEntities() {
        return this.entities;
    }
}
