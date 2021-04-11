package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.Tile;
import javafx.animation.Animation;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Arrays;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage){
        theStage.setTitle("MapTest");
        int size = 5;
        size = size * 3 + 2;
        // logic part

        Grid logicGrid = new Grid(size, size);
        logicGrid.set_default_walls();
        logicGrid.generateRandomWalls();

        // gui part

        // GridPane group that will contain the Grid
        GamePane gamePane = new GamePane(logicGrid);
        // Scene with the game
        Scene gameScene = new Scene(gamePane);
        theStage.setScene(gameScene);
        gamePane.initiate();

        theStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            gamePane.getGrid().resetGrid();
            gamePane.getGrid().set_default_walls();
            gamePane.getGrid().generateRandomWalls();
            gamePane.initiate();
        });
        theStage.show();
    }
}
