package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteUI;
import be.ac.umons.Sokoban.JavaFX.Sprite.UIImg;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MenuScene extends SceneTool{

    public static GridPane root = new GridPane();


    public static void makeScene(){

        root.setMinHeight(720);
        root.setMinWidth(1280);
        root.setBackground(new Background(bgFillLightBlue));

        root.setGridLinesVisible(false);

        topGenesis(root);
        centerGenesis(root);

        Scene scene = new Scene(root);
        SceneList.MENU2.setScene(scene);

    }
    public static void topGenesis(GridPane root){
        HBox emptyLeftBox = new HBox();
        HBox emptyRightBox = new HBox();

        emptyLeftBox.setPrefSize(640, 150);
        emptyRightBox.setPrefSize(640,150);

        root.add(emptyLeftBox, 0, 0);
        root.add(emptyRightBox, 2, 0);

        HBox titleBox = new HBox();

        Label title = new Label("Sokoban");
        title.setFont(Font.font("impact", 50));
        title.setAlignment(Pos.CENTER);

        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(540, 200);

        root.add(titleBox, 1, 0);
    }
    public static void centerGenesis(GridPane root){
        VBox choiceBox = new VBox(100);

        StackPane playButton = new StackPane();
        StackPane lvlGenButton = new StackPane();
        StackPane quitButton = new StackPane();

        ImageView playButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView lvlGenButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView quitButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);

        playButtonImg.setScaleY(2);
        playButtonImg.setScaleX(2);
        lvlGenButtonImg.setScaleX(2);
        lvlGenButtonImg.setScaleY(2);
        quitButtonImg.setScaleY(2);
        quitButtonImg.setScaleX(2);

        Label playText = new Label("Play");
        Label lvlText = new Label("Level Creator");
        Label quitText = new Label("Quit");

        playText.setFont(Font.font("impact",40));
        lvlText.setFont(Font.font("impact",40));
        quitText.setFont(Font.font("impact",40));

        playButton.getChildren().addAll(playButtonImg, playText);
        lvlGenButton.getChildren().addAll(lvlGenButtonImg, lvlText);
        quitButton.getChildren().addAll(quitButtonImg, quitText);

        playButton.setStyle("-fx-cursor: hand;");
        lvlGenButton.setStyle("-fx-cursor: hand;");
        quitButton.setStyle("-fx-cursor: hand");

        playButton.setOnMouseClicked(event -> {
            SceneList.PROFILE.setOnActive();
        });
        lvlGenButton.setOnMouseClicked(event -> {
            LevelGenScene.resetScene();
            SceneList.LVL_GEN.setOnActive();
        });
        quitButton.setOnMouseClicked(event -> quit());

        choiceBox.getChildren().addAll(playButton, lvlGenButton, quitButton);
        choiceBox.setAlignment(Pos.CENTER);
        choiceBox.setPrefSize(640, 780);

        root.add(choiceBox, 1, 1);
    }

}
