package entities.Room.Textures;

import javafx.scene.image.Image;

public class Texture {
    private final Image texture;

    public Texture(String path) {
        this.texture = new Image(getClass().getResource(path).toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
