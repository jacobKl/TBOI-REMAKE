package entities.Room.Obstacles;

import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Enemies.Enemy;
import entities.Room.Textures.DoorOpenTexture;
import entities.Room.Textures.DoorTexture;
import entities.Room.Textures.Texture;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class Door extends Entity {
    private DoorOpenTexture doorOpenTexture;
    private DoorTexture doorTexture;
    private Integer toRoomId;
    private String orientation;
    private Texture currentTexture;

    public Door(double x, double y, DoorOpenTexture doorOpenTexture, DoorTexture doorTexture, Integer toRoomId, String orientation) {
        super(x, y, 120, 90);
        this.doorOpenTexture = doorOpenTexture;
        this.doorTexture = doorTexture;
        this.toRoomId = toRoomId;
        this.orientation = orientation;

        this.currentTexture = this.doorOpenTexture;
        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        double angle = this.getRenderAngle();
        Vector2D center = this.getEntityCenter();

        gc.save();
        gc.translate(center.getX(), center.getY());

        gc.rotate(angle);

        gc.drawImage(this.currentTexture.get(), -this.getWidth() / 2, -this.getHeight() / 2);

        gc.restore();
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        boolean monstersAlive = entities.stream().anyMatch(o -> o instanceof Enemy);

        if (monstersAlive && !(this.currentTexture instanceof DoorTexture)) {
            this.currentTexture = this.doorTexture;
        } else if (!monstersAlive && !(this.currentTexture instanceof DoorOpenTexture)) {
            this.currentTexture = this.doorOpenTexture;
        }
    }

    public double getRenderAngle() {
        switch(this.orientation) {
            case "bottom":
                return 180;
        }

        return 0;
    }

    public Vector2D getWalkOutPosition() {
        switch (this.orientation) {
            case "top":
                return new Vector2D(550, 580);
        }

        return new Vector2D(550, 140);
    }

    public Integer getToRoomId() {
        return this.toRoomId;
    }

    public Bounds getBounds() {
        return new BoundingBox(this.getPosition().getX() + 10, this.getPosition().getY() + 25, this.width - 20, this.height - 50);
    }

    public boolean isClosed() {
        return this.currentTexture instanceof DoorTexture;
    }
}
