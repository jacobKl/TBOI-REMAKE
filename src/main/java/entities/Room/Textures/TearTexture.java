package entities.Room.Textures;

import javafx.scene.image.Image;

public class TearTexture {
    private final Image texture;

    public TearTexture() {
        this.texture = new Image(getClass().getResource("/tear.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
