package entities.Room;

import entities.Entity;
import entities.Room.Textures.*;

public class PickupFactory {
    private RockTexture rockTexture;
    private SpikesTexture spikesTexture;
    private BombTexture bombTexture;

    private CoinTexture coinTexture;

    private KeyTexture keyTexture;
    public PickupFactory() {
        this.rockTexture = new RockTexture();
        this.spikesTexture = new SpikesTexture();
        this.bombTexture = new BombTexture();
        this.coinTexture = new CoinTexture();
        this.keyTexture = new KeyTexture();
    }

    public Entity createEntity(String entity, double x, double y) {
        switch (entity) {
            case "bomb":
                return this.createBomb(x, y);
            case "coin":
                return this.createCoin(x, y);
            case "key":
                return this.createKey(x, y);
        }

        return null;
    }

    private Bomb createBomb(double x, double y) {
        return new Bomb(x, y, this.bombTexture);
    }

    private Coin createCoin(double x, double y) {
        return new Coin(x, y, this.coinTexture);
    }

    private Key createKey(double x, double y) { return new Key(x, y, this.keyTexture); }
}
