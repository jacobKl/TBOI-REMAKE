package entities.Room.Textures;

import javafx.scene.image.Image;

public class BombTexture {
    private final Image texture;

    public BombTexture() {
        this.texture = new Image(getClass().getResource("/pickup_bomb.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
