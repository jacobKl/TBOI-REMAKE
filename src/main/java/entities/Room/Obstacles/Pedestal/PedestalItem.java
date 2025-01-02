package entities.Room.Obstacles.Pedestal;

import GUI.GUI;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

abstract public class PedestalItem {
    protected boolean shouldDisplayItemInfo = false;
    private Image underlay = new Image(getClass().getResource("/item_get.png").toExternalForm());

    protected void displayItemInfo(String name, String description, GraphicsContext gc) {
        gc.setFill(Color.WHITE);
        gc.setFont(GUI.font);

        gc.setTextAlign(TextAlignment.CENTER);

        gc.drawImage(this.underlay, 270, 45, 660, 100);
        gc.fillText(name, 600, 70);
        gc.fillText(description, 600, 100);

        CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS).execute(() -> {
            this.shouldDisplayItemInfo = false;
        });
    }
}
