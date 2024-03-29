package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.*;
import be.ac.umons.bernardhofmanshouba.JavaFX.Size;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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
        SANDBOX,
        GENERATOR,
        MENU,
        GAME_MODE,
        LVL_SELECTION,
        PROFILE,
        STATS,
        GAME_GEN,
        GAME;

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

        public Scene getScene(){
            return scene;
        }
    }

    protected static Stage WINDOW;

    public static void setStage(Stage theStage){
        WINDOW = theStage;
    }

    public static Stage getWINDOW(){
        return WINDOW;
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

    protected static class ExitButton extends Button{
        protected ExitButton(SceneList direction){
            super();

            setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));
            setBackground(new Background(bgFillGray));
            setStyle("-fx-cursor: hand;");

            setOnAction(event -> direction.setOnActive());

        }

        protected ExitButton(){
            super();

            setGraphic(SpriteIcon.getIconImg(IconImg.EXIT));
            setBackground(new Background(bgFillGray));
            setStyle("-fx-cursor: hand;");
        }

        protected void setScale(double s) {
            setScaleX(s);
            setScaleY(s);
        }
    }
    protected static Grid copyOfGrid(Grid old){
        Grid copy = new Grid(old.getSize());
        for (int i = 0; i < old.getSize().getRow(); i++) {
            for (int j = 0; j < old.getSize().getCol(); j++) {
                copy.getGridAt(j, i).setMovableContent(old.getGridAt(j, i).getMovableContent());
                copy.getGridAt(j, i).setImmovableContent(old.getGridAt(j, i).getImmovableContent());
            }
        }
        copy.setPlayer(old.getPlayerX(), old.getPlayerY());
        return copy;
    }

    protected static GamePane copyOfSpecialPane(GamePane old){
        return new GamePane(copyOfGrid(old.getGrid()));
    }
}
