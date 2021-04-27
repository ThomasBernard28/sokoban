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

        ExitButton exitButton = new ExitButton(SceneList.PLAY_MENU);

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
