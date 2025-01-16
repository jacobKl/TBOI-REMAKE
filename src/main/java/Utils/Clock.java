package Utils;

public class Clock {
    private double sum;
    private final double threshold;

    public Clock(double threshold) {
        this.sum = 0;
        this.threshold = threshold;
    }

    public boolean update(double deltaTime) {
        this.sum += deltaTime;
        if (this.sum > this.threshold) {
            this.sum = 0;
            return true;
        }
        return false;
    }
}