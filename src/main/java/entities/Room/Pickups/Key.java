package entities.Room.Pickups;

import application.AudioManager;
import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Textures.KeyTexture;
import javafx.scene.canvas.GraphicsContext;

public class Key extends Entity implements PickupEntity {
    private KeyTexture keyTexture;
    public Key(double x, double y, KeyTexture texture) {
        super(x, y, 40, 40);
        this.keyTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.keyTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        AudioManager am = AudioManager.getInstance();
        player.getPlayerInventory().addKeys(1);

        this.audioManager.playSound("key_pickup.mp3");
    }
}
