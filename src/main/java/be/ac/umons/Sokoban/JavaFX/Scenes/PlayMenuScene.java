package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;



public class PlayMenuScene extends SceneTool{
    public final static BorderPane root = new BorderPane();
    private final static int MARGIN = 30;
    private static Button resetButton = null;

    public static void makeScene(){
        root.setCenter(centerGenesis());
        root.setTop(topGenesis());
        root.setBottom(bottomGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());

        root.setBackground(new Background(bgFillLightBlue));
        root.setMinHeight(720);
        root.setMinWidth(1280);

        Scene scene  = new Scene(root);
        SceneList.PLAY_MENU.setScene(scene);
    }

    private static TilePane centerGenesis(){
        TilePane centerSide = new TilePane();

        StackPane LvlSelectButton = new StackPane();
        StackPane LoadSavedGameButton = new StackPane();

        ImageView LvlSelectButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView LoadSavedGameButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);

        LvlSelectButtonImg.setScaleX(2);
        LvlSelectButtonImg.setScaleY(2);
        LoadSavedGameButtonImg.setScaleX(2);
        LoadSavedGameButtonImg.setScaleY(2);


        Label LvlSelectText = new Label("Level Selection");
        Label LoadText = new Label("Load");

        LvlSelectText.setFont(Font.font("impact", 40));
        LoadText.setFont(Font.font("impact", 40));

        LvlSelectButton.getChildren().addAll(LvlSelectButtonImg, LvlSelectText);
        LoadSavedGameButton.getChildren().addAll(LoadSavedGameButtonImg, LoadText);

        LvlSelectButton.setStyle("-fx-cursor: hand;");
        LoadSavedGameButton.setStyle("-fx-cursor: hand;");

        LvlSelectButton.setOnMouseClicked(event -> {
            LevelSelectionScene.makeScene();
            SceneList.LVL_SELECTION.setOnActive();
        });


        LoadSavedGameButton.setOnMouseClicked(event -> {
            LoadScene.makeScene();
            SceneList.LOAD.setOnActive();
        });
        centerSide.getChildren().addAll(LvlSelectButton,LoadSavedGameButton);
        centerSide.setOrientation(Orientation.VERTICAL);
        centerSide.setAlignment(Pos.CENTER);
        centerSide.setVgap(100);


    return centerSide;

    }
    private static VBox rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(MARGIN);

        return rightSide;
    }
    private static TilePane bottomGenesis(){
        TilePane bottomSide = new TilePane();

        bottomSide.setMinHeight(MARGIN);

        return bottomSide;
    }
    private static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(MARGIN);

        return leftSide;
    }
    private static HBox topGenesis(){
        HBox topSide = new HBox();
        Label title = new Label("Game Mode Chooser");

        title.setFont(Font.font("impact", 70));
        title.setStyle("-fx-padding: 20 20 20 20");

        Button exitButton = makeToMenuButton();;
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

        topSide.setStyle("-fx-padding: 70 50 20 50");
        topSide.setSpacing(50);

        topSide.getChildren().addAll(exitButton, title);

        return topSide;
    }

    public static void resetScene() {
        resetButton.fire();
    }
}
