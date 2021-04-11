package be.ac.umons.Sokoban.JavaFX;


import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MenuScene extends BorderPaneScene{

    public final static BorderPane root = new BorderPane();

    public MenuScene(){
        super(root);
        centerGenesis();

        rightGenesis();
        leftGenesis();
        bottomGenesis();
        topGenesis();

        // root.setBackground(new Background(bgFillLightBlue));
        root.setMinWidth(1280);
        root.setMinHeight(720);

    }
    protected void centerGenesis(){
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

        playText.setFont(getFont(40));
        lvlText.setFont(getFont(40));
        quitText.setFont(getFont(40));

        playButton.getChildren().addAll(playButtonImg, playText);
        lvlGenButton.getChildren().addAll(lvlGenButtonImg, lvlText);
        quitButton.getChildren().addAll(quitButtonImg, quitText);

        playButton.setStyle("-fx-cursor: hand;");
        lvlGenButton.setStyle("-fx-cursor: hand;");
        quitButton.setStyle("-fx-cursor: hand");

        playButton.setOnMouseClicked(event -> {
            SceneSwitcher.switchScene(SceneSwitcher.UniqueScene.GAME);
            WINDOW.setFullScreen(true);
        });
        lvlGenButton.setOnMouseClicked(event -> {
            SceneSwitcher.switchScene(SceneSwitcher.UniqueScene.LVL_GEN);
            WINDOW.setFullScreen(true);
        });
        quitButton.setOnMouseClicked(event -> SceneSwitcher.quit());

        centerSide.getChildren().addAll(playButton, lvlGenButton, quitButton);
        centerSide.setOrientation(Orientation.VERTICAL);
        centerSide.setAlignment(Pos.CENTER);
        centerSide.setVgap(100);

        root.setCenter(centerSide);

    }
    protected void rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(LEFT_MARGIN);

        root.setRight(rightSide);
    }
    protected void bottomGenesis(){
        TilePane bottomSide = new TilePane();

        bottomSide.setMinHeight(LEFT_MARGIN);

        root.setBottom(bottomSide);
    }
    protected void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);

        root.setLeft(leftSide);
    }
    protected void topGenesis(){
        HBox topSide = new HBox();

        Label title = new Label("Menu");
        title.setFont(getFont(70));
        title.setStyle("-fx-padding: 20 20 20 20;");


        topSide.getChildren().add(title);
        topSide.setAlignment(Pos.CENTER);
        root.setTop(topSide);
    }
}
