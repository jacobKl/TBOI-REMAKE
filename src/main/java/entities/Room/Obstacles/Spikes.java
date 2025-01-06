package entities.Room.Obstacles;

import application.GameApplication;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.SpikesTexture;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;

public class Spikes extends Entity {
    private SpikesTexture spikesTexture;
    public Spikes(double x, double y, SpikesTexture texture) {
        super(x, y, GameApplication.GRID_WIDTH, GameApplication.GRID_WIDTH);
        this.spikesTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spikesTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(this.getX() + 15, this.getY() + 15, this.width - 30, this.height - 30);
    }

    public void contactWithPlayer(Player player) {
        player.receiveDamage(1);
    }
}
