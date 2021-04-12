package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class GameScene extends SceneTool{

    private final static VBox V_ROOT = new VBox();
    private final static HBox root = new HBox(V_ROOT);


    public static void makeScene(Size size){
        Scene scene = new Scene(root);
        SceneList.GAME.setScene(scene);

        V_ROOT.getChildren().addAll(topRowGenesis(), bottomRowGenesis(size));

        V_ROOT.setStyle("-fx-padding: 0 100 100 100");
        V_ROOT.setSpacing(100);

        root.setBackground(new Background(bgFillLightBlue));
        root.setAlignment(Pos.CENTER);
    }

    private static GamePane bottomRowGenesis(Size size){
        // TODO change the resize factor of initiate method
        Grid logicGrid = new Grid(size);
        logicGrid.set_default_walls();
        logicGrid.set_player(1,1);
        logicGrid.set_boxes(logicGrid.col/2, logicGrid.row/2);
        logicGrid.set_flag((logicGrid.col/2) + 1, (logicGrid.row/2) + 1);

        GamePane gamePane = new GamePane(logicGrid);
        gamePane.initiate();

        SceneList.GAME.getScene().addEventHandler(KeyEvent.KEY_PRESSED,new PlayerEvent(logicGrid, gamePane));
        return gamePane;
    }

    private static HBox topRowGenesis(){
        HBox topSide = new HBox();
        //TODO this label will display the current lvl playing
        Label title = new Label("Sokoban");

        title.setFont(Font.font("impact", 50));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = makeToMenuButton();
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

        topSide.setStyle("-fx-padding: 70 50 20 50");
        topSide.setSpacing(50);

        topSide.getChildren().addAll(exitButton, title);
        return topSide;

    }
}
