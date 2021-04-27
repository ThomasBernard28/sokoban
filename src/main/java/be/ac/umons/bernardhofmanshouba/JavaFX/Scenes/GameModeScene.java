package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import be.ac.umons.bernardhofmanshouba.Save.Path;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * This Class display the Game mode chooser scene
 */


public class GameModeScene extends SceneTool {
    public final static GridPane root = new GridPane();

    public static void makeScene() {
        root.setBackground(new Background(bgFillLightBlue));
        root.setMinHeight(720);
        root.setMinWidth(1280);

        topLeftGenesis(root);
        topGenesis(root);
        centerGenesis(root);


        Scene scene = new Scene(root);
        SceneList.GAME_MODE.setScene(scene);
    }

    public static void topLeftGenesis(GridPane root) {
        HBox exitBox = new HBox();
        ExitButton exitButton = new ExitButton(SceneList.PROFILE);

        exitButton.setTranslateX(15);
        exitButton.setTranslateY(15);

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(590, 150);
        root.add(exitBox, 0, 0);
    }

    public static void topGenesis(GridPane root) {
        HBox titleBox = new HBox();
        HBox emptyBox = new HBox();

        Label title = new Label("Game Mode Chooser");
        title.setFont(Font.font("impact", 70));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(740, 150);

        root.add(titleBox, 1, 0);

        emptyBox.setPrefSize(590, 150);
        root.add(emptyBox, 2, 0);
    }

    private static void centerGenesis(GridPane root) {
        VBox centerSide = new VBox(100);

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
            if (fileChosen != null) {
                GameScene.loadLevel(Path.SAVE, fileChosen.getName());
            }
        });
        centerSide.getChildren().addAll(LvlSelectButton, GeneratorButton, LoadSavedGameButton);
        centerSide.setAlignment(Pos.CENTER);

        centerSide.setPrefSize(640, 780);
        root.add(centerSide, 1, 1);

    }
}
