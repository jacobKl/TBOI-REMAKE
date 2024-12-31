package entities.Room.Obstacles;

import entities.Entity;
import entities.Room.Obstacles.Rock;
import entities.Room.Obstacles.Spikes;
import entities.Room.Textures.RockTexture;
import entities.Room.Textures.SpikesTexture;

public class ObstacleFactory {
    private RockTexture rockTexture;
    private SpikesTexture spikesTexture;

    public ObstacleFactory() {
        this.rockTexture = new RockTexture();
        this.spikesTexture = new SpikesTexture();
    }

    public Entity createEntity(String entity, double x, double y) {
        switch (entity) {
            case "rock":
                return this.createRock(x, y);
            case "spikes":
                return this.createSpikes(x, y);
        }

        return null;
    }

    private Rock createRock(double x, double y) {
        return new Rock(x, y, this.rockTexture);
    }

    private Spikes createSpikes(double x, double y) {
        return new Spikes(x, y, this.spikesTexture);
    }
}
