package entities.Room;

import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Textures.CoinTexture;
import javafx.scene.canvas.GraphicsContext;

public class Coin extends Entity implements PickupEntity {
    private CoinTexture coinTexture;
    public Coin(double x, double y, CoinTexture texture) {
        super(x, y, 40, 40);
        this.coinTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.coinTexture.get(), this.x, this.y, this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerInventory().addCoins(1);
    }
}
