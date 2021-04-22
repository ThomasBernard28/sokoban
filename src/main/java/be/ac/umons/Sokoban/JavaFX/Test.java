package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

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

        testGrid.constructMovables(5, 100);
        gamePane.initiate();

        gamePane.setOnMouseClicked(event -> {
            testGrid.constructMovables(5, 100);
            gamePane.initiate();
        });
        Scene myScene = new Scene(gamePane);
        //myScene.setOnKeyPressed(event -> gamePane.initiate());
        theStage.setScene(myScene);
        theStage.show();
    }
}
