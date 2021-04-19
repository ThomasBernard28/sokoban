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

public class MenuScene extends SceneTool {

    public final static BorderPane root = new BorderPane();
    private final static int MARGIN = 30;


    public static void makeScene(){
        root.setTop(topGenesis());
        root.setCenter(centerGenesis());
        //root.setLeft(leftGenesis());
        //root.setRight(rightGenesis());
        //root.setBottom(bottomGenesis());

        root.setBackground(new Background(bgFillLightBlue));
        root.setMinHeight(720);
        root.setMinWidth(1280);

        Scene scene = new Scene(root);

        SceneList.MENU.setScene(scene);
    }

    private static TilePane centerGenesis(){
        TilePane centerSide = new TilePane();

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

        centerSide.getChildren().addAll(playButton, lvlGenButton, quitButton);
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

        Label title = new Label("Menu");
        title.setFont(Font.font("impact",70));
        title.setStyle("-fx-padding: 20 20 20 20;");


        topSide.getChildren().add(title);
        topSide.setAlignment(Pos.CENTER);
        return topSide;
    }


}
