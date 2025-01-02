package entities.Room.Obstacles.Pedestal;

import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.PedestalTexture;
import entities.Room.Textures.RockTexture;
import javafx.scene.canvas.GraphicsContext;

public class Pedestal extends Entity {
    private PedestalTexture pedestalTexture;
    private PedestalItemInterface item;
    private boolean pickedUp = false;
    public Pedestal(double x, double y, PedestalTexture texture, String item) {
        super(x, y, 70, 50);
        this.pedestalTexture = texture;
        this.item = this.getItem(item);
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.pedestalTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
        if (!this.pickedUp) {
            this.item.render(this.getEntityCenter(), gc);
        }
    }

    private PedestalItemInterface getItem(String item) {
        return new BloodOfTheMartyr();
    }

    public void contactWithPlayer(Player player) {
        this.pickedUp = true;
        this.item.contactWithPlayer(player);
    }
}
