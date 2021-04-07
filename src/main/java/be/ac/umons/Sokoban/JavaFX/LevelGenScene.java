package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class LevelGenScene {
    /*
        CSS code example:
        generate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-wrap-text: true;");
        reset.setStyle("-fx-padding: 20 40 40 30; -fx-wrap-text: true;-fx-font-weight: 700;-fx-font-size: 43px;");
        validate.setStyle("-fx-border-width: 3;-fx-min-width: 90px;-fx-cursor: hand;" +
                "-fx-border-color: transparent #E8E8E8 transparent transparent;");

         */
    private final static int COLUMNS = 16;
    private final static int ROWS = 9;
    private final static int SIZE = 32;

    private final static Paint lightOrangePaint = Color.valueOf("#FA8132");
    private final static Paint darkOrangePaint = Color.valueOf("#E86A17");
    private final static Paint lightBluePaint = Color.valueOf("#1EA7E1");
    private final static Paint darkBluePaint = Color.valueOf("#808080");

    private final static BackgroundFill bgFillLightBlue =
            new BackgroundFill(lightBluePaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillDarkOrange =
            new BackgroundFill(darkOrangePaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillDarkBlue =
            new BackgroundFill(darkBluePaint, new CornerRadii(1), null);

    private final static SpriteIcon iconGiver = new SpriteIcon("images/sheet_black1x.png", 50);
    private final static SpriteGame cellGiver = new SpriteGame();

    public static Scene genesis(){
        BorderPane root = new BorderPane();
        root.setCenter(centerGenesis());
        root.setTop(topGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());
        root.setBottom(bottomGenesis());

        return new Scene(root);
    }

    private static SpecialPane centerGenesis(){
        SpecialPane visualPane = new SpecialPane(new Grid(COLUMNS, ROWS));
        visualPane.initiate();

        return visualPane;
    }

    private static TilePane rightGenesis(){

        TilePane rightSide = new TilePane();
        rightSide.setOrientation(Orientation.VERTICAL);
        rightSide.setAlignment(Pos.CENTER);

        Button boxButton = new Button("Box");
        Button flagButton = new Button("Flag");
        Button playerButton = new Button("Player");
        Button wallButton = new Button("Wall");
        Button eraseButton = new Button("Erase");

        boxButton.setGraphic(cellGiver.getTileImg(SpriteGame.TileType.BOX));
        flagButton.setGraphic(cellGiver.getTileImg(SpriteGame.TileType.FLAG));
        playerButton.setGraphic(cellGiver.getTileImg(SpriteGame.TileType.PLAYER));
        wallButton.setGraphic(cellGiver.getTileImg(SpriteGame.TileType.WALL));
        eraseButton.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RESET));

        boxButton.setStyle("-fx-background-color: transparent");
        flagButton.setStyle("-fx-background-color: transparent");
        wallButton.setStyle("-fx-background-color: transparent");

        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);

        return rightSide;
    }

    private static GridPane bottomGenesis(){
        GridPane bottomSide = new GridPane();

        Button generate = new Button("Generate");
        Button reset = new Button("Reset");
        Button validate = new Button("Validate");
        Button play = new Button("try");

        ObservableList<CellSize> diffSize = FXCollections.observableArrayList(CellSize.values());
        ListView<CellSize> sizePicker = new ListView<CellSize>(diffSize);
        sizePicker.setMaxHeight(90);
        sizePicker.setFixedCellSize(29);

        generate.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RELOAD));
        reset.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RESET));
        play.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.PLAY));

        bottomSide.add(generate, 0, 0);
        bottomSide.add(reset, 0, 1);
        bottomSide.add(sizePicker, 1,1);
        bottomSide.add(validate,2,0);
        bottomSide.add(play,2, 1);

        bottomSide.setBackground(new Background(bgFillLightBlue));
        return bottomSide;
    }

    private static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(20);
        leftSide.setMinHeight(ROWS * SIZE);
        leftSide.setBackground(new Background(bgFillLightBlue));
        return leftSide;
    }

    private static HBox topGenesis(){
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(Font.font("Impact", 25));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = new Button();
        ImageView exitIcon = iconGiver.getIcon(SpriteIcon.IconType.EXIT);

        exitButton.setScaleX(0.5);
        exitButton.setScaleY(0.5);

        exitButton.setGraphic(exitIcon);
        exitButton.setBackground(new Background(bgFillDarkBlue));

        ImageView playerHead = cellGiver.getTileImg(SpriteGame.TileType.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, cellGiver.getTileImg(SpriteGame.TileType.HEAD), title);
        topSide.setSpacing(40);

        topSide.setBackground(new Background(bgFillLightBlue));
        return topSide;
    }
}
