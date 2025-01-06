package entities.Room;

import Utils.OscillatingValue;
import application.GameApplication;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class RoomOverlays {
    private Image roomShadow = new Image(getClass().getResource("/room_shading.png").toExternalForm());

    private Image roomOverlays = new Image(getClass().getResource("/room_overlays.png").toExternalForm());

    private OscillatingValue shining = new OscillatingValue(0.5, 1, 0.5, 1, 0.005);
    public void renderShadow(GraphicsContext gc) {
        gc.drawImage(this.roomShadow, 0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);
    }

    public void renderOverlay(GraphicsContext gc, Integer roomId) {
        Integer overlayToRender = roomId % 3;

        double value = this.shining.update();

        gc.setGlobalAlpha(value);
        gc.drawImage(this.roomOverlays, overlayToRender * 660 + overlayToRender * 10, 0, 660, 423, value, value, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);
        gc.setGlobalAlpha(1);
    }
}
