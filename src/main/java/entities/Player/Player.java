package entities.Player;

import application.Vector2D;
import application.VectorUtils;
import entities.Entity;
import entities.PickupEntity;
import entities.Projectile;
import entities.Room.Room;
import entities.Room.Textures.TearTexture;
import entities.SpritesheetPart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

import java.util.ArrayList;

public class Player extends Entity {
    private PlayerActivity playerActivity = new PlayerActivity();
    private PlayerInventory playerInventory = new PlayerInventory();
    private PlayerAttributes playerAttributes = new PlayerAttributes();
    private Image spritesheet;
    private PlayerState state = new PlayerActive();
    private ArrayList<Projectile> projectiles;
    private TearTexture tearTexture = new TearTexture();

    public Player(double startX, double startY, ArrayList<Projectile> projectiles) {
        super(startX, startY, 64, 64);

        this.projectiles = projectiles;
        this.spritesheet = new Image(getClass().getResource("/isaac_old.png").toExternalForm());
    }

    public void handleInput(KeyEvent keyEvent, boolean isPressed) {
        switch(keyEvent.getCode()) {
            case W -> this.playerActivity.up = isPressed;
            case S -> this.playerActivity.down = isPressed;
            case A -> this.playerActivity.left = isPressed;
            case D -> this.playerActivity.right = isPressed;
        }

        switch(keyEvent.getCode()) {
            case UP -> this.playerActivity.shootingUp = isPressed;
            case DOWN -> this.playerActivity.shootingDown = isPressed;
            case LEFT -> this.playerActivity.shootingLeft = isPressed;
            case RIGHT -> this.playerActivity.shootingRight = isPressed;
        }
    }

    public void update(ArrayList<Entity> entities, double deltaTime) {
        Vector2D movementVector = this.getMovementVelocity(this.getPosition().getX(), this.getPosition().getY());

        Entity futurePosition = new Entity(movementVector.getX() + 15, movementVector.getY() + 30, this.width - 30, this.height - 30);

        boolean obstacleCollision = false;

        for (Entity entity : entities) {
            if (futurePosition.intersects(entity)) {
                if (entity.isCollidable())
                    obstacleCollision = true;

                entity.contactWithPlayer(this);

                if (entity instanceof PickupEntity) {
                    entities.remove(entity);
                }
            }
        }


        boolean moveWithinBounds = Room.isWithinBounds(futurePosition);

        if (!obstacleCollision && moveWithinBounds) {
            this.setX(movementVector.getX());
            this.setY(movementVector.getY());
        }

        this.playerActivity.update(deltaTime);

        if (this.playerActivity.isShooting() && this.playerActivity.canShoot()) {
            this.shoot();
        }

    }

    public void render(GraphicsContext gc, double deltaTime) {
        this.state.setPlayerAttributes(this.playerActivity);

        SpritesheetPart[] parts = this.state.getSpritesheetParts(this.getPosition().getX(), this.getPosition().getY(), deltaTime);

        for (SpritesheetPart part : parts) {
            gc.drawImage(this.spritesheet, part.sourceX, part.sourceY, part.sourceWidth, part.sourceHeight, part.targetX, part.targetY + 20, part.targetWidth, part.targetHeight);
        }
    }

    public PlayerAttributes getPlayerAttributes() {
        return this.playerAttributes;
    }

    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }

    public void receiveDamage(double number) {
        if (this.playerActivity.canBeHurt()) {
            this.getPlayerAttributes().recieveDamage(number);
            this.audioManager.playSound("hurt.wav");

            this.playerActivity.setImmuneDelay(1.0);
        }
    }

    public void shoot() {
        if (this.playerActivity.canShoot()) {
            Vector2D shootingVector = this.playerActivity.getShootingVector();
            Vector2D walkingVector = this.playerActivity.getWalkingVector();

            Vector2D entityCenter = this.getEntityCenter();

            boolean isPerpendicular = (VectorUtils.scalar(shootingVector, walkingVector) == 0 && this.playerActivity.isWalking()) ? true : false;

            Projectile projectile = new Projectile(entityCenter.getX(), entityCenter.getY(), walkingVector, shootingVector, isPerpendicular, this.tearTexture.get());

            this.projectiles.add(projectile);
            this.audioManager.playSound("attack.wav");
            this.playerActivity.setTearDelay(.5);
        }
    }

    public Vector2D getMovementVelocity(double dx, double dy) {
        double speed = this.playerAttributes.getSpeed();

        if (this.playerActivity.up) dy -= speed;
        if (this.playerActivity.down) dy += speed;
        if (this.playerActivity.left) dx -= speed;
        if (this.playerActivity.right) dx += speed;

        return new Vector2D(dx, dy);
    }
}
