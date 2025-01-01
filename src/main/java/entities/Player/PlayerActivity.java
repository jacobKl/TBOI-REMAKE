package entities.Player;

import application.Vector2D;
import application.VectorUtils;

public class PlayerActivity {
    public boolean up, down, left, right = false;
    public boolean shootingUp, shootingDown, shootingLeft, shootingRight, placingBomb = false;
    public double tearDelay = 0, immuneDelay = 0;

    public boolean isWalking() {
        return this.up || this.down || this.left|| this.right;
    }

    public boolean isShooting() {
        return this.shootingUp || this.shootingDown || this.shootingLeft|| this.shootingRight;
    }

    public void setTearDelay(double tearDelay) {
        this.tearDelay = tearDelay;
    }

    public void setImmuneDelay(double immuneDelay) {
        this.immuneDelay = immuneDelay;
    }

    public void update(double deltaTime) {
        this.tearDelay -= deltaTime;
        this.immuneDelay -= deltaTime;
    }

    public boolean canShoot() {
        if (this.tearDelay <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean canBeHurt() {
        if (this.immuneDelay <= 0) {
            return true;
        } else {
            return false;
        }
    }

    public String getShootingDirection() {
        return this.getString(shootingRight, shootingLeft, shootingDown, shootingUp);
    }
    public String getWalkingDirection() {
        return this.getString(right, left, down, up);
    }

    public Vector2D getShootingVector() {
        return VectorUtils.directionToVector(this.getShootingDirection());
    }

    public Vector2D getWalkingVector() {
        return VectorUtils.directionToVector(this.getWalkingDirection());
    }

    private String getString(boolean right, boolean left, boolean down, boolean up) {
        if (right) {
            return "right";
        }

        if (left) {
            return "left";
        }

        if (down) {
            return "down";
        }

        if (up) {
            return "up";
        }

        return "";
    }
}
