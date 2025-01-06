package entities.Player;

public class PlayerAttributes {
    private double speed = 6;
    private double maxHealth = 3;
    private double currentHealth = 2;
    private double damage = 3;
    private double shootSpeed = 0.5;

    public void update() {
        this.speed = 6;
    }

    public double getSpeed() {
        return this.speed;
    }

    public double getMaxHealth() {
        return this.maxHealth;
    }

    public double getCurrentHealth() {
        return this.currentHealth;
    }

    public double getDamage() {
        return this.damage;
    }

    public void recieveDamage(double number) {
        this.currentHealth -= number;
    }

    public void slowDown(double amount) {
        this.speed -= amount;
    }

    public void addHealth(double amount) { this.currentHealth += amount; }

    public void changeDamage(double amount) {
        this.damage += amount;
    }

    public void changeShootSpeed(double amount) {
        this.shootSpeed += amount; }

    public double getShootSpeed() { return this.shootSpeed; }
}
