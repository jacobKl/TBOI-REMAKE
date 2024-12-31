package entities.Room.Pickups;

import application.AudioManager;
import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Textures.CoinTexture;
import javafx.scene.canvas.GraphicsContext;

public class Coin extends Entity implements PickupEntity {
    private CoinTexture coinTexture;
    public Coin(double x, double y, CoinTexture texture) {
        super(x, y, 30, 30);
        this.coinTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.coinTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        AudioManager am = AudioManager.getInstance();
        player.getPlayerInventory().addCoins(1);

        this.audioManager.playSound("gold_pickup.wav");
    }
}
