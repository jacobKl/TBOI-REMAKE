package entities.Room.Pickups;

import entities.Entity;
import entities.Room.Textures.*;

public class PickupFactory {
    private BombTexture bombTexture;
    private CoinTexture coinTexture;
    private KeyTexture keyTexture;
    private HeartTexture heartTexture;
    public PickupFactory() {
        this.bombTexture = new BombTexture();
        this.coinTexture = new CoinTexture();
        this.keyTexture = new KeyTexture();
        this.heartTexture = new HeartTexture();
    }

    public Entity createEntity(String entity, double x, double y) {
        switch (entity) {
            case "bomb":
                return this.createBomb(x, y);
            case "coin":
                return this.createCoin(x, y);
            case "key":
                return this.createKey(x, y);
            case "heart":
                return this.createHeart(x, y);
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

    private Heart createHeart(double x, double y) { return new Heart(x, y, this.heartTexture); }
}
