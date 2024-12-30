package application;

public class Vector2D {
    private double x, y;

    public Vector2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D add(Vector2D other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }

    public Vector2D multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
        return this;
    }

    public Vector2D copy() {
        return new Vector2D(this.x, this.y);
    }

    public Vector2D normalize() {
        double length = (double) Math.sqrt(x * x + y * y);
        if (length != 0) {
            this.x /= length;
            this.y /= length;
        }
        return this;
    }

    public double getX() {
        return this.x;
    }

    public void setX(double x) { this.x = x; }

    public double getY() {
        return this.y;
    }

    public void setY(double y) { this.y = y; }

    public String toString () {
        return "X: " + this.getX() + " Y: " + this.getY();
    }
}
