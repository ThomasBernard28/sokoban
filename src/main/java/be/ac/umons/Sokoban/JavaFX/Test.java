package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.GamePane;
import be.ac.umons.Sokoban.JavaFX.Scenes.SceneTool;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteTile;
import be.ac.umons.Sokoban.JavaFX.Sprite.TileImg;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicInteger;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void _start(Stage theStage){
        Grid testGrid = new Grid(Size.MEDIUM);

        GamePane gamePane = new GamePane(testGrid);

        testGrid.set_default_walls();
        testGrid.generateRandomWalls();
        testGrid.placeRandomBox(5);
        gamePane.initiate();
        testGrid.suitableNextBoxX(gamePane);

        gamePane.setOnMouseClicked(event -> {
            testGrid.resetGrid();
            testGrid.set_default_walls();
            testGrid.generateRandomWalls();
            testGrid.placeRandomBox(5);
            gamePane.initiate();
            testGrid.suitableNextBoxX(gamePane);
        });

        theStage.setScene(new Scene(gamePane));
        theStage.show();
    }

    public void start(Stage theStage) {
        Grid testGrid = new Grid(Size.MEDIUM);

        GamePane gamePane = new GamePane(testGrid);

        testGrid.set_default_walls();
        testGrid.generateRandomWalls();
        testGrid.constructMovables(5, 100);
        gamePane.initiate();

        gamePane.setOnMouseClicked(event -> {
            testGrid.resetGrid();
            testGrid.set_default_walls();
            testGrid.generateRandomWalls();
            testGrid.constructMovables(5, 100);
            gamePane.initiate();
        });
        Scene myScene = new Scene(gamePane);
        //myScene.setOnKeyPressed(event -> gamePane.initiate());
        theStage.setScene(myScene);
        theStage.show();
    }
}
