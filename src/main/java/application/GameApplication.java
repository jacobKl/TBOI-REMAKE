package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GameApplication extends Application {
    private static final String WINDOW_NAME = "The Binding of Isaac - Remake";
    public static final Integer WINDOW_WIDTH = 800;
    public static final Integer WINDOW_HEIGHT = 500;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(GameApplication.WINDOW_WIDTH, GameApplication.WINDOW_HEIGHT);

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root, Color.BLACK);
        primaryStage.setTitle(GameApplication.WINDOW_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();

        GameLoop gameLoop = new GameLoop(canvas.getGraphicsContext2D(), scene);

        gameLoop.start();
    }
}