package application;

public class VectorUtils {
    public static Vector2D directionToVector(String direction) {
        if (direction == "left") {
            return new Vector2D(-1, 0);
        } else if (direction == "right") {
            return new Vector2D(1, 0);
        } else if (direction == "up") {
            return new Vector2D(0, -1);
        } else if (direction == "down") {
            return new Vector2D(0, 1);
        }

        return new Vector2D(0, 0);
    }

    public static Integer scalar(Vector2D first, Vector2D second) {
        return (int) (first.getX() * second.getX() + first.getY() * second.getY());
    }
}
