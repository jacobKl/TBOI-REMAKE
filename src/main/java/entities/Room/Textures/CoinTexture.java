package entities.Room.Textures;

import javafx.scene.image.Image;

public class CoinTexture {
    private final Image texture;

    public CoinTexture() {
        this.texture = new Image(getClass().getResource("/pickup_coin.png").toExternalForm());
    }

    public Image get() {
        return this.texture;
    }
}
