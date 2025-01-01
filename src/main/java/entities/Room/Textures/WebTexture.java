package entities.Room.Textures;

import javafx.scene.image.Image;

public class WebTexture {
    private final Image texture;

    public WebTexture() {
        this.texture = new Image(getClass().getResource("/web.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
