package application;

import GUI.GUI;
import entities.Player.Player;
import entities.Room.Room;
import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;

public class GameLoop extends AnimationTimer {
    private static final double TARGET_FPS = 60.0;
    private final GraphicsContext graphicsContext;
    private final Scene scene;
    private long lastTime = 0;
    private Player player;
    private Room currentRoom;
    private GUI gui;

    public GameLoop(GraphicsContext graphicsContext, Scene scene) {
        this.graphicsContext = graphicsContext;
        this.scene = scene;

        this.player = new Player(200, 200);
        this.currentRoom = new Room();
        this.gui = new GUI();

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
        this.player.update(this.currentRoom.getEntities());
    }

    private void render(double deltaTime) {
        this.currentRoom.render(this.graphicsContext, deltaTime);
        this.player.render(this.graphicsContext, deltaTime);
        this.gui.render(this.graphicsContext, deltaTime, this.player);
    }
}
