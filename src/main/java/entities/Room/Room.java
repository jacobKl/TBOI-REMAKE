package entities.Room;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.BombTexture;
import entities.Room.Textures.RockTexture;
import entities.Room.Textures.SpikesTexture;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Room {
    private Image roomBackground;

    private ArrayList<Entity> entities;

    public Room() {
        this.roomBackground = new Image(getClass().getResource("/basement.png").toExternalForm());
        this.entities = new ArrayList<>();

        PickupFactory pickupFactory = new PickupFactory();

        this.entities.add(pickupFactory.createEntity("bomb", 100, 100));
        this.entities.add(pickupFactory.createEntity("coin", 400, 200));
        this.entities.add(pickupFactory.createEntity("key", 400, 120));
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.roomBackground, 0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);

        for (Entity entity : this.entities) {
            entity.render(gc, deltaTime);
        }
    }

    public ArrayList<Entity> getEntities() {
        return this.entities;
    }
}
