package entities.Player;

public class PlayerActivity {
    public boolean up, down, left, right = false;
    public boolean shootingUp, shootingDown, shootingLeft, shootingRight = false;

    public boolean isWalking() {
        return this.up || this.down || this.left|| this.right;
    }

    public boolean isShooting() {
        return this.shootingUp || this.shootingDown || this.shootingLeft || this.shootingRight;
    }

    public String getShootingDirection() {
        return this.getString(shootingRight, shootingLeft, shootingDown, shootingUp);
    }
    public String getWalkingDirection() {
        return this.getString(right, left, down, up);
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
