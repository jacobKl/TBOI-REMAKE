package entities.Room.Obstacles.Pedestal;

import application.Vector2D;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class BloodOfTheMartyr implements PedestalItemInterface {
    private Image image = new Image(getClass().getResource("/item/blood-of-the-martyr.png").toExternalForm());
    public void render(Vector2D center, GraphicsContext gc) {
        gc.drawImage(this.image, center.getX() - 32, center.getY() - 64, 64, 40);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerAttributes().changeDamage(3);
        player.getPlayerInventory().equipItem(this);
    }

    public void renderOnPlayer(GraphicsContext gc, Player player) {
        Vector2D center = player.getEntityCenter();

        gc.drawImage(this.image, center.getX() - 37, center.getY() - 74, 74, 41);
    }
}
