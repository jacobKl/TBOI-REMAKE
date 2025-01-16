package application;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import entities.Entity;
import entities.Room.Enemies.EnemyFactory;
import entities.Room.Obstacles.ObstacleFactory;
import entities.Room.Pickups.PickupFactory;
import entities.RoomPartsFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Objects;

public class RoomLoader {
    private JsonArray roomsArray;
    public RoomLoader() {
        try {
            FileReader jsonContent = new FileReader("src/main/resources/rooms.json");
            JsonArray roomsArray = (JsonArray) Jsoner.deserialize(jsonContent);

            this.roomsArray = roomsArray;
        } catch (JsonException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public ArrayList<Entity> loadRoomEntities(Integer index, ArrayList<Integer> defeatedRooms) {
        ArrayList<Entity> entities = new ArrayList<>();
        JsonObject roomObject = (JsonObject) this.roomsArray.get(index);

        this.mapObjectsToFactory(entities, new ObstacleFactory(), (JsonArray) roomObject.get("entities"), defeatedRooms, index);

        if (!defeatedRooms.contains(index)) {
            this.mapObjectsToFactory(entities, new PickupFactory(), (JsonArray) roomObject.get("pickups"), defeatedRooms, index);
            this.mapObjectsToFactory(entities, new EnemyFactory(), (JsonArray) roomObject.get("enemies"), defeatedRooms, index);
        }

        return entities;
    }

    private void mapObjectsToFactory(ArrayList<Entity> entities, RoomPartsFactory factory, JsonArray jsonEntities, ArrayList<Integer> defeatedRooms, Integer index) {
        for (Object entity : jsonEntities) {
            JsonObject single = (JsonObject) entity;

            String type = (String) single.get("type");
            Number xValue = (Number) single.get("x");
            Number yValue = (Number) single.get("y");

            if (Objects.equals(type, "pedestal") && defeatedRooms.contains(index)) continue;

            Double x = xValue != null ? xValue.doubleValue() : null;
            Double y = yValue != null ? yValue.doubleValue() : null;

            if (factory instanceof ObstacleFactory) {
                entities.add(((ObstacleFactory) factory).createEntity(type, x, y, (JsonObject) single.get("additionalAttributes")));
            } else if (factory instanceof PickupFactory) {
                entities.add(((PickupFactory) factory).createEntity(type, x, y));
            } else if (factory instanceof EnemyFactory) {
                entities.add(((EnemyFactory) factory).createEntity(type, x, y));
            }
        }
    }
}
