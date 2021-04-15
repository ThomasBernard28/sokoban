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

    public void start(Stage theStage){
        // https://gillius.org/blog/2013/02/javafx-window-scaling-on-resize.html
        theStage.setTitle("MapTest");

        GridPane root = new GridPane();

        Grid logicGrid = new Grid(Size.MEDIUM);
        logicGrid.set_default_walls();
        logicGrid.generateRandomWalls();

        GamePane gamePane = new GamePane(logicGrid);
        gamePane.initiate();


        Scene myScene = new Scene(gamePane);

        gamePane.layoutBoundsProperty().addListener(((observable, oldValue, newValue) -> {
             int cellX = (int) newValue.getWidth() / gamePane.col;
             int cellY = (int) newValue.getHeight() / gamePane.row;

             if(cellX != gamePane.cellSize || cellY != gamePane.cellSize){
                 if (cellX <= cellY) {
                     gamePane.initiate();
                     gamePane.cellSize = cellX;
                 } else {
                     gamePane.initiate();
                     gamePane.cellSize = cellY;
                     //myScene.setFill(new Background(new BackgroundFill(Color.valueOf("#1EA7E1"), new CornerRadii(1), null)));
                 }
             }
        }));

        gamePane.setStyle("-fx-border-color: red; -fx-border-width: 5");
        theStage.setScene(myScene);
        theStage.show();
    }
}
