package entities.Room;

import application.GameApplication;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Enemies.Enemy;
import entities.Room.Enemies.EnemyFactory;
import entities.Room.Obstacles.ObstacleFactory;
import entities.Room.Pickups.PickupFactory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class Room {
    private Image roomBackground;
    private Player player;
    private ArrayList<Entity> entities;

    public Room(Player player, ArrayList<Entity> entities) {
        this.roomBackground = new Image(getClass().getResource("/basement.jpg").toExternalForm());
        this.player = player;
        this.entities = entities;

        PickupFactory pickupFactory = new PickupFactory();
        ObstacleFactory obstacleFactory = new ObstacleFactory();
        EnemyFactory enemyFactory = new EnemyFactory();

        this.entities.add(pickupFactory.createEntity("bomb", 100, 100));
        this.entities.add(pickupFactory.createEntity("coin", 400, 200));
        this.entities.add(pickupFactory.createEntity("coin", 450, 200));
        this.entities.add(pickupFactory.createEntity("coin", 150, 200));
        this.entities.add(pickupFactory.createEntity("key", 400, 120));
        this.entities.add(obstacleFactory.createEntity("spikes", 300, 300));
        this.entities.add(enemyFactory.createEntity("fly", 217, 317));
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.roomBackground, 0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);

        for (Entity entity : this.entities) {
            entity.render(gc, deltaTime);
        }
    }

    public static boolean isWithinBounds(Entity position) {
        if (position.getX() <= 80) return false;
        if (position.getY() <= 80) return false;
        if (position.getX() + position.getWidth() > GameApplication.WINDOW_WIDTH - 80) return false;
        if (position.getY() + position.getHeight() > GameApplication.WINDOW_HEIGHT - 80) return false;

        return true;
    }
}
