package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.IconImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import be.ac.umons.bernardhofmanshouba.Save.Path;
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

    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        topLeftGenesis(root);
        topGenesis(root);
        centerGenesis(root);
        bottomGenesis(root);

        Scene scene = new Scene(root);
        SceneList.LVL_SELECTION.setScene(scene);
    }

    public static void topLeftGenesis(GridPane root){
        HBox exitBox = new HBox();


        Button exitButton = makeExitButton();
        exitButton.setScaleY(1);
        exitButton.setScaleX(1);
        exitButton.setTranslateY(15);
        exitButton.setTranslateX(15);
        exitButton.setOnAction(event ->{
            SceneList.PROFILE.setOnActive();
        });

        exitBox.getChildren().add(exitButton);
        exitBox.setPrefSize(590, 150);
        root.add(exitBox, 0, 0);
    }

    public static void topGenesis(GridPane root){
        HBox titleBox = new HBox();
        HBox emptyBox = new HBox();

        Label title = new Label("Choose your level");
        title.setFont(Font.font("impact", 70));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(740, 150);
        titleBox.setTranslateY(50);

        root.add(titleBox, 1, 0);

        emptyBox.setPrefSize(590, 150);
        root.add(emptyBox, 2, 0);
    }

    public static void centerGenesis(GridPane root){
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
        centerSide.setPrefSize(1000, 780);

        root.add(centerSide, 1, 1);
    }
    public static void bottomGenesis(GridPane root){
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

        bottomSide.setPrefSize(640, 150);

        root.add(bottomSide, 1, 2);
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
