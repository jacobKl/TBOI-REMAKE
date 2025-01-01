package entities.Player;

import entities.SpritesheetPart;

public class PlayerActive implements PlayerState {
    private int bodyFrame = 0;
    private int headFrame = 0;
    private double bodySum = 0;
    private double headSum = .25;
    private Integer headColumn = 0;
    private Integer bodyRow = 1;
    private boolean verticalFlip = false;
    private PlayerActivity activity;

    public void setPlayerAttributes(PlayerActivity activity) {
        this.activity = activity;
    }

    @Override
    public SpritesheetPart[] getSpritesheetParts(double x, double y, double deltaTime) {
        this.clock(deltaTime);

        SpritesheetPart[] parts = new SpritesheetPart[2];

        this.setSheets();

        parts[1] = new SpritesheetPart((this.headColumn + this.headFrame) * 32, 0, 32, 32, x, y - 45, 90, 90);

        if (this.verticalFlip) {
            parts[0] = new SpritesheetPart(this.bodyFrame * 32, this.bodyRow * 32, 32, 32, x + 90, y, -90, 90);
        } else {
            parts[0] = new SpritesheetPart(this.bodyFrame * 32, this.bodyRow * 32, 32, 32, x, y, 90, 90);
        }

        return parts;
    }

    private void clock(double deltaTime) {
        if (!this.activity.isWalking()) {
            this.bodyFrame = 0;
        }

        if (!this.activity.isShooting()) {
            this.headFrame = 0;
        }

        if (this.bodySum > 0.1) {
            if (this.activity.isWalking())
                this.bodyFrame += 1;

            if (this.bodyFrame == 9) {
                this.bodyFrame = 0;
            }

            this.bodySum = 0;
        } else {
            this.bodySum += deltaTime;
        }

        if (this.headSum > .25) {
            if (this.activity.isShooting()) {
                this.headFrame = this.headFrame + 1;
                this.headFrame %= 2;
            }

            this.headSum = 0;
        } else {
            this.headSum += deltaTime;
        }

    }

    private void setSheets() {
        switch (this.activity.getWalkingDirection()) {
            case "right":
                this.bodyRow = 2;
                this.headColumn = 2;
                this.verticalFlip = false;
                break;
            case "left":
                this.bodyRow = 2;
                this.headColumn = 6;
                this.verticalFlip = true;
                break;
            case "down":
                this.bodyRow = 1;
                this.headColumn = 0;
                this.verticalFlip = false;
                break;
            case "up":
                this.bodyRow = 1;
                this.headColumn = 4;
                this.verticalFlip = false;
                break;
            default:
                this.bodyRow = 1;
                this.headColumn = 0;
                break;
        }

        if (this.activity.isShooting()) {
            switch (this.activity.getShootingDirection()) {
                case "right":
                    this.headColumn = 2;
                    break;
                case "left":
                    this.headColumn = 6;
                    break;
                case "down":
                    this.headColumn = 0;
                    break;
                case "up":
                    this.headColumn = 4;
                    break;
            }
        }
    }
}
