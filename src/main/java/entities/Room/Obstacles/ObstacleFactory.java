package entities.Room.Obstacles;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonObject;
import entities.Entity;
import entities.Room.Obstacles.Rock;
import entities.Room.Obstacles.Spikes;
import entities.Room.Textures.*;
import entities.RoomPartsFactory;

public class ObstacleFactory implements RoomPartsFactory {
    private RockTexture rockTexture = new RockTexture();
    private SpikesTexture spikesTexture = new SpikesTexture();
    private WebTexture webTexture = new WebTexture();
    private PedestalTexture pedestalTexture = new PedestalTexture();
    private DoorTexture doorTexture = new DoorTexture();
    private DoorOpenTexture doorOpenTexture = new DoorOpenTexture();

    public Entity createEntity(String entity, double x, double y, JsonObject additionalAttributes) {
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
                return this.createDoor(x, y, additionalAttributes);
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

    private Door createDoor(double x, double y, JsonObject additionalAttributes) {
        Number toRoom = (Number) additionalAttributes.get("toRoom");
        String orientation = (String) additionalAttributes.get("orientation");

        Integer toRoomId = toRoom != null ? toRoom.intValue() : null;

        return new Door(x, y, this.doorOpenTexture, this.doorTexture, toRoomId, orientation);
    }
}
