package entities.Room;

import application.GameApplication;
import entities.Entity;
import entities.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Room {
    private Image roomBackground;
    private ArrayList<Entity> entities = new ArrayList<>();

    public Room() {
        this.roomBackground = new Image(getClass().getResource("/basement.png").toExternalForm());

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

    public static boolean isWithinBounds(Entity position) {
        if (position.getX() <= 80) return false;
        if (position.getY() <= 80) return false;
        if (position.getX() + position.getWidth() > GameApplication.WINDOW_WIDTH - 80) return false;
        if (position.getY() + position.getHeight() > GameApplication.WINDOW_HEIGHT - 80) return false;

        return true;
    }
}
