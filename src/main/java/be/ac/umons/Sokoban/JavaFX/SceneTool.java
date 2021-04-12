package be.ac.umons.Sokoban.JavaFX;

import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class SceneTool {
    /**
     * This enum contains every scene and allows to switch the current scene
     */
    protected enum SceneList{
        GAME,
        LVL_GEN,
        MENU,
        LVL_SELECTION;

        private Scene scene = null;

        protected void setScene(Scene scene){
            this.scene = scene;
        }

        /**
         * Change the scene of the window by the one contained in the enum
         */
        public void setOnActive(){
            WINDOW.setScene(this.scene);
        }

        protected Scene getScene(){
            return scene;
        }
    }

    protected static Stage WINDOW;

    public static void setStage(Stage theStage){
        WINDOW = theStage;
    }

    /**
     *Close the window
     */
    protected static void quit(){
        WINDOW.close();
    }

    public static void start(){
        WINDOW.show();
    }

    /*
    Here we have some design constant that are used in several scenes
     */

    protected final static Paint lightOrangePaint = Color.valueOf("#FA8132");
    protected final static Paint darkOrangePaint = Color.valueOf("#E86A17");
    protected final static Paint lightBluePaint = Color.valueOf("#1EA7E1");
    protected final static Paint darkBluePaint = Color.valueOf("#1989B8");
    protected final static Paint grayPaint = Color.valueOf("#808080");
    protected final static Paint greenPaint = Color.valueOf("#77D04F");

    protected final static BackgroundFill bgFillLightBlue =
            new BackgroundFill(lightBluePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillDarkOrange =
            new BackgroundFill(darkOrangePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillGray =
            new BackgroundFill(grayPaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillDarkBlue =
            new BackgroundFill(darkBluePaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillGreen =
            new BackgroundFill(greenPaint, new CornerRadii(1), null);

    protected static Button makeToMenuButton(){
        Button exitButton = new Button();
        ImageView exit = SpriteIcon.getIconImg(IconImg.EXIT);

        exitButton.setScaleX(0.5);
        exitButton.setScaleY(0.5);

        exitButton.setGraphic(exit);
        exitButton.setBackground(new Background(bgFillGray));
        exitButton.setStyle("-fx-cursor: hand");

        exitButton.setOnAction(event -> SceneList.MENU.setOnActive());
        return exitButton;
    }
}