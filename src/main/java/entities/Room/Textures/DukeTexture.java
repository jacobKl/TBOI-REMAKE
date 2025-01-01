package entities.Room.Textures;

import javafx.scene.image.Image;

public class DukeTexture {
    private final Image texture;

    public DukeTexture() {
        this.texture = new Image(getClass().getResource("/duke.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
