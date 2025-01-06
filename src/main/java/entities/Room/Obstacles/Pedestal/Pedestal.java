package entities.Room.Obstacles.Pedestal;

import Utils.OscillatingValue;
import application.Vector2D;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.PedestalTexture;
import javafx.scene.canvas.GraphicsContext;

public class Pedestal extends Entity {
    private PedestalTexture pedestalTexture;
    private PedestalItemInterface item;
    private boolean pickedUp = false;
    private OscillatingValue floatingPosition = new OscillatingValue(0, 1, -0.5, 0.5);
    public Pedestal(double x, double y, PedestalTexture texture, String item) {
        super(x, y, 70, 50);
        this.pedestalTexture = texture;
        this.item = this.getItem(item);
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.pedestalTexture.get(), this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
        if (!this.pickedUp) {
            Vector2D itemPosition = this.getEntityCenter();
            itemPosition.setY(itemPosition.getY() + this.floatingPosition.update() * 10);

            this.item.render(itemPosition, gc);
        }
    }

    private PedestalItemInterface getItem(String item) {
        switch(item) {
            case "blood-of-the-martyr":
                return new BloodOfTheMartyr();
            case "cat-o-nine-tails":
                return new CatONineTails();
        }

        return null;
    }

    public void contactWithPlayer(Player player) {
        this.pickedUp = true;
        this.item.contactWithPlayer(player);
    }
}
