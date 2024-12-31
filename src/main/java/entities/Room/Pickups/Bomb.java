package entities.Room.Pickups;

import application.AudioManager;
import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Textures.BombTexture;
import javafx.scene.canvas.GraphicsContext;

public class Bomb extends Entity implements PickupEntity {
    private BombTexture bombTexture;
    public Bomb(double x, double y, BombTexture texture) {
        super(x, y, 40, 40);
        this.bombTexture = texture;

        this.collidable = false;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.bombTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerInventory().addBombs(1);

        this.audioManager.playSound("bomb_pickup.mp3");
    }
}
