package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class GameApplication extends Application {
    private static String WINDOW_NAME = "The Binding of Isaac - remake";

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Canvas canvas = new Canvas(800, 600);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        StackPane root = new StackPane(canvas);
        Scene scene = new Scene(root);
        primaryStage.setTitle(GameApplication.WINDOW_NAME);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}