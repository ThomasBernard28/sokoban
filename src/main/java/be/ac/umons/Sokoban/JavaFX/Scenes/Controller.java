package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Scenes.LevelGenScene;
import be.ac.umons.Sokoban.JavaFX.Scenes.ProfileScene;
import be.ac.umons.Sokoban.JavaFX.Scenes.SceneTool;

import java.awt.*;

public class Controller {

    public void playButton(){
        ProfileScene.makeScene();
        SceneTool.SceneList.PROFILE.setOnActive();

    }

    public void levelGenButton(){
        LevelGenScene.resetScene();
        SceneTool.SceneList.LVL_GEN.setOnActive();
    }

    public void quitButton(){
        SceneTool.quit();
    }

}
