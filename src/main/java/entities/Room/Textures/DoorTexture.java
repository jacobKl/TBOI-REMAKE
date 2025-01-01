package entities.Room.Textures;

import javafx.scene.image.Image;

public class DoorTexture {
    private final Image texture;

    public DoorTexture() {
        this.texture = new Image(getClass().getResource("/doors.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
