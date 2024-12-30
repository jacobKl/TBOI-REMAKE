package entities.Player;

public class PlayerAttributes {
    private double speed = 3;
    private double maxHealth = 3;
    private double currentHealth = 3;

    public double getSpeed() {
        return this.speed;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public void recieveDamage(double number) {
        this.currentHealth -= number;
    }
}
