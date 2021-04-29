
package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteUI;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.UIImg;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class MenuScene extends SceneTool{

    public static GridPane root = new GridPane();

    public static void makeScene(){
        root.setMinWidth(1280);
        root.setMinHeight(720);
        root.setBackground(new Background(bgFillLightBlue));

        topGenesis(root);
        centerGenesis(root);

        Scene scene = new Scene(root);
        SceneList.MENU.setScene(scene);
    }

    public static void topGenesis(GridPane root){
        HBox titleBox = new HBox();
        HBox leftEmpty = new HBox();
        HBox rightEmpty = new HBox();

        leftEmpty.setPrefSize(640, 150);
        root.add(leftEmpty, 0, 0);

        rightEmpty.setPrefSize(640, 150);
        root.add(rightEmpty, 2, 0);

        Label title = new Label("MENU");
        title.setFont(Font.font("impact", 70));
        title.setAlignment(Pos.CENTER);
        titleBox.getChildren().add(title);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPrefSize(640,150);
        root.add(titleBox, 1, 0);
    }
    public static void centerGenesis(GridPane root){
        VBox choiceBox = new VBox(100);

        StackPane playButton = new StackPane();
        StackPane generatorButton = new StackPane();
        StackPane sandboxButton = new StackPane();
        StackPane quitButton = new StackPane();

        ImageView playButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView generatorButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView sandboxButtonImg = SpriteUI.getUIImg(UIImg.YELLOW_BUTTON02);
        ImageView quitButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);

        playButtonImg.setScaleY(2);
        playButtonImg.setScaleX(2);
        generatorButtonImg.setScaleX(2);
        generatorButtonImg.setScaleY(2);
        sandboxButtonImg.setScaleX(2);
        sandboxButtonImg.setScaleY(2);
        quitButtonImg.setScaleY(2);
        quitButtonImg.setScaleX(2);

        Label playText = new Label("Play");
        Label generatorText = new Label("Generator");
        Label sandboxText = new Label("Sandbox");
        Label quitText = new Label("Quit");

        playText.setFont(Font.font("impact",40));
        generatorText.setFont(Font.font("impact", 40));
        sandboxText.setFont(Font.font("impact",40));
        quitText.setFont(Font.font("impact",40));

        playButton.getChildren().addAll(playButtonImg, playText);
        generatorButton.getChildren().addAll(generatorButtonImg, generatorText);
        sandboxButton.getChildren().addAll(sandboxButtonImg, sandboxText);
        quitButton.getChildren().addAll(quitButtonImg, quitText);

        playButton.setStyle("-fx-cursor: hand;");
        generatorButton.setStyle("-fx-cursor: hand;");
        sandboxButton.setStyle("-fx-cursor: hand;");
        quitButton.setStyle("-fx-cursor: hand");

        playButton.setOnMouseClicked(event -> {
            SceneList.PROFILE.setOnActive();
        });

        generatorButton.setOnMouseClicked(event -> {
            SceneList.GENERATOR.setOnActive();
        });

        sandboxButton.setOnMouseClicked(event -> {
            SandBoxScene.resetScene();
            SceneList.SANDBOX.setOnActive();
        });

        quitButton.setOnMouseClicked(event -> quit());

        choiceBox.getChildren().addAll(playButton,generatorButton, sandboxButton, quitButton);
        choiceBox.setAlignment(Pos.CENTER);

        choiceBox.setPrefSize(640, 780);
        root.add(choiceBox, 1, 1);
    }
}

