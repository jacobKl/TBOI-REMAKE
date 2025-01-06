package entities.Room.Enemies;

import application.GameApplication;
import entities.Entity;
import entities.Room.Textures.ChargerTexture;
import entities.Room.Textures.DukeTexture;
import entities.Room.Textures.FlyTexture;
import entities.RoomPartsFactory;

public class EnemyFactory implements RoomPartsFactory {
    private FlyTexture flyTexture = new FlyTexture();
    private DukeTexture dukeTexture = new DukeTexture();

    private ChargerTexture chargerTexture = new ChargerTexture();

    public Entity createEntity(String entity, double x, double y) {
        double startX = GameApplication.GRID_BUFFER + x * GameApplication.GRID_WIDTH;
        double startY = GameApplication.GRID_BUFFER + y * GameApplication.GRID_WIDTH;

        switch (entity) {
            case "fly":
                return this.createFly(startX, startY);
            case "duke":
                return this.createDuke(startX, startY);
            case "charger":
                return this.createCharger(startX, startY);
        }

        return null;
    }

    private Fly createFly(double x, double y) {
        return new Fly(x, y, this.flyTexture.get());
    }

    private DukeOfFlies createDuke(double x, double y ) {
        return new DukeOfFlies(x, y, this.dukeTexture.get());
    }

    private Charger createCharger(double x, double y) {
        return new Charger(x, y, this.chargerTexture.get());
    }
}
