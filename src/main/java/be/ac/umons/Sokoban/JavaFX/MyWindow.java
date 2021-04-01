package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
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
        int size = 8;
        // logic part

        Grid gameGrid = logicGridGenesis(size);

        // gui part

        Resources resources = new Resources();
        resources.loadImages();
        theStage.setTitle("Sokoban");
        // GridPane group that will contain the Grid
        GridPane root = visualGridGenesis(size);
        // Scene with the game
        Scene gameScene = new Scene(root);
        theStage.setScene(gameScene);
        renderer(root, gameGrid, resources);
        theStage.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(gameGrid, root, resources));

        theStage.show();
    }

    public void renderer(GridPane GUIGrid, Grid logicGrid, Resources re){
        for (int i = 0; i < logicGrid.row; i++){
            for (int j = 0; j < logicGrid.col; j++){
                if(logicGrid.grid[i][j].isBox() || logicGrid.grid[i][j].isFlaggedBox()){
                    GUIGrid.add(new ImageView(re.getBox()), j, i);
                }
                if(logicGrid.grid[i][j].isPlayer() || logicGrid.grid[i][j].isFlaggedPlayer()){
                    GUIGrid.add(new ImageView(re.getPlayer()), j, i);
                }
                if(logicGrid.grid[i][j].isWall()){
                    GUIGrid.add(new ImageView(re.getWall()), j, i);
                }
                if(logicGrid.grid[i][j].isFlag()){
                    GUIGrid.add(new ImageView(re.getFlag()), j, i);
                }
                if(logicGrid.grid[i][j].isEmpty()){
                        GUIGrid.add(new ImageView(re.getBg()), j, i);
                }
            }
        }
    }

    public GridPane visualGridGenesis(int size){
        GridPane gridpane = new GridPane();
        for (int i = 0; i < size; i++) {
            ColumnConstraints column = new ColumnConstraints(40);
            RowConstraints row = new RowConstraints(40);
            gridpane.getRowConstraints().add(row);
            gridpane.getColumnConstraints().add(column);
        }
        return gridpane;
    }

    public Grid logicGridGenesis(int size){
        Grid grid = new Grid(size, size);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
        return grid;
    }
}