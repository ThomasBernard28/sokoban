package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import be.ac.umons.Sokoban.Save.Path;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;

import java.io.File;

public class LevelSelectionScene extends SceneTool{
    public static BorderPane root;
    private static final int MARGIN = 30;
    protected static Path currPath = null;
    protected static String currFile = null;

    public static void makeScene(){
        root = new BorderPane();
        root.setCenter(_centerGenesis());
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
            SceneList.GAME_MODE.setOnActive();
        });

        topSide.setStyle("-fx-padding: 60, 50, 20, 50");
        topSide.setSpacing(50);

        topSide.getChildren().addAll(exitButton, title);
        topSide.setAlignment(Pos.CENTER);

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
                GameScene.loadLevel(Path.LVL, "level1_0.xsb");
        });

        level2Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level2_0.xsb");
        });

        level3Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level3_0.xsb");
        });

        level4Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level4_1.xsb");
        });

        level5Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level5_1.xsb");
        });

        level6Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level6_1.xsb");
        });

        level7Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level7_1.xsb");
        });

        level8Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level8_2.xsb");
        });
        level9Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level9_2.xsb");
        });
        level10Button.setOnMouseClicked(event -> {
                GameScene.loadLevel(Path.LVL, "level10_2.xsb");
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

    public static GridPane _centerGenesis(){
        GridPane centerSide = new GridPane();

        LevelButton[] lvlButtons = new LevelButton[10];
        for (int i = 0; i < 10; i++) {
            lvlButtons[i] = new LevelButton(i + 1);
        }

        final int lvlCompleted = GameScene.getCurrProfile().getProfile().getLvlCompleted();
        for (int i = 0; i < lvlCompleted; i++) {
            lvlButtons[i].unlock();
        }
        if(lvlCompleted != 10) {
            lvlButtons[lvlCompleted].setNext();
        }

        HBox firstLevels = new HBox();
        HBox otherLevels = new HBox();
        for(int i = 0; i < 5; i++){
            firstLevels.getChildren().add(lvlButtons[i]);
            otherLevels.getChildren().add(lvlButtons[5 + i]);
        }
        firstLevels.setSpacing(100);
        firstLevels.setAlignment(Pos.TOP_CENTER);

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


        StackPane customLevelButton = new StackPane();
        ImageView customLevelButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);
        customLevelButtonImg.setScaleX(2);
        customLevelButtonImg.setScaleY(2);
        Label customLvlText = new Label("Load custom level");
        customLvlText.setFont(Font.font("impact", 25));
        customLevelButton.getChildren().addAll(customLevelButtonImg, customLvlText);
        customLevelButton.setStyle("-fx-cursor: hand;");

        customLevelButton.setOnMouseClicked(event -> {
            FileChooser fc = new FileChooser();
            File fileToload = new File(Path.LVL.getPath());
            fc.setInitialDirectory(fileToload);
            File fileChosen = fc.showOpenDialog(null);
            if(fileChosen != null){
                GameScene.loadLevel(Path.LVL, fileChosen.getName());
            }
        });



        bottomSide.getChildren().add(customLevelButton);
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

class LevelButton extends StackPane {

    final String filename;
    final int lvl;

    LevelButton(int lvl){
        assert lvl <= 10;
        this.lvl = lvl;

        ImageView btnImg = SpriteUI.getUIImg(UIImg.RED_BUTTON06);
        btnImg.setScaleX(2);
        btnImg.setScaleY(2);
        ImageView lockImg = SpriteIcon.getIconImg(IconImg.LOCKED);

        this.getChildren().addAll(btnImg, lockImg);
        this.setStyle("-fx-cursor: hand;");

        String name = "level" + lvl +"_2";
        if(lvl <= 7){
            name = "level" + lvl +"_1";
        }
        if(lvl <= 3){
            name = "level" + lvl +"_0";
        }
        // made final to be used in the lambda expression
        filename = name + ".xsb";

    }
    public int getLvl(){
        return lvl;
    }

    public void setNext(){
        this.getChildren().clear();

        ImageView btnImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON09);
        btnImg.setScaleY(2);
        btnImg.setScaleX(2);

        Label btnText = new Label("Lvl " + lvl);
        btnText.setFont(Font.font("impact", 20));

        this.getChildren().addAll(btnImg, btnText);

        this.setOnMouseClicked(event -> {
            GameScene.loadLevel(Path.LVL, filename);
        });
    }



    public void unlock(){
        this.getChildren().clear();

        ImageView btnImg = SpriteUI.getUIImg(UIImg.GREEN_BUTTON09);
        btnImg.setScaleY(2);
        btnImg.setScaleX(2);

        Label btnText = new Label("Lvl " + lvl);
        btnText.setFont(Font.font("impact", 20));

        this.getChildren().addAll(btnImg, btnText);

        this.setOnMouseClicked(event -> {
            GameScene.loadLevel(Path.LVL, filename);
        });
    }


}
