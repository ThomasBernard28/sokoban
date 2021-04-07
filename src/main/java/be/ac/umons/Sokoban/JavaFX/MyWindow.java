package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class MyWindow extends Application
{

    public static void main(String[] args)
    {
        launch(args);
    }

    //Method to create an image and ;oad it with absolute path
    public static Image createImage(Object context, String resourceName)
    {
        URL _url = context.getClass().getResource(resourceName);
        return new Image(_url.toExternalForm());
    }

    public void start(Stage theStage){
        final Image imageHead = new Image("images/Head.png");

        BorderPane root = LevelGenScene.genesis();

        Scene levelGenScene = new Scene(root);

        Scene gameScene = makeGameScene(4, 4, 64);

        LevelGenScene paneGiver = new LevelGenScene(theStage);
        Scene test = new Scene(paneGiver.root);


        theStage.setTitle("Sokoban");
        theStage.getIcons().add(imageHead);

        SceneSwitcher.setStage(theStage);
        SceneSwitcher.setScenes(gameScene, levelGenScene);

        theStage.setScene(test);
        theStage.show();
    }

    public static Grid logicGridGenesis(int size){
        Grid grid = new Grid(size, size);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
        return grid;
    }

    public static Scene makeGameScene(int patRow, int patCol, int size){
        Grid logicGrid = logicGridGenesis(patRow * 3 + 2);
        SpecialPane gamePane = new SpecialPane(logicGrid);
        gamePane.initiate();

        Scene gameScene = new Scene(gamePane);
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(logicGrid, gamePane));

        return gameScene;
    }
}

enum CellSize{
    SMALL,
    MEDIUM,
    LARGE
}