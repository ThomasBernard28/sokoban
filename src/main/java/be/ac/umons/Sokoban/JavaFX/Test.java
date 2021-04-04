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
        Image sprite = new Image("images/sokoban_tilesheet.png");

        SpecialPane gamePane = new SpecialPane(logicGrid, 64, sprite);
        // Scene with the game
        Scene gameScene = new Scene(gamePane);
        theStage.setScene(gameScene);
        gamePane.initiate();

        theStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            gamePane.lG.resetGrid();
            gamePane.lG.set_default_walls();
            gamePane.lG.generateRandomWalls();
            gamePane.initiate();
        });
        theStage.show();
    }
}
