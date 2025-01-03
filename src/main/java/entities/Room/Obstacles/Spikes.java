package entities.Room.Obstacles;

import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.SpikesTexture;
import javafx.scene.canvas.GraphicsContext;

public class Spikes extends Entity {
    private SpikesTexture spikesTexture;
    public Spikes(double x, double y, SpikesTexture texture) {
        super(x, y, 70, 70);
        this.spikesTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spikesTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.receiveDamage(1);
    }
}
