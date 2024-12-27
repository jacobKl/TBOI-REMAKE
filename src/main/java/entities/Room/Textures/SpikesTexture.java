package entities.Room.Textures;

import javafx.scene.image.Image;

public class SpikesTexture {
    private final Image texture;

    public SpikesTexture() {
        this.texture = new Image(getClass().getResource("/basement_spikes.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
