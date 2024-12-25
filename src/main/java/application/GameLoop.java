package application;

import entities.Player.Player;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameLoop extends AnimationTimer {
    private static final double TARGET_FPS = 60.0;
    private static final double NANOSECONDS_PER_FRAME = 1_000_000_000.0 / TARGET_FPS;
    private final GraphicsContext graphicsContext;
    private final Scene scene;
    private long lastTime = 0;
    private Player player;

    public GameLoop(GraphicsContext graphicsContext, Scene scene) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;
        this.player = new Player(10, 10);

        this.init();
    }

    private void init() {
        this.scene.setOnKeyPressed(key -> this.player.handleInput(key, true));
        this.scene.setOnKeyReleased(key -> this.player.handleInput(key, false));
    }

    @Override
    public void handle(long now) {
        if (lastTime == 0) {
            lastTime = now;
            return;
        }

        double deltaTime = (now - lastTime) / 1_000_000_000.0;

        this.update();

        this.render(deltaTime);

        lastTime = now;
    }

    private void update() {
        this.player.update();
    }

    private void render(double deltaTime) {
        this.graphicsContext.setFill(Color.WHITE);
        this.graphicsContext.fillRect(0, 0, GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);
        this.graphicsContext.setFill(Color.BLACK);
        this.player.render(this.graphicsContext, deltaTime);
    }
}
