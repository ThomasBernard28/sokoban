package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public abstract class BorderPaneScene extends SceneSwitcher {
    /*
    LARGE: 16, 9 pour 64 de cellSize
    MEDIUM: 21, 12 pour 48 de cellSize
    SMALL:32, 18 pour 32 de cellSize

    PARAM to change:
        this:
            COLUMNS
            ROWS
            SIZE
        reconstruct imgGive.setSPRITE(SIZE) in every container ie this and every SpecialPane

     */
    protected static int COLUMNS = 21;
    protected static int ROWS = 12;
    protected final static int LEFT_MARGIN = 30;

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


    // Non-static
    protected final Scene rootScene;

    protected BorderPaneScene(Pane root){
        rootScene = new Scene(root);
    }

    public Scene getScene(){
        return rootScene;
    }

    protected Font getFont(int size){
        return Font.font("Impact", size);
    }

    protected Button makeToMenuButton(){
        Button exitButton = new Button();
        ImageView exit = SpriteIcon.getIconImg(IconImg.EXIT);

        exitButton.setScaleX(0.5);
        exitButton.setScaleY(0.5);

        exitButton.setGraphic(exit);
        exitButton.setBackground(new Background(bgFillGray));
        exitButton.setStyle("-fx-cursor: hand");

        exitButton.setOnAction(event -> switchScene(UniqueScene.MENU));
        return exitButton;
    }

    protected void centerGenesis(int row, int col){}
    protected void centerGenesis(){}
    protected void bottomGenesis(){}
    protected void leftGenesis(){}
    protected void topGenesis(){}
}
