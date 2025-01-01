package entities.Room.Textures;

import javafx.scene.image.Image;

public class DoorOpenTexture {
    private final Image texture;

    public DoorOpenTexture() {
        this.texture = new Image(getClass().getResource("/doors_open.png").toExternalForm());
    }
    public Image get() {
            return this.texture;
        }
}
