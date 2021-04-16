package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Event.PlayerEvent;
import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.regex.Pattern;

public class LevelSelectionScene extends SceneTool{
    private static final BorderPane root = new BorderPane();
    private static final int MARGIN = 30;

    public static void makeScene(){
        root.setCenter(centerGenesis());
        root.setBottom(bottomGenesis());
        root.setTop(topGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());


        root.setBackground(new Background(bgFillLightBlue));
        root.setMinWidth(1280);
        root.setMinHeight(720);

        Scene scene = new Scene(root);
        SceneList.LVL_SELECTION.setScene(scene);
    }
    public static HBox topGenesis(){
        HBox topSide = new HBox();
        Label title = new Label("Choose your level");

        title.setFont(Font.font("impact", 70));
        title.setStyle("-fx-padding: 20, 20 ,20 , 20");

        Button exitButton = makeExitButton();
        exitButton.setScaleX(1.0);
        exitButton.setScaleY(1.0);
        exitButton.setOnAction(event -> {
            SceneList.PLAY_MENU.setOnActive();
        });

        topSide.setStyle("-fx-padding: 60, 50, 20, 50");
        topSide.setSpacing(50);

        topSide.getChildren().addAll(exitButton, title);

        return topSide;
    }
    public static VBox rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(MARGIN);

        return rightSide;
    }
    public static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(MARGIN);

        return leftSide;
    }

    public static GridPane centerGenesis() {
        GridPane centerSide = new GridPane();

        StackPane level1Button = new StackPane();
        StackPane level2Button = new StackPane();
        StackPane level3Button = new StackPane();
        StackPane level4Button = new StackPane();
        StackPane level5Button = new StackPane();
        StackPane level6Button = new StackPane();
        StackPane level7Button = new StackPane();
        StackPane level8Button = new StackPane();
        StackPane level9Button = new StackPane();
        StackPane level10Button = new StackPane();


        ImageView level1ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON06);
        ImageView level2ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON06);
        ImageView level3ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON06);
        ImageView level4ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON06);
        ImageView level5ButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON06);
        ImageView level6ButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);
        ImageView level7ButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);
        ImageView level8ButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);
        ImageView level9ButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);
        ImageView level10ButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);


        scalier(level1ButtonImg, level2ButtonImg, level3ButtonImg, level4ButtonImg, level5ButtonImg);
        scalier(level6ButtonImg, level7ButtonImg, level8ButtonImg, level9ButtonImg, level10ButtonImg);

        Label level1text = new Label("Lvl 1");
        Label level2text = new Label("Lvl 2");
        Label level3text = new Label("Lvl 3");
        Label level4text = new Label("Lvl 4");
        Label level5text = new Label("Lvl 5");
        Label level6text = new Label("Lvl 6");
        Label level7text = new Label("Lvl 7");
        Label level8text = new Label("Lvl 8");
        Label level9text = new Label("Lvl 9");
        Label level10text = new Label("Lvl 10");


        setFont(level1text, level2text, level3text, level4text, level5text);
        setFont(level6text, level7text, level8text, level9text, level10text);

        level1Button.getChildren().addAll(level1ButtonImg, level1text);
        level2Button.getChildren().addAll(level2ButtonImg, level2text);
        level3Button.getChildren().addAll(level3ButtonImg, level3text);
        level4Button.getChildren().addAll(level4ButtonImg, level4text);
        level5Button.getChildren().addAll(level5ButtonImg, level5text);
        level6Button.getChildren().addAll(level6ButtonImg, level6text);
        level7Button.getChildren().addAll(level7ButtonImg, level7text);
        level8Button.getChildren().addAll(level8ButtonImg, level8text);
        level9Button.getChildren().addAll(level9ButtonImg, level9text);
        level10Button.getChildren().addAll(level10ButtonImg, level10text);

        level1Button.setStyle("-fx-cursor: hand;");
        level2Button.setStyle("-fx-cursor: hand;");
        level3Button.setStyle("-fx-cursor: hand;");
        level4Button.setStyle("-fx-cursor: hand;");
        level5Button.setStyle("-fx-cursor: hand;");
        level6Button.setStyle("-fx-cursor: hand;");
        level7Button.setStyle("-fx-cursor: hand;");
        level8Button.setStyle("-fx-cursor: hand;");
        level9Button.setStyle("-fx-cursor: hand;");
        level10Button.setStyle("-fx-cursor: hand;");

        level1Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level1_0");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level2Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level2_0");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level3Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level3_0");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level4Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level4_1");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level5Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level5_1");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level6Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level6_1");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level7Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level7_1");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level8Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level8_2");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level9Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level9_2");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });
        level10Button.setOnMouseClicked(event -> {
            try {
                Grid gameFile =Load.loadFile(Path.LVL, "level10_2");
                GameScene.makeScene(gameFile);
                SceneList.GAME.setOnActive();

            }catch (IOException e){
                e.printStackTrace();
            }
        });



        HBox firstLevels = new HBox();
        firstLevels.getChildren().addAll(level1Button, level2Button, level3Button, level4Button, level5Button);
        firstLevels.setSpacing(100);
        firstLevels.setAlignment(Pos.TOP_CENTER);


        HBox otherLevels = new HBox();
        otherLevels.getChildren().addAll(level6Button, level7Button, level8Button, level9Button, level10Button);
        otherLevels.setSpacing(100);
        otherLevels.setAlignment(Pos.BOTTOM_CENTER);

        VBox levels = new VBox();
        levels.getChildren().addAll(firstLevels, otherLevels);
        levels.setSpacing(100);
        levels.setAlignment(Pos.CENTER);

        centerSide.getChildren().add(levels);
        centerSide.setAlignment(Pos.CENTER);

        return centerSide;
    }
    public static HBox bottomGenesis(){
        HBox bottomSide = new HBox();

        TextField fileInput = new TextField();
        fileInput.setFont(new Font("arial", 20));
        fileInput.setScaleX(1.5);
        fileInput.setScaleY(2.5);

        StackPane customLevelButton = new StackPane();
        ImageView customLevelButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);
        customLevelButtonImg.setScaleX(2);
        customLevelButtonImg.setScaleY(2);
        Label customLvlText = new Label("Load custom level");
        customLvlText.setFont(Font.font("impact", 25));
        customLevelButton.getChildren().addAll(customLevelButtonImg, customLvlText);
        customLevelButton.setStyle("-fx-cursor: hand;");

        customLevelButton.setOnMouseClicked(event -> {
            CharSequence input = fileInput.getCharacters();
            if (Pattern.matches("^(\\w|_)+$", input)) {
                try {
                    Grid gameFile = Load.loadFile(Path.LVL, input.toString());
                    GameScene.makeScene(gameFile);
                    SceneList.GAME.setOnActive();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });



        bottomSide.getChildren().addAll(customLevelButton, fileInput);
        bottomSide.setSpacing(175);
        bottomSide.setAlignment(Pos.TOP_CENTER);
        bottomSide.setStyle("-fx-padding: 70, 50, 20, 50");

        return bottomSide;
    }

    private static void setFont(Label level1text, Label level2text, Label level3text, Label level4text, Label level5text) {
        level1text.setFont(Font.font("impact", 20));
        level2text.setFont(Font.font("impact", 20));
        level3text.setFont(Font.font("impact", 20));
        level4text.setFont(Font.font("impact", 20));
        level5text.setFont(Font.font("impact", 20));
    }


    private static void scalier(ImageView level1ButtonImg, ImageView level2ButtonImg, ImageView level3ButtonImg, ImageView level4ButtonImg, ImageView level5ButtonImg) {
        level1ButtonImg.setScaleX(2);
        level1ButtonImg.setScaleY(2);
        level2ButtonImg.setScaleX(2);
        level2ButtonImg.setScaleY(2);
        level3ButtonImg.setScaleX(2);
        level3ButtonImg.setScaleY(2);
        level4ButtonImg.setScaleX(2);
        level4ButtonImg.setScaleY(2);
        level5ButtonImg.setScaleX(2);
        level5ButtonImg.setScaleY(2);
    }
}
