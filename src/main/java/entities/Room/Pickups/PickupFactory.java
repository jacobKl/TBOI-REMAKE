package entities.Room.Pickups;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.*;
import entities.RoomPartsFactory;

public class PickupFactory implements RoomPartsFactory {
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
        double startX = GameApplication.GRID_BUFFER + x * GameApplication.GRID_WIDTH;
        double startY = GameApplication.GRID_BUFFER + y * GameApplication.GRID_WIDTH;

        switch (entity) {
            case "bomb":
                return this.createBomb(startX, startY);
            case "coin":
                return this.createCoin(startX, startY);
            case "key":
                return this.createKey(startX, startY);
            case "heart":
                return this.createHeart(startX, startY);
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
