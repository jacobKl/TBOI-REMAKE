package entities.Player;

import application.GameApplication;
import entities.Entity;
import entities.SpritesheetPart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player extends Entity {
    private PlayerActivity playerActivity = new PlayerActivity();
    private PlayerInventory playerInventory = new PlayerInventory();
    private PlayerAttributes playerAttributes = new PlayerAttributes();
    private Image spritesheet;
    private PlayerState state = new PlayerActive();

    public Player(double startX, double startY) {
        super(startX, startY, 64, 64);

        this.spritesheet = new Image(getClass().getResource("/isaac_old.png").toExternalForm());
    }

    public void handleInput(KeyEvent keyEvent, boolean isPressed) {
        switch(keyEvent.getCode()) {
            case W -> this.playerActivity.up = isPressed;
            case S -> this.playerActivity.down = isPressed;
            case A -> this.playerActivity.left = isPressed;
            case D -> this.playerActivity.right = isPressed;
            case UP -> this.playerActivity.shootingUp = isPressed;
            case DOWN -> this.playerActivity.shootingDown = isPressed;
            case LEFT -> this.playerActivity.shootingLeft = isPressed;
            case RIGHT -> this.playerActivity.shootingRight = isPressed;
        }
    }

    public void update(Entity[] entities) {
        double dx = this.x;
        double dy = this.y;

        double speed = this.playerAttributes.getSpeed();

        if (this.playerActivity.up) dy -= speed;
        if (this.playerActivity.down) dy += speed;
        if (this.playerActivity.left) dx -= speed;
        if (this.playerActivity.right) dx += speed;

        Entity futurePosition = new Entity(dx + 15, dy + 30, this.width - 30, this.height - 30);

        boolean obstacleCollision = false;

        for (Entity entity : entities) {
            if (futurePosition.intersects(entity)) {
                if (entity.isCollidable())
                    obstacleCollision = true;
                entity.contactWithPlayer(this);
            }
        }

        boolean moveWithinBounds = this.isWithinBounds(futurePosition);

        if (!obstacleCollision && moveWithinBounds) {
            this.x = dx;
            this.y = dy;
        }
    }

    public void render(GraphicsContext gc, double deltaTime) {
        this.state.setPlayerAttributes(this.playerActivity);

        SpritesheetPart[] parts = this.state.getSpritesheetParts(this.x, this.y, deltaTime);

        for (SpritesheetPart part : parts) {
            gc.drawImage(this.spritesheet, part.sourceX, part.sourceY, part.sourceWidth, part.sourceHeight, part.targetX, part.targetY + 20, part.targetWidth, part.targetHeight);
        }
    }

    public boolean isWithinBounds(Entity futurePosition) {
        if (futurePosition.getX() <= 80) return false;
        if (futurePosition.getY() <= 80) return false;
        if (futurePosition.getX() + futurePosition.getWidth() > GameApplication.WINDOW_WIDTH - 80) return false;
        if (futurePosition.getY() + futurePosition.getHeight() > GameApplication.WINDOW_HEIGHT - 80) return false;

        return true;
    }

    public PlayerAttributes getPlayerAttributes() {
        return this.playerAttributes;
    }

    public PlayerInventory getPlayerInventory() {
        return this.playerInventory;
    }

    public void recieveDamage(double number) {
        this.getPlayerAttributes().recieveDamage(number);
    }
}
