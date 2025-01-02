package entities.Room.Obstacles.Pedestal;

import GUI.GUI;
import application.Vector2D;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CatONineTails extends PedestalItem implements PedestalItemInterface {
    private Image image = new Image(getClass().getResource("/item/cat-o-nine-tails.png").toExternalForm());
    private boolean pickedUp = false;
    public void render(Vector2D center, GraphicsContext gc) {
        gc.drawImage(this.image, 4, 39, 30, 30, center.getX() - 32, center.getY() - 74, 64, 64);
    }

    public void contactWithPlayer(Player player) {
        if (this.pickedUp) return;

        player.getPlayerAttributes().changeShootSpeed(-0.1);
        player.getPlayerInventory().equipItem(this);
        this.shouldDisplayItemInfo = true;
        this.pickedUp = true;
    }

    public void renderOnPlayer(GraphicsContext gc, Player player) {
        Vector2D center = player.getEntityCenter();

        gc.drawImage(this.image, 4, 4, 30, 30, center.getX() - 32, center.getY() - 56, 64, 64);

        if (this.shouldDisplayItemInfo) {
            this.displayItemInfo("Cat O'Nine Tails", "Shot speed up, damage up", gc);
        }
    }
}
