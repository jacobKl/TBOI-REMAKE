package entities.Room.Obstacles;

import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.SpikesTexture;
import entities.Room.Textures.WebTexture;
import javafx.scene.canvas.GraphicsContext;

public class Web extends Entity {
    private WebTexture webTexture;

    private Integer row = 0;

    public Web(double x, double y, WebTexture texture) {
        super(x, y, 70, 70);
        this.webTexture = texture;

        this.collidable = false;

        this.row = Math.random() > 0.5 ? 1 : 0;
    }

    public void update(double deltaTime) {

    }
    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.webTexture.get(), this.row * 26, 0, 26, 26, this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerAttributes().slowDown(2.5);
    }
}
