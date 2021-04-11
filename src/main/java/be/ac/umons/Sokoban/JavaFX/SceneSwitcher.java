package be.ac.umons.Sokoban.JavaFX;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class SceneSwitcher {
    public enum UniqueScene{
        GAME,
        LVL_GEN,
        MENU,
        LVL_SELECTION
    }

    protected static Stage WINDOW;

    private static Scene menuScene;
    private static Scene gameScene;
    private static Scene levelGenScene;
    private static Scene lvlSelectionScene;

    public static void setScenes(Scene _gameScene,
                                 Scene _levelGenScene,
                                 Scene _menuScene,
                                 Scene _lvlSelectionScene){
        gameScene = _gameScene;
        levelGenScene = _levelGenScene;
        menuScene = _menuScene;
        lvlSelectionScene = _lvlSelectionScene;
        WINDOW.setRenderScaleX(0.5);
        WINDOW.setRenderScaleY(0.5);
        WINDOW.addEventHandler(MouseEvent.MOUSE_CLICKED, event -> printWindowSize());
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
            case LVL_SELECTION:
                WINDOW.setScene(lvlSelectionScene);
                break;
            default:
                throw new IllegalStateException("Wrong enum value");
        }
    }

    public static void printWindowSize(){
        System.out.println("win size: " + WINDOW.getWidth() + "," + WINDOW.getHeight());
    }

    public static void quit(){
        WINDOW.close();
    }
}
