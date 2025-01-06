package entities.Room.Obstacles;

import application.GameApplication;
import com.github.cliftonlabs.json_simple.JsonObject;
import entities.Entity;
import entities.Room.Obstacles.Pedestal.Pedestal;
import entities.Room.Textures.*;
import entities.RoomPartsFactory;

import java.util.Objects;

public class ObstacleFactory implements RoomPartsFactory {
    private RockTexture rockTexture = new RockTexture();
    private SpikesTexture spikesTexture = new SpikesTexture();
    private WebTexture webTexture = new WebTexture();
    private PedestalTexture pedestalTexture = new PedestalTexture();
    private DoorTexture doorTexture = new DoorTexture();
    private DoorOpenTexture doorOpenTexture = new DoorOpenTexture();
    private TreasureRoomDoorOpenTexture treasureRoomDoorOpenTexture = new TreasureRoomDoorOpenTexture();
    private TreasureRoomDoorTexture treasureRoomDoorTexture = new TreasureRoomDoorTexture();
    private MushroomTexture mushroomTexture = new MushroomTexture();

    private VaseTexture vaseTexture = new VaseTexture();

    public Entity createEntity(String entity, double x, double y, JsonObject additionalAttributes) {
        double startX = GameApplication.GRID_BUFFER + x * GameApplication.GRID_WIDTH;
        double startY = GameApplication.GRID_BUFFER + y * GameApplication.GRID_WIDTH;

        switch (entity) {
            case "rock":
                return this.createRock(startX, startY);
            case "spikes":
                return this.createSpikes(startX, startY);
            case "web":
                return this.createWeb(startX, startY);
            case "pedestal":
                return this.createPedestal(startX, startY, additionalAttributes);
            case "door":
                return this.createDoor(x, y, additionalAttributes);
            case "mushroom":
                return this.createMushroom(startX, startY);
            case "vase":
                return this.createVase(startX, startY);
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

    private Pedestal createPedestal(double x, double y, JsonObject additionalAttributes) {
        String item = (String) additionalAttributes.get("item");

        return new Pedestal(x, y, this.pedestalTexture, item);
    }

    private Door createDoor(double x, double y, JsonObject additionalAttributes) {
        Number toRoom = (Number) additionalAttributes.get("toRoom");
        String orientation = (String) additionalAttributes.get("orientation");
        String roomType = (String) additionalAttributes.get("roomType");
        Integer toRoomId = toRoom != null ? toRoom.intValue() : null;

        if (Objects.equals(roomType, "treasure")) {
            return new Door(x, y, this.treasureRoomDoorOpenTexture, this.treasureRoomDoorTexture, toRoomId, orientation);
        } else {
            return new Door(x, y, this.doorOpenTexture, this.doorTexture, toRoomId, orientation);
        }
    }

    private Mushroom createMushroom(double x, double y) {
        return new Mushroom(x, y, this.mushroomTexture);
    }

    private Vase createVase(double x, double y) {
        return new Vase(x,y, this.vaseTexture);
    }
}
