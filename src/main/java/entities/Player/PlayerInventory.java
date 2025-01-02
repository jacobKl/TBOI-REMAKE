package entities.Player;

import entities.Room.Obstacles.Pedestal.PedestalItemInterface;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class PlayerInventory {
    private Integer bombs = 1;
    private Integer keys = 1;
    private Integer money = 1;

    private ArrayList<PedestalItemInterface> items = new ArrayList<>();

    public Integer getBombs() {
        return this.bombs;
    }

    public Integer getKeys() {
        return this.keys;
    }

    public Integer getMoney() {
        return this.money;
    }

    public void addBombs(Integer number) {
        this.bombs += number;
    }

    public void addCoins(Integer number) {
        this.money += number;
    }

    public void addKeys(Integer number) { this.keys += number; }

    public void equipItem(PedestalItemInterface item) {
        this.items.add(item);
    }

    public void renderItems(GraphicsContext gc, Player player) {
        for (PedestalItemInterface item : this.items) {
            item.renderOnPlayer(gc, player);
        }
    }
}
