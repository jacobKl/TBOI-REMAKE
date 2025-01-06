package Utils;

public class OscillatingValue {
    private double oscillating;
    private double direction;
    private double from;
    private double to;
    private double step;

    public OscillatingValue(double initialOscillating, double initialDirection, double from, double to) {
        this.oscillating = initialOscillating;
        this.direction = initialDirection;
        this.from = from;
        this.to = to;
        this.step = 0.02;
    }

    public OscillatingValue(double initialOscillating, double initialDirection, double from, double to, double step) {
        this.oscillating = initialOscillating;
        this.direction = initialDirection;
        this.from = from;
        this.to = to;
        this.step = step;
    }

    public double update() {
        this.oscillating += this.direction * this.step;
        if (this.oscillating > this.to || this.oscillating < this.from) {
            this.direction = -this.direction;
        }
        return this.oscillating;
    }
}