package entities.Room.Obstacles;

import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.DoorOpenTexture;
import entities.Room.Textures.DoorTexture;
import javafx.scene.canvas.GraphicsContext;

public class Door extends Entity {
    private DoorOpenTexture doorOpenTexture;
    private DoorTexture doorTexture;

    public Door(double x, double y, DoorOpenTexture doorOpenTexture, DoorTexture doorTexture) {
        super(x, y, 120, 90);
        this.doorOpenTexture = doorOpenTexture;
        this.doorTexture = doorTexture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.doorOpenTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        System.out.println("Doors touched");
    }
}
