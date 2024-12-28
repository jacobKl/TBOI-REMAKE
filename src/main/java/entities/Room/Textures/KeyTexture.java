package entities.Room.Textures;

import javafx.scene.image.Image;

public class KeyTexture {
    private final Image texture;

    public KeyTexture() {
        this.texture = new Image(getClass().getResource("/pickup_key.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
