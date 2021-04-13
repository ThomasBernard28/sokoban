package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.GamePane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Test extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage theStage){
        theStage.setTitle("MapTest");
        // logic part

        Grid logicGrid = new Grid(Size.SMALL);
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

    public void startl(Stage theStage){
        /*
        GameScene gamePaneGiver = new GameScene(3,3);
        Scene gameScene = gamePaneGiver.rootScene;

        GamePane testPane = new GamePane(new Grid(5, 5));

        GridPane testBis = new GridPane();
        testBis.add(SpriteTile.getTileImg(TileImg.EMPTY), 0, 0);

        Scene test = new Scene(testPane);
        for (int i = 0; i < 5; i++) {
            testPane.setAt(SpriteTile.getTileImg(TileImg.EMPTY), i, 0);
        }


        testPane.initiate();

        theStage.setScene(gameScene);
        theStage.show();*/
    }
}
