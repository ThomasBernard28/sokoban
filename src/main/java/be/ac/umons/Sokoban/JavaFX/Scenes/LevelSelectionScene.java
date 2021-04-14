package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class LevelSelectionScene extends SceneTool{
    private static final BorderPane selectionRoot = new BorderPane();
    private static final int MARGIN = 30;

    public static void makeScene(){
        selectionRoot.setCenter(centerGenesis());
        selectionRoot.setBottom(bottomGenesis());
        selectionRoot.setTop(topGenesis());
        selectionRoot.setLeft(leftGenesis());
        selectionRoot.setRight(rightGenesis());


        selectionRoot.setBackground(new Background(bgFillLightBlue));
        selectionRoot.setMinWidth(1280);
        selectionRoot.setMinHeight(720);

        Scene scene = new Scene(selectionRoot);
        SceneList.LVL_SELECTION.setScene(scene);
    }
    public static HBox topGenesis(){
        HBox topSide = new HBox();
        Label title = new Label("Choose your level");

        title.setFont(Font.font("impact", 70));
        title.setStyle("-fx-padding: 20, 20 ,20 , 20");

        Button exitButton = new Button();
        ImageView exit = SpriteIcon.getIconImg(IconImg.EXIT);

        exit.setScaleX(0.5);
        exit.setScaleY(0.5);

        exitButton.setGraphic(exit);
        exitButton.setBackground(new Background(bgFillGray));
        exitButton.setStyle("-fx-cursor: hand;");

        exitButton.setOnMouseClicked(event -> {
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

    public static TilePane centerGenesis() {
        TilePane centerSide = new TilePane();

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


        level1text.setFont(Font.font("impact", 20));
        level2text.setFont(Font.font("impact", 20));
        level3text.setFont(Font.font("impact", 20));
        level4text.setFont(Font.font("impact", 20));
        level5text.setFont(Font.font("impact", 20));
        level6text.setFont(Font.font("impact", 20));
        level7text.setFont(Font.font("impact", 20));
        level8text.setFont(Font.font("impact", 20));
        level9text.setFont(Font.font("impact", 20));
        level10text.setFont(Font.font("impact", 20));

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

        StackPane customLevelButton = new StackPane();
        ImageView customLevelButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);
        customLevelButtonImg.setScaleX(2);
        customLevelButtonImg.setScaleY(2);
        Label customLvlText = new Label("Load custom level");
        customLvlText.setFont(Font.font("impact", 25));
        customLevelButton.getChildren().addAll(customLevelButtonImg, customLvlText);
        customLevelButton.setStyle("-fx-cursor: hand;");
        //TODO Add an empty fill text to specify the name of the file to load


        bottomSide.getChildren().add(customLevelButton);
        bottomSide.setAlignment(Pos.TOP_CENTER);
        bottomSide.setStyle("-fx-padding: 70, 50, 20, 50");

        return bottomSide;
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
