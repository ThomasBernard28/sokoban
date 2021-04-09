package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneSwitcher {
    public enum UniqueScene{
        GAME,
        LVL_GEN,
        MENU
    }

    private static Stage WINDOW;

    private static Scene menuScene;
    private static Scene gameScene;
    private static Scene levelGenScene;

    public static void setScenes(Scene _gameScene,
                                 Scene _levelGenScene,
                                 Scene _menuScene){
        gameScene = _gameScene;
        levelGenScene = _levelGenScene;
        menuScene = _menuScene;
    }
    public static void setStage(Stage theStage){
        WINDOW = theStage;
    }

    public static void switchScene(UniqueScene newScene){
        switch (newScene){
            case GAME:
                WINDOW.setScene(gameScene);
                break;
            case LVL_GEN:
                WINDOW.setScene(levelGenScene);
                break;
            case MENU:
                WINDOW.setScene(menuScene);
                break;
            default:
                throw new IllegalStateException("Wrong enum value");
        }
    }
}
