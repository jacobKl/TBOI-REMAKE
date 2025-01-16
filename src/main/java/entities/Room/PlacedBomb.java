package entities.Room;

import Utils.Clock;
import Utils.OscillatingValue;
import entities.Entity;
import entities.PickupEntity;
import entities.Player.Player;
import entities.Room.Enemies.Enemy;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Iterator;

public class PlacedBomb extends Entity {
    private Image bombImage = new Image(getClass().getResource("/pickup_bomb.png").toExternalForm());
    private Clock bombClock = new Clock(2);
    private OscillatingValue size = new OscillatingValue(1, 1, 0.9, 1.3);
    public PlacedBomb(double x, double y) {
        super(x, y, 50, 50);

        this.collidable = false;
    }

    public void update(double deltaTime, Player player, ArrayList<Entity> entities) {
        if(this.bombClock.update(deltaTime)) {
            this.audioManager.playSound("bomb.mp3");

            entities.remove(this);
            this.removeEntitiesWithinRadius(entities, 150);
        }
    }

    public void render(GraphicsContext gc, double deltaTime) {
        double value = this.size.update();
        double width = this.width * value;
        double height = this.height * value;

        gc.drawImage(this.bombImage, this.getX() - (width / 2), this.getY() - (height / 2), width, height);
    }

    public void removeEntitiesWithinRadius(ArrayList<Entity> entities, double radius) {
        Iterator<Entity> iterator = entities.iterator();
        while (iterator.hasNext()) {
            Entity entity = iterator.next();
            double distance = calculateDistance(this, entity);
            if (distance <= radius) {
                if (entity instanceof PickupEntity) {

                } else if (entity instanceof Enemy) {
                    boolean killed = ((Enemy) entity).receiveDamage(50);

                    if (killed) {
                        entities.remove(entity);
                    }
                } else {
                    iterator.remove();
                }
            }
        }
    }

    public static double calculateDistance(Entity bomb, Entity entity) {
        double dx = entity.getX() - bomb.getX();
        double dy = entity.getY() - bomb.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }
}
