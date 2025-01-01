package entities.Room.Obstacles;

import entities.Entity;
import entities.Room.Obstacles.Rock;
import entities.Room.Obstacles.Spikes;
import entities.Room.Textures.*;

public class ObstacleFactory {
    private RockTexture rockTexture = new RockTexture();
    private SpikesTexture spikesTexture = new SpikesTexture();
    private WebTexture webTexture = new WebTexture();
    private PedestalTexture pedestalTexture = new PedestalTexture();
    private DoorTexture doorTexture = new DoorTexture();
    private DoorOpenTexture doorOpenTexture = new DoorOpenTexture();

    public Entity createEntity(String entity, double x, double y) {
        switch (entity) {
            case "rock":
                return this.createRock(x, y);
            case "spikes":
                return this.createSpikes(x, y);
            case "web":
                return this.createWeb(x, y);
            case "pedestal":
                return this.createPedestal(x, y);
            case "door":
                return this.createDoor(x, y);
        }

        return null;
    }

    private Rock createRock(double x, double y) {
        return new Rock(x, y, this.rockTexture);
    }

    private Spikes createSpikes(double x, double y) {
        return new Spikes(x, y, this.spikesTexture);
    }

    private Web createWeb(double x, double y) { return new Web(x, y, this.webTexture); }

    private Pedestal createPedestal(double x, double y) {
        return new Pedestal(x, y, this.pedestalTexture);
    }

    private Door createDoor(double x, double y) {return new Door(x, y, this.doorOpenTexture, this.doorTexture); }
}
