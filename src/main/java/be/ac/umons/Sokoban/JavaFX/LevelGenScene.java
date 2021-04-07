package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LevelGenScene{
    /*
        CSS code example:
        generate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-wrap-text: true;");
        reset.setStyle("-fx-padding: 20 40 40 30; -fx-wrap-text: true;-fx-font-weight: 700;-fx-font-size: 43px;");
        validate.setStyle("-fx-border-width: 3;-fx-min-width: 90px;-fx-cursor: hand;" +
                "-fx-border-color: transparent #E8E8E8 transparent transparent;");
        bottomSide.setStyle("fx-border-style: solid inside;-fx-border-width: 2; -fx-border-color: black");

         */
    private final static int COLUMNS = 16;
    private final static int ROWS = 9;
    private final static int SIZE = 64;
    private final static int LEFT_MARGIN = 30;

    private final static Paint lightOrangePaint = Color.valueOf("#FA8132");
    private final static Paint darkOrangePaint = Color.valueOf("#E86A17");
    private final static Paint lightBluePaint = Color.valueOf("#1EA7E1");
    private final static Paint darkBluePaint = Color.valueOf("#1989B8");
    private final static Paint grayPaint = Color.valueOf("#808080");
    private final static Paint greenPaint = Color.valueOf("#77D04F");

    private final static BackgroundFill bgFillLightBlue =
            new BackgroundFill(lightBluePaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillDarkOrange =
            new BackgroundFill(darkOrangePaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillGray =
            new BackgroundFill(grayPaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillDarkBlue =
            new BackgroundFill(darkBluePaint, new CornerRadii(1), null);
    private final static BackgroundFill bgFillGreen =
            new BackgroundFill(greenPaint, new CornerRadii(1), null);

    private final static SpriteIcon iconGiver = new SpriteIcon("images/sheet_black1x.png", 50);
    private final static SpriteGame cellGiver = new SpriteGame();

    // Non static part
    public final BorderPane root = new BorderPane();
    private final SpecialPane visualGrid;
    private final Stage WINDOW;

public LevelGenScene(Stage WINDOW){
        SpecialPane visualGrid = centerGenesis();
        _leftGenesis();
        _topGenesis();
        _bottomGenesis();
        _rightGenesis();
        _centerGenesis();

        this.visualGrid = visualGrid;
        this.WINDOW = WINDOW;
    }


    private void _centerGenesis(){
        SpecialPane visualPane = new SpecialPane(new Grid(COLUMNS, ROWS));
        visualPane.initiate();
        this.root.setCenter(visualPane);
    }



    private void _rightGenesis(){
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

        final String buttonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";

        boxButton.setStyle(buttonCSS);
        flagButton.setStyle(buttonCSS);
        wallButton.setStyle(buttonCSS);
        playerButton.setStyle(buttonCSS);
        eraseButton.setStyle(buttonCSS);

        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);
        rightSide.setStyle("-fx-padding: 10 10 10 10");

        this.root.setRight(rightSide);
    }



    private void _bottomGenesis(){
        GridPane bottomSide = new GridPane();

        Button generate = new Button();
        Button reset = new Button();
        Button save = new Button();
        Button play = new Button();
        Button stop = new Button();

        ObservableList<CellSize> diffSize = FXCollections.observableArrayList(CellSize.values());
        ListView<CellSize> sizePicker = new ListView<CellSize>(diffSize);
        sizePicker.setMaxHeight(90);
        sizePicker.setFixedCellSize(29);
        sizePicker.setMaxWidth(200);
        sizePicker.setScaleY(1.2);
        sizePicker.setScaleX(1.2);
        sizePicker.setStyle("-fx-border-insets: 1000");

        generate.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RELOAD));
        reset.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RESET));
        play.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.PLAY));
        save.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.SAVE));
        stop.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.STOP));

        generate.setBackground(new Background(bgFillGray));
        reset.setBackground(new Background(bgFillDarkOrange));
        save.setBackground(new Background(bgFillGray));
        play.setBackground(new Background(bgFillGreen));
        stop.setBackground(new Background(bgFillDarkOrange));

        final String buttonCSS = "-fx-padding: 10 10 10 10;-fx-cursor: hand";
        generate.setStyle(buttonCSS);
        save.setStyle(buttonCSS);
        reset.setStyle(buttonCSS);
        play.setStyle(buttonCSS);
        stop.setStyle(buttonCSS);

        bottomSide.add(generate,0,0);
        bottomSide.add(reset, 0,1);
        bottomSide.add(sizePicker,1,0);
        bottomSide.add(save,2,0);
        bottomSide.add(play,2,1);
        bottomSide.add(stop,3,1);

        bottomSide.getColumnConstraints().add(new ColumnConstraints());
        bottomSide.getColumnConstraints().add(new ColumnConstraints((COLUMNS * SIZE)/ 2.0));
        bottomSide.setPadding(new Insets(LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN));
        bottomSide.setHgap(20);

        GridPane.setHalignment(sizePicker, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.CENTER);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(save, HPos.CENTER);



        bottomSide.setBackground(new Background(bgFillLightBlue));
        this.root.setBottom(bottomSide);
    }


    private void _leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);
        leftSide.setMinHeight(ROWS * SIZE);
        leftSide.setBackground(new Background(bgFillLightBlue));
        this.root.setLeft(leftSide);
    }

    private void _topGenesis(){
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(Font.font("Impact", 25));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = new Button();
        ImageView exitIcon = iconGiver.getIcon(SpriteIcon.IconType.EXIT);

        exitButton.setScaleX(0.5);
        exitButton.setScaleY(0.5);

        exitButton.setGraphic(exitIcon);
        exitButton.setBackground(new Background(bgFillGray));
        exitButton.setStyle("-fx-cursor: hand");

        ImageView playerHead = cellGiver.getTileImg(SpriteGame.TileType.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, cellGiver.getTileImg(SpriteGame.TileType.HEAD), title);
        topSide.setSpacing(50);

        topSide.setBackground(new Background(bgFillLightBlue));
        this.root.setTop(topSide);
    }








    // Back to static
    public static BorderPane genesis(){
        BorderPane root = new BorderPane();
        root.setCenter(centerGenesis());
        //root.setTop(topGenesis());
        root.setLeft(leftGenesis());
        root.setRight(rightGenesis());
        root.setBottom(bottomGenesis());

        return root;
    }

    private static SpecialPane centerGenesis(){
        SpecialPane visualPane = new SpecialPane(new Grid(COLUMNS, ROWS));
        visualPane.initiate();

        return visualPane;
    }

    private static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);
        leftSide.setMinHeight(ROWS * SIZE);
        leftSide.setBackground(new Background(bgFillLightBlue));
        return leftSide;
    }

    private static GridPane bottomGenesis(){
        GridPane bottomSide = new GridPane();

        Button generate = new Button();
        Button reset = new Button();
        Button save = new Button();
        Button play = new Button();
        Button stop = new Button();

        ObservableList<CellSize> diffSize = FXCollections.observableArrayList(CellSize.values());
        ListView<CellSize> sizePicker = new ListView<CellSize>(diffSize);
        sizePicker.setMaxHeight(90);
        sizePicker.setFixedCellSize(29);
        sizePicker.setMaxWidth(200);
        sizePicker.setScaleY(1.2);
        sizePicker.setScaleX(1.2);
        sizePicker.setStyle("-fx-border-insets: 1000");

        generate.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RELOAD));
        reset.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RESET));
        play.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.PLAY));
        save.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.SAVE));
        stop.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.STOP));

        generate.setBackground(new Background(bgFillGray));
        reset.setBackground(new Background(bgFillDarkOrange));
        save.setBackground(new Background(bgFillGray));
        play.setBackground(new Background(bgFillGreen));
        stop.setBackground(new Background(bgFillDarkOrange));

        final String buttonCSS = "-fx-padding: 10 10 10 10;-fx-cursor: hand";
        generate.setStyle(buttonCSS);
        save.setStyle(buttonCSS);
        reset.setStyle(buttonCSS);
        play.setStyle(buttonCSS);
        stop.setStyle(buttonCSS);

        bottomSide.add(generate,0,0);
        bottomSide.add(reset, 0,1);
        bottomSide.add(sizePicker,1,0);
        bottomSide.add(save,2,0);
        bottomSide.add(play,2,1);
        bottomSide.add(stop,3,1);

        bottomSide.getColumnConstraints().add(new ColumnConstraints());
        bottomSide.getColumnConstraints().add(new ColumnConstraints((COLUMNS * SIZE)/ 2.0));
        bottomSide.setPadding(new Insets(LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN));
        bottomSide.setHgap(20);

        GridPane.setHalignment(sizePicker, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.CENTER);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(save, HPos.CENTER);



        bottomSide.setBackground(new Background(bgFillLightBlue));
        return bottomSide;
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

        final String buttonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";

        boxButton.setStyle(buttonCSS);
        flagButton.setStyle(buttonCSS);
        wallButton.setStyle(buttonCSS);
        playerButton.setStyle(buttonCSS);
        eraseButton.setStyle(buttonCSS);

        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);
        rightSide.setStyle("-fx-padding: 10 10 10 10");

        return rightSide;
    }
}
