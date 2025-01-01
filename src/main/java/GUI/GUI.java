package GUI;

import entities.Player.Player;
import entities.Player.PlayerInventory;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.InputStream;

public class GUI {
    private Image heartImage, emptyHeartImage, keyImage, bombImage, moneyImage;
    private Font font;

    public GUI() {
        this.heartImage = new Image(getClass().getResource("/full_heart.png").toExternalForm());
        this.emptyHeartImage = new Image(getClass().getResource("/empty_heart.png").toExternalForm());
        this.keyImage = new Image(getClass().getResource("/pickup_key.png").toExternalForm());
        this.bombImage = new Image(getClass().getResource("/pickup_bomb.png").toExternalForm());
        this.moneyImage = new Image(getClass().getResource("/pickup_coin.png").toExternalForm());

       this.font = Font.loadFont(getClass().getResourceAsStream("/fonts/upheavtt.ttf"), 24);
    }

    public void render(GraphicsContext gc, double deltaTime, Player player) {
        PlayerInventory playerInventory = player.getPlayerInventory();

        this.renderHearts(gc, player);

        this.renderRow(gc, 0, this.moneyImage, playerInventory.getMoney());
        this.renderRow(gc, 1, this.bombImage, playerInventory.getBombs());
        this.renderRow(gc, 2, this.keyImage, playerInventory.getKeys());
    }

    private void renderHearts(GraphicsContext gc, Player player) {
        for (int i = 0; i < player.getPlayerAttributes().getMaxHealth(); i++) {
            if (i + 1 <= player.getPlayerAttributes().getCurrentHealth()) {
                gc.drawImage(this.heartImage, 70 + i * 50, 10, 50, 50);
            } else {
                gc.drawImage(this.emptyHeartImage, 70 + i * 50, 10, 50, 50);
            }
        }
    }

    private void renderRow(GraphicsContext gc, Integer index, Image image, Integer amount) {
        gc.drawImage(image, 20, 60 + index * 50, 40,40);
        gc.setFill(Color.WHITE);
        gc.setFont(this.font);

        gc.fillText(String.format("%02" + "d", amount), 70, 85 + index * 50);
    }
}
