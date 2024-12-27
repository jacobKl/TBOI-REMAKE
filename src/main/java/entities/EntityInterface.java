package entities;

import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;

public interface EntityInterface {

    public void render(GraphicsContext gc, double deltaTime);
    public void contactWithPlayer(Player player);
}
