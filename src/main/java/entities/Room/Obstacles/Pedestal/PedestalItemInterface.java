package entities.Room.Obstacles.Pedestal;

import application.Vector2D;
import entities.Player.Player;
import javafx.scene.canvas.GraphicsContext;

public interface PedestalItemInterface {
    public void render(Vector2D center, GraphicsContext gc);

    public void contactWithPlayer(Player player);

    public void renderOnPlayer(GraphicsContext gc, Player player);
}
