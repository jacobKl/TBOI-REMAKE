package entities.Room.Textures;

import javafx.scene.image.Image;

public class FlyTexture {
    private final Image texture;

    public FlyTexture() {
        this.texture = new Image(getClass().getResource("/fly.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
