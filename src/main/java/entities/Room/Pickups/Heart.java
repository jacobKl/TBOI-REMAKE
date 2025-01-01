package entities.Room.Pickups;

import application.AudioManager;
import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Textures.CoinTexture;
import entities.Room.Textures.HeartTexture;
import javafx.scene.canvas.GraphicsContext;

public class Heart extends Entity implements PickupEntity {
    private HeartTexture heartTexture;
    public Heart(double x, double y, HeartTexture texture) {
        super(x, y, 40, 40);
        this.heartTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.heartTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerAttributes().addHealth(1);

        this.audioManager.playSound("heart_pickup.mp3");
    }
}
