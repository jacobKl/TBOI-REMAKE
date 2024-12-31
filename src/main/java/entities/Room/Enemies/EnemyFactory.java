package entities.Room.Enemies;

import entities.Entity;
import entities.Room.Textures.FlyTexture;

public class EnemyFactory {
    private FlyTexture flyTexture;
    public EnemyFactory() {
        this.flyTexture = new FlyTexture();
    }

    public Entity createEntity(String entity, double x, double y) {
        switch (entity) {
            case "fly":
                return this.createFly(x, y);
        }

        return null;
    }

    private Fly createFly(double x, double y) {
        return new Fly(x, y, this.flyTexture.get());
    }
}
