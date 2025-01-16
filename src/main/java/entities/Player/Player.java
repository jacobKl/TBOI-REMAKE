package entities.Player;

import application.Vector2D;
import application.VectorUtils;
import entities.Entity;
import entities.PickupEntity;
import entities.Projectile;
import entities.Room.PlacedBomb;
import entities.Room.Room;
import entities.Room.Textures.TearShadowTexture;
import entities.Room.Textures.TearTexture;
import entities.SpritesheetPart;
import javafx.geometry.BoundingBox;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class Player extends Entity {
    private PlayerActivity playerActivity = new PlayerActivity();
    private PlayerInventory playerInventory = new PlayerInventory();
    private PlayerAttributes playerAttributes = new PlayerAttributes();
    private Image spritesheet;
    private PlayerState state = new PlayerActive();
    private ArrayList<Projectile> projectiles;
    private TearTexture tearTexture = new TearTexture();
    private TearShadowTexture tearShadowTexture = new TearShadowTexture();

    private boolean bombPlaced = false;

    public Player(double startX, double startY, ArrayList<Projectile> projectiles) {
        super(startX, startY, 90, 90);

        this.projectiles = projectiles;
        this.spritesheet = new Image(getClass().getResource("/isaac_old.png").toExternalForm());
    }

    public void handleInput(KeyEvent keyEvent, boolean isPressed) {
        switch(keyEvent.getCode()) {
            case W -> this.playerActivity.up = isPressed;
            case S -> this.playerActivity.down = isPressed;
            case A -> this.playerActivity.left = isPressed;
            case D -> this.playerActivity.right = isPressed;
            case E -> this.playerActivity.placingBomb = isPressed;
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

        this.playerAttributes.update();

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

        if (this.playerActivity.placingBomb && this.playerInventory.getBombs() > 0) {
            this.placeBomb(entities);
        }
    }

    public void render(GraphicsContext gc, double deltaTime) {
        this.state.setPlayerAttributes(this.playerActivity);

        if (this.playerActivity.isHurt()) {
            gc.drawImage(this.spritesheet, 0, 128, 32, 32, this.getX(), this.getY(), this.getWidth(), this.getHeight());
        } else {
            SpritesheetPart[] parts = this.state.getSpritesheetParts(this.getPosition().getX(), this.getPosition().getY(), deltaTime);

            for (SpritesheetPart part : parts) {
                gc.drawImage(this.spritesheet, part.sourceX, part.sourceY, part.sourceWidth, part.sourceHeight, part.targetX, part.targetY + 20, part.targetWidth, part.targetHeight);
            }
        }

        this.playerInventory.renderItems(gc, this);
    }

    @Override
    public Bounds getBounds() {
        return new BoundingBox(this.getX() + 10, this.getY() + 10, this.width - 20, this.height - 20);
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
        if (!this.playerActivity.canShoot())
            return;

        Vector2D shootingVector = this.playerActivity.getShootingVector();
        Vector2D walkingVector = this.playerActivity.getWalkingVector();

        Vector2D entityCenter = this.getEntityCenter();

        boolean isPerpendicular = VectorUtils.scalar(shootingVector, walkingVector) == 0 && this.playerActivity.isWalking();

        Projectile projectile = new Projectile(entityCenter.getX(), entityCenter.getY(), walkingVector, shootingVector, isPerpendicular, this.tearTexture.get(), this.tearShadowTexture.get());

        this.projectiles.add(projectile);
        this.audioManager.playSound("attack.wav");

        this.playerActivity.setTearDelay(this.playerAttributes.getShootSpeed());
    }

    public Vector2D getMovementVelocity(double dx, double dy) {
        double speed = this.playerAttributes.getSpeed();

        if (this.playerActivity.up) dy -= speed;
        if (this.playerActivity.down) dy += speed;
        if (this.playerActivity.left) dx -= speed;
        if (this.playerActivity.right) dx += speed;

        return new Vector2D(dx, dy);
    }

    private void placeBomb(ArrayList<Entity> entities) {
        if (this.playerInventory.getBombs() > 0 && !this.bombPlaced) {
            this.playerInventory.addBombs(-1);

            Vector2D centerPosition = this.getEntityCenter();

            entities.add(new PlacedBomb(centerPosition.getX(), centerPosition.getY()));

            this.bombPlaced = true;
            CompletableFuture.delayedExecutor(2, TimeUnit.SECONDS).execute(() -> {
                this.bombPlaced = false;
            });
        }
    }
}
