package be.ac.umons.bernardhofmanshouba.JavaFX;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.JavaFX.Scenes.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void _start(Stage theStage){
        Grid testGrid = new Grid(Size.MEDIUM);
        testGrid.set_default_walls();
        testGrid.generateRandomWalls();


        GamePane gamePane = new GamePane(testGrid);
        gamePane.initiate();

        gamePane.setOnMouseClicked(event -> {
            testGrid.resetGrid();
            testGrid.set_default_walls();
            testGrid.generateRandomWalls();
            gamePane.initiate();
        });

        theStage.setScene(new Scene(gamePane));
        theStage.show();
    }

    public void start(Stage theStage) {
        Grid testGrid = new Grid(Size.LARGE);

        GamePane gamePane = new GamePane(testGrid);

        testGrid.constructMovables(10, 1000);
        gamePane.initiate();

        gamePane.setOnMouseClicked(event -> {
            testGrid.constructMovables(10, 1000);
            gamePane.initiate();
        });
        Scene myScene = new Scene(gamePane);
        theStage.setScene(myScene);
        theStage.show();
    }
}
