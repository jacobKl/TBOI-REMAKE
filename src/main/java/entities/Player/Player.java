package entities.Player;

import entities.SpritesheetPart;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;

public class Player {
    private double x, y;
    private double speed = 3;

    private PlayerActivity playerActivity = new PlayerActivity();
    private Image spritesheet;
    private PlayerState state = new PlayerActive();

    public Player(double startX, double startY) {
        this.x = startX;
        this.y = startY;

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

    public void update() {
        if (this.playerActivity.up) y -= speed;
        if (this.playerActivity.down) y += speed;
        if (this.playerActivity.left) x -= speed;
        if (this.playerActivity.right) x += speed;
    }

    public void render(GraphicsContext gc, double deltaTime) {
        this.state.setPlayerAttributes(this.playerActivity);
        SpritesheetPart[] parts = this.state.getSpritesheetParts(this.x, this.y, deltaTime);

        for (SpritesheetPart part : parts) {
            gc.drawImage(this.spritesheet, part.sourceX, part.sourceY, part.sourceWidth, part.sourceHeight, part.targetX, part.targetY, part.targetWidth, part.targetHeight);
        }
    }
}
