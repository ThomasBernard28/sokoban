package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
        /*
        // Level Gen scene
        */

        final int sizeX = 16;
        final int sizeY = 9;
        final int SIZE = 32;

        // root
        BorderPane root = new BorderPane();

        // Center
        Grid visualGrid = new Grid(sizeX, sizeY);
        SpecialPane visualPane = new SpecialPane(visualGrid, SIZE, new Image("images/tile_sheet_" + SIZE + ".png"));
        visualPane.initiate();

        // Right
        GridPane rightSide = new GridPane();

        Button boxButton = new Button("Box   ");
        Button flagButton = new Button("Flag  ");
        Button playerButton = new Button("Player");
        Button wallButton = new Button("Wall  ");
        Button eraseButton = new Button("Erase ");

        rightSide.add(playerButton, 1, 1);
        rightSide.add(boxButton,1,2);
        rightSide.add(wallButton,1,3);
        rightSide.add(flagButton, 1, 4);
        rightSide.add(eraseButton,1, 5);

        rightSide.setVgap(20);
        rightSide.setMinSize(80, 0);
        BackgroundFill rightSideFill = new BackgroundFill(Color.CRIMSON, new CornerRadii(1), null);
        rightSide.setBackground(new Background(rightSideFill));
        rightSide.setAlignment(Pos.CENTER);

        //Bottom
        Pane bottomSide = new Pane();

        Button generate = new Button("Generate");
        Button reset = new Button("Reset");
        Button validate = new Button("Validate");
        Button play = new Button("try");

        ImageView btnImage = new ImageView(new Image("images/Head.png"));
        play.setGraphic(btnImage);

        setAt(generate, 50, 10, bottomSide);
        setAt(reset, 50, 50, bottomSide);
        setAt(validate, 400, 10, bottomSide);
        setAt(play, 400, 50, bottomSide);

        //Left
        Rectangle leftSide = new Rectangle(0, 0, 20, visualGrid.getGrid().length * SIZE);
        leftSide.setFill(Color.BLUE);
        //Top
        Text title = new Text("    Level Build Tool");
        title.setFont(new Font("arial", 20));

        HBox topSide = new HBox(title);

        BackgroundFill bgFill = new BackgroundFill(Color.CHARTREUSE, new CornerRadii(1), null);
        topSide.setBackground(new Background(bgFill));
        // Assembly
        root.setCenter(visualPane);
        root.setTop(topSide);
        root.setLeft(leftSide);
        root.setRight(rightSide);
        root.setBottom(bottomSide);

        Scene LevelGenScene = new Scene(root);

        /*
        // Game scene
        */
        Scene gameScene = makeGameScene(4, 4, 48);

        // Stage part
        theStage.setTitle("Sokoban");
        theStage.getIcons().add(new Image("images/Head.png"));

        theStage.setScene(LevelGenScene);
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
        SpecialPane gamePane = new SpecialPane(logicGrid, size, new Image("images/tile_sheet_" + size +".png"));
        gamePane.initiate();

        Scene gameScene = new Scene(gamePane);
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(logicGrid, gamePane));

        return gameScene;
    }

    public static void setAt(Node child, int setX, int setY, Pane thePane){
        thePane.getChildren().add(child);
        child.relocate(setX, setY);
    }
}