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

public class LoadScene extends SceneTool{

    private static final BorderPane root = new BorderPane();
    private static final int MARGIN = 30;

    public static void makeScene(){
        root.setTop(topGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());
        root.setBottom(bottomGenesis());
        root.setCenter(centerGenesis());

        root.setBackground(new Background(bgFillLightBlue));
        root.setMinHeight(720);
        root.setMinWidth(1280);

        Scene scene = new Scene(root);
        SceneList.LOAD.setScene(scene);
    }

    public static HBox topGenesis() {
        HBox topSide = new HBox();
        Label title = new Label("File Loader");

        title.setFont(Font.font("impact", 70));
        title.setStyle("-fx-padding: 70, 50, 20, 50");

        Button exitButton = makeExitButton();
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

        exitButton.setOnAction(event -> {
            SceneList.PLAY_MENU.setOnActive();
        });

        topSide.setSpacing(50);
        topSide.getChildren().addAll(exitButton, title);
        topSide.setStyle("-fx-padding: 60, 50, 20, 50");

        return topSide;
    }
    public static GridPane centerGenesis(){
        GridPane centerSide = new GridPane();
        VBox differentFunction = new VBox();

        StackPane loadButton = new StackPane();
        ImageView loadButtonImg = SpriteUI.getUIImg(UIImg.RED_BUTTON01);
        loadButtonImg.setScaleX(3);
        loadButtonImg.setScaleY(2);

        Label advertising = new Label("! BE SURE TO ENTER AN EXISTING FILE !");
        advertising.setFont(Font.font("impact", 40));

        Label loadButtonTxt = new Label("Load the Saved File");
        loadButtonTxt.setFont(Font.font("impact", 30));
        loadButton.getChildren().addAll(loadButtonImg, loadButtonTxt);
        loadButton.setStyle("-fx-cursor: hand;");

        TextField fileInput = new TextField();
        fileInput.setFont(new Font("arial", 30));
        fileInput.setScaleX(1);
        fileInput.setScaleY(2);

        differentFunction.getChildren().addAll(advertising, fileInput, loadButton);
        differentFunction.setSpacing(75);
        differentFunction.setAlignment(Pos.CENTER);

        centerSide.getChildren().add(differentFunction);
        centerSide.setAlignment(Pos.CENTER);
        centerSide.setStyle("-fx-padding:70, 50, 20, 50 ");

        loadButton.setOnMouseClicked(event -> {
            CharSequence input = fileInput.getCharacters();
            if (Pattern.matches("^(\\w|_)+$", input)){
                    GameScene.makeTheGame(Path.SAVE, input.toString());
                    fileInput.clear();
            }
        });

        return centerSide;
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
    public static HBox bottomGenesis(){
        HBox bottomSide = new HBox();

        bottomSide.setMinHeight(200);

        return bottomSide;
    }
}
