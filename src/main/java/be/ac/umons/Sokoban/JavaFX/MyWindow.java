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
        theStage.setTitle("Sokoban");
        // GridPane group that will contain the Grid
        GridPane root = visualGridGenesis(size);
        // Scene with the game
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);
        /*
         * // works when the only event handler
        root.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("print");
            }
        }); */
        // need an button with and event handler to work
        root.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("grid");
            }
        });

        //loading images
        Image mario = new Image("images/player.png");
        Image box = new Image("images/box.png");
        Image flag = new Image("images/flag.png");
        Image wall = new Image("images/wall.png");
        Image bg = new Image("images/bg_40.png");

        // logic Grid init
        Grid gameGrid = logicGridGenesis(size);

        // creating visuals
        renderer(root, gameGrid, mario, box, flag, wall, bg);

        theStage.show();
    }
    public void renderer(GridPane GUIGrid, Grid logicGrid, Image player, Image box, Image flag, Image wall, Image bg){
        for (int i = 0; i < logicGrid.row; i++){
            for (int j = 0; j < logicGrid.col; j++){
                if(logicGrid.grid[i][j].isBox() || logicGrid.grid[i][j].isFlaggedBox()){
                    GUIGrid.add(new ImageView(box), j, i);
                }
                if(logicGrid.grid[i][j].isPlayer() || logicGrid.grid[i][j].isFlaggedPlayer()){
                    GUIGrid.add(new ImageView(player), j, i);
                }
                if(logicGrid.grid[i][j].isWall()){
                    GUIGrid.add(new ImageView(wall), j, i);
                }
                if(logicGrid.grid[i][j].isFlag()){
                    GUIGrid.add(new ImageView(flag), j, i);
                }
                if(logicGrid.grid[i][j].isEmpty()){
                        GUIGrid.add(new ImageView(bg), j, i);
                }
            }
        }
        /*
         * // required for the key event on grid pane
        Button button = new Button("Print");
        button.setOnAction((ActionEvent event) -> {
            System.out.println("button");
        });
        GUIGrid.add(button, 9, 9);*/
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