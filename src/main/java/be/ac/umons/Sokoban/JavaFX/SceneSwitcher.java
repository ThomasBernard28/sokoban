package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    private static Stage WINDOW;
    private static Scene gameScene;
    private static Scene levelGenScene;

    public static void setScenes(Scene _gameScene, Scene _levelGenScene){
        gameScene = _gameScene;
        levelGenScene = _levelGenScene;
    }
    public static void setStage(Stage theStage){
        WINDOW = theStage;
    }
    public static void switchScene(Scene newScene){
        WINDOW.setScene(newScene);
    }
}
