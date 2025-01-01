package entities.Room.Textures;

import javafx.scene.image.Image;

public class HeartTexture {
    private final Image texture;

    public HeartTexture() {
        this.texture = new Image(getClass().getResource("/pickup_heart.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
