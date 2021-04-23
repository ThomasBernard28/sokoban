package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Sprite.*;
import be.ac.umons.Sokoban.JavaFX.Size;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;

public class SceneTool {

    /**
     * This enum contains every scene and allows to switch the current scene
     */
    public enum SceneList{
        GAME,
        LVL_GEN,
        MENU,
        PLAY_MENU,
        LVL_SELECTION,
        PROFILE;

        private Scene scene = null;

        protected void setScene(Scene scene){
            this.scene = scene;
        }

        /**
         * Change the scene of the window by the one contained in the enum
         */
        public void setOnActive(){
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();

            WINDOW.setX(bounds.getMinX());
            WINDOW.setY(bounds.getMinX());
            WINDOW.setWidth(screenSize.getWidth());
            WINDOW.setHeight(screenSize.getHeight());
            WINDOW.setResizable(true);
            WINDOW.setScene(this.scene);

            //WINDOW.setMaximized(true);
            //WINDOW.setFullScreen(true);

        }

        protected Scene getScene(){
            return scene;
        }
    }

    protected static Stage WINDOW;
    private  static double windowResizeFactor;

    public static void setStage(Stage theStage){
        WINDOW = theStage;
        Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        windowResizeFactor = screenSize.height/1080.0;

    }

    public static double getWindowResizeFactor() {
        return windowResizeFactor;
    }

    /**
     *Close the window
     */
    protected static void quit(){
        WINDOW.close();
    }

    public static void start(){
        SceneTool.SceneList.MENU.setOnActive();
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
    protected final static Paint redPaint = Color.valueOf("red");
    protected final static Paint yellowPaint = Color.valueOf("#FFCD00");

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
    protected final static BackgroundFill bgFillRed =
            new BackgroundFill(redPaint, new CornerRadii(1), null);
    protected final static BackgroundFill bgFillYellow =
            new BackgroundFill(yellowPaint, new CornerRadii(1), null);

    protected static Button makeExitButton(){
        Button exitButton = new Button();
        ImageView exit = SpriteIcon.getIconImg(IconImg.EXIT);

        exitButton.setScaleX(0.5);
        exitButton.setScaleY(0.5);

        exitButton.setGraphic(exit);
        exitButton.setBackground(new Background(bgFillGray));
        exitButton.setStyle("-fx-cursor: hand;");

        return exitButton;
    }



    protected static Size currSize;

    public static void setCurrSize(Size newSize){
        currSize = newSize;
        SpriteTile.setGameSheet(newSize);
    }

    /**
     * This class allows to construct a button and directly apply a style onto it to lighten UI code
     */
    protected static class StyleBtn extends Button{

        public final static String UnpressedCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";
        public final static String PressedCSS ="-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #E86A17;-fx-cursor: hand";
        public final static String classicCSS = "-fx-padding: 10 10 10 10;-fx-cursor: hand";

        /**
         *
         * @param type Tile Img that'll be applied on the button
         * @param CSS CSS style that'll be applied on the button if not desired value must be set to null
         * @param bg Bg color that'll be applied on the button if not desired value must be set to null
         * @param txt txt that'll be applied on the button if not desired value must be set to null
         */
        protected StyleBtn(TileImg type, String CSS, BackgroundFill bg, String txt){
            super();
            this.setGraphic(SpriteTile.getTileImg(type));
            applyStyle(CSS, bg, txt);
        }

        /**
         * Same as other constructors but with an Icon img
         */
        protected StyleBtn(IconImg type, String CSS, BackgroundFill bg, String txt){
            super();
            this.setGraphic(SpriteIcon.getIconImg(type));
            applyStyle(CSS, bg, txt);
        }

        /**
         * Same as other constructors but with an UI img
         */
        protected StyleBtn(UIImg type, String CSS, BackgroundFill bg, String txt){
            super();
            this.setGraphic(SpriteUI.getUIImg(type));
            applyStyle(CSS, bg, txt);
        }

        private void applyStyle(String CSS, BackgroundFill bg, String txt){
            if(CSS != null){
                this.setStyle(CSS);
            }
            if(bg != null){
                this.setBackground(new Background(bg));
            }
            if(txt != null){
                this.setText(txt);
            }
        }
    }
}
