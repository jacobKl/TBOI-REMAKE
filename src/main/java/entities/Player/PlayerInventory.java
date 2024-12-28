package entities.Player;

public class PlayerInventory {
    private Integer bombs = 1;
    private Integer keys = 1;
    private Integer money = 1;

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
}
