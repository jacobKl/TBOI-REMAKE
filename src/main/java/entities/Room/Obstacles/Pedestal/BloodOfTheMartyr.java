package entities.Room.Obstacles.Pedestal;

import GUI.GUI;
import application.Vector2D;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

public class BloodOfTheMartyr extends PedestalItem implements PedestalItemInterface {
    private Image image = new Image(getClass().getResource("/item/blood-of-the-martyr.png").toExternalForm());
    private boolean pickedUp = false;
    public void render(Vector2D center, GraphicsContext gc) {
        gc.drawImage(this.image, center.getX() - 32, center.getY() - 64, 64, 40);
    }

    public void contactWithPlayer(Player player) {
        if (this.pickedUp) return;

        player.getPlayerAttributes().changeDamage(3);
        player.getPlayerInventory().equipItem(this);
        this.shouldDisplayItemInfo = true;
        this.pickedUp = true;
    }

    public void renderOnPlayer(GraphicsContext gc, Player player) {
        Vector2D center = player.getEntityCenter();

        gc.drawImage(this.image, center.getX() - 37, center.getY() - 74, 74, 41);

        if (this.shouldDisplayItemInfo) {
            this.displayItemInfo("Blood of The Martyr", "Big damage up", gc);
        }
    }
}
