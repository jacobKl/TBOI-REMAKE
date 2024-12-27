package entities.Room;

import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.SpikesTexture;
import javafx.scene.canvas.GraphicsContext;

public class Spikes extends Entity {
    private SpikesTexture spikesTexture;
    public Spikes(double x, double y, SpikesTexture texture) {
        super(x, y, 50, 50);
        this.spikesTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.spikesTexture.get(), this.x, this.y, this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.recieveDamage(1);
    }
}
