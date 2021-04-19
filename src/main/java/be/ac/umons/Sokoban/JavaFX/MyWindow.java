package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.*;
import javafx.application.Application;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.awt.*;
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
        theStage.setResizable(false);
        theStage.setTitle("Sokoban");
        SceneTool.setStage(theStage);
        SceneTool.setCurrSize(Size.MEDIUM);


        final Image imageHead = new Image("images/Head.png");
        theStage.getIcons().add(imageHead);

        LevelGenScene.makeScene();
        LevelSelectionScene.makeScene();
        PlayMenuScene.makeScene();
        MenuScene.makeScene();
        ProfileScene.makeScene();

        theStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(GameScene.getCurrProfile() != null) {
                System.out.println(GameScene.getCurrProfile().getUsername());
            }else{
                System.out.println("null");
            }
        });
        SceneTool.start();
    }

    public static Grid logicGridGenesis(Size size){
        Grid grid = new Grid(size);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(size.getCol()/2, size.getRow()/2);
        grid.set_flag((size.getCol()/2)+1, (size.getRow()/2)+1);
        return grid;
    }
}

