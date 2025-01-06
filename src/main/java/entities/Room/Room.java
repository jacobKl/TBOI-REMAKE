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
    private ArrayList<Entity> entities;
    private RoomOverlays roomOverlays;
    private Integer roomId = 0;

    public Room(ArrayList<Entity> entities) {
        this.roomBackground = new Image(getClass().getResource("/empty_basement.png").toExternalForm());
        this.entities = entities;
        this.roomOverlays = new RoomOverlays();
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.roomBackground, 0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);
    }

    public void renderShadows(GraphicsContext gc) {
        this.roomOverlays.renderShadow(gc);
        this.roomOverlays.renderOverlay(gc, this.roomId);
    }

    public static boolean isWithinBounds(Entity position) {
        if (position.getX() <= 120) return false;
        if (position.getY() <= 120) return false;
        if (position.getX() + position.getWidth() > GameApplication.WINDOW_WIDTH - 120) return false;
        if (position.getY() + position.getHeight() > GameApplication.WINDOW_HEIGHT - 120) return false;

        return true;
    }

    public void setRoomId(Integer roomId) {
        this.roomId = roomId;
    }

    public Integer getRoomId() {
        return this.roomId;
    }
}
