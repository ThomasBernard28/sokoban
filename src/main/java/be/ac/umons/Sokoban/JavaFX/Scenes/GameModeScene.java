package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import be.ac.umons.Sokoban.Save.Path;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * This Class display the Game mode chooser scene
 */


public class GameModeScene extends SceneTool{
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
        StackPane GeneratorButton = new StackPane();
        StackPane LoadSavedGameButton = new StackPane();

        ImageView LvlSelectButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView GeneratorButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView LoadSavedGameButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);

        LvlSelectButtonImg.setScaleX(2);
        LvlSelectButtonImg.setScaleY(2);
        GeneratorButtonImg.setScaleX(2);
        GeneratorButtonImg.setScaleY(2);
        LoadSavedGameButtonImg.setScaleX(2);
        LoadSavedGameButtonImg.setScaleY(2);


        Label LvlSelectText = new Label("Level Selection");
        Label GeneratorText = new Label("Generator");
        Label LoadText = new Label("Load");

        LvlSelectText.setFont(Font.font("impact", 40));
        GeneratorText.setFont(Font.font("impact", 40));
        LoadText.setFont(Font.font("impact", 40));

        LvlSelectButton.getChildren().addAll(LvlSelectButtonImg, LvlSelectText);
        GeneratorButton.getChildren().addAll(GeneratorButtonImg, GeneratorText);
        LoadSavedGameButton.getChildren().addAll(LoadSavedGameButtonImg, LoadText);

        LvlSelectButton.setStyle("-fx-cursor: hand;");
        GeneratorButton.setStyle("-fx-cursor: hand;");
        LoadSavedGameButton.setStyle("-fx-cursor: hand;");

        //Switching to the level selection
        LvlSelectButton.setOnMouseClicked(event -> {
            LevelSelectionScene.makeScene();
            SceneList.LVL_SELECTION.setOnActive();

        });
        //Switching to the Generator Scene
        GeneratorButton.setOnMouseClicked(event -> {
            //TODO
        });

        //Opening a file explorer to load a saved game
        LoadSavedGameButton.setOnMouseClicked(event -> {
            FileChooser fc = new FileChooser();
            File fileToload = new File(Path.SAVE.getPath());
            fc.setInitialDirectory(fileToload);
            File fileChosen = fc.showOpenDialog(null);
            if(fileChosen != null){
                GameScene2.loadLevel(Path.SAVE, fileChosen.getName());
            }
        });
        centerSide.getChildren().addAll(LvlSelectButton, GeneratorButton, LoadSavedGameButton);
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

        Button exitButton = makeExitButton();
        exitButton.setOnAction(event -> {
            GameScene2.setCurrProfile(null);
            SceneList.PROFILE.setOnActive();
        });
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

        topSide.setStyle("-fx-padding: 70 50 20 50");
        topSide.setSpacing(50);

        topSide.getChildren().addAll(exitButton, title);
        topSide.setAlignment(Pos.CENTER);

        return topSide;
    }

    public static void resetScene() {
        resetButton.fire();
    }
}
