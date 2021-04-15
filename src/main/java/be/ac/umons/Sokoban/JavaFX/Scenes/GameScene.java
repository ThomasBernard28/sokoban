package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Event.PlayerEvent;
import be.ac.umons.Sokoban.JavaFX.Size;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;

public class GameScene extends SceneTool {

    public static void makeScene(){
        VBox V_ROOT = new VBox();
        HBox root = new HBox(V_ROOT);

        Scene scene = new Scene(root);
        SceneList.GAME.setScene(scene);

        V_ROOT.getChildren().addAll(topRowGenesis(), bottomRowGenesis(currSize));

        V_ROOT.setStyle("-fx-padding: 0 100 100 100");
        V_ROOT.setSpacing(100);

        root.setBackground(new Background(bgFillLightBlue));
        root.setAlignment(Pos.CENTER);

        //root.setStyle("-fx-border-width: 5;-fx-border-color: red");



    }

    private static GamePane bottomRowGenesis(Size size){
        // TODO change the resize factor of initiate method
        Grid logicGrid = new Grid(size);
        logicGrid.set_default_walls();
        logicGrid.set_player(1,1);
        logicGrid.set_boxes(logicGrid.getSize().getCol()/2, logicGrid.getSize().getRow()/2);
        logicGrid.set_flag((logicGrid.getSize().getCol()/2) + 1, (logicGrid.getSize().getRow()/2) + 1);

        Grid test = null;
        try{
            test = Load.loadFile(Path.LVL,"level1_0");
        }catch (IOException e){
            e.printStackTrace();
        }


        GamePane gamePane = new GamePane(test);
        gamePane.initiate();

        SceneList.GAME.getScene().addEventHandler(KeyEvent.KEY_PRESSED,new PlayerEvent(gamePane));
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
    public static GamePane createGamePane(Grid gameFile){
        GamePane gamePane = new GamePane(gameFile);
        gamePane.initiate();

        return gamePane;
    }
}
