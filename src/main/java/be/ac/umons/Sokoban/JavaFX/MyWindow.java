package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class MyWindow extends Application
{

    private static final int COLUMNS  =   3;
    private static final int COUNT    =  8;

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
        theStage.setTitle("Sokoban");
        theStage.getIcons().add(new Image("images/Head.png"));
        int size = 8;
        // logic part

        Grid logicGrid = logicGridGenesis(size);

        // gui part

        // GridPane group that will contain the Grid
        Image sprite = new Image("images/tile_sheet_64.png");

        SpecialPane gamePane = new SpecialPane(logicGrid, 64, sprite);
        // Scene with the game
        Scene gameScene = new Scene(gamePane);
        theStage.setScene(gameScene);
        gamePane.initiate();
        // part with animation


        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(logicGrid, gamePane));

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
}