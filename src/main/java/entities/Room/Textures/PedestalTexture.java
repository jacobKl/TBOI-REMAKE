package entities.Room.Textures;

import javafx.scene.image.Image;

public class PedestalTexture {
    private final Image texture;

    public PedestalTexture() {
        this.texture = new Image(getClass().getResource("/pedestal.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
