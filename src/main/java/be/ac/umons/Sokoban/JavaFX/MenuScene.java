package be.ac.umons.Sokoban.JavaFX;


import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MenuScene extends BorderPaneScene{

    private final SpriteUI UIGiver = new SpriteUI("images/yellowSheet.png", 0);

    public MenuScene(){
        centerGenesis();

        rightGenesis();
        leftGenesis();
        bottomGenesis();
        topGenesis();

        root.setBackground(new Background(bgFillLightBlue));

    }
    protected void centerGenesis(){
        TilePane centerSide = new TilePane();

        StackPane playButton = new StackPane();
        StackPane lvlGenButton = new StackPane();

        ImageView playButtonImg = UIGiver.getUiPart(SpriteUI.TYPE.YELLOW_BUTTON02);
        ImageView lvlGenButtonImg = UIGiver.getUiPart(SpriteUI.TYPE.YELLOW_BUTTON02);

        playButtonImg.setScaleY(2);
        playButtonImg.setScaleX(2);
        lvlGenButtonImg.setScaleX(2);
        lvlGenButtonImg.setScaleY(2);

        Label playText = new Label("Play");
        Label lvlText = new Label("Level Creator");

        playText.setFont(getFont(40));
        lvlText.setFont(getFont(40));

        playButton.getChildren().addAll(playButtonImg, playText);
        lvlGenButton.getChildren().addAll(lvlGenButtonImg, lvlText);

        playButton.setStyle("-fx-cursor: hand;");
        lvlGenButton.setStyle("-fx-cursor: hand;");

        playButton.setOnMouseClicked(event -> SceneSwitcher.switchScene(SceneSwitcher.UniqueScene.GAME));
        lvlGenButton.setOnMouseClicked(event -> SceneSwitcher.switchScene(SceneSwitcher.UniqueScene.LVL_GEN));

        centerSide.getChildren().addAll(playButton, lvlGenButton);
        centerSide.setOrientation(Orientation.VERTICAL);
        centerSide.setAlignment(Pos.CENTER);
        centerSide.setVgap(100);

        root.setCenter(centerSide);
        root.setMinHeight(720);
        root.setMinWidth(1280);

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
