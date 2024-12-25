package entities;

public class SpritesheetPart {
    public double sourceX, sourceY, sourceWidth, sourceHeight, targetX, targetY, targetWidth, targetHeight;

    public SpritesheetPart(double sourceX, double sourceY, double sourceWidth, double sourceHeight, double targetX, double targetY, double targetWidth, double targetHeight) {
        this.sourceX = sourceX;
        this.sourceY = sourceY;
        this.sourceWidth = sourceWidth;
        this.sourceHeight = sourceHeight;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetWidth = targetWidth;
        this.targetHeight = targetHeight;
    }
}
