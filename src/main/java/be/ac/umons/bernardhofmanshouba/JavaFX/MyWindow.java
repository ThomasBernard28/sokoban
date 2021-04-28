package be.ac.umons.bernardhofmanshouba.JavaFX;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.JavaFX.Scenes.*;
import be.ac.umons.bernardhofmanshouba.Save.ProfileList;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MyWindow extends Application
{


    public static void main(String[] args)
    {
        launch(args);
    }


    public void start(Stage theStage){
        theStage.setResizable(false);
        theStage.setTitle("Sokoban");
        SceneTool.setStage(theStage);
        SceneTool.setCurrSize(Size.MEDIUM);
        ProfileList.initiateProfileList();


        final Image imageHead = new Image("images/Head.png");
        theStage.getIcons().add(imageHead);


        LevelGenScene.makeScene();
        //LevelSelectionScene.makeScene();
        GameModeScene.makeScene();
        MenuScene.makeScene();
        ProfileScene.makeScene();
        StatScene.makeScene();


        theStage.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {
            if(GameScene.getCurrProfile() != null) {
                System.out.println(GameScene.getCurrProfile().getProfile().getUsername());
            }else{
                System.out.println("null");
            }
        });
        SceneTool.start();
    }

    public static Grid logicGridGenesis(Size size){
        Grid grid = new Grid(size);
        grid.set_default_walls();
        grid.setPlayer(1,6);
        grid.setBox(size.getCol()/2, size.getRow()/2);
        grid.set_flag((size.getCol()/2)+1, (size.getRow()/2)+1);
        return grid;
    }
}
