package entities.Room.Obstacles;

import application.GameApplication;
import entities.Entity;
import entities.Player.Player;
import entities.Room.Textures.SpikesTexture;
import entities.Room.Textures.WebTexture;
import javafx.scene.canvas.GraphicsContext;

public class Web extends Entity {
    private WebTexture webTexture;

    private Integer row = 0;

    public Web(double x, double y, WebTexture texture) {
        super(x, y, GameApplication.GRID_WIDTH, GameApplication.GRID_WIDTH);
        this.webTexture = texture;

        this.collidable = false;

        this.row = Math.random() > 0.5 ? 1 : 0;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        gc.drawImage(this.webTexture.get(), this.row * 26, 0, 26, 26, this.getPosition().getX(), this.getPosition().getY(), this.width, this.height);
    }

    public void contactWithPlayer(Player player) {
        player.getPlayerAttributes().slowDown(2.5);
    }
}
