package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Event.LevelGenEvent;
import be.ac.umons.Sokoban.JavaFX.Event.PlayerEvent;
import be.ac.umons.Sokoban.JavaFX.Size;
import be.ac.umons.Sokoban.JavaFX.Sprite.IconImg;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteTile;
import be.ac.umons.Sokoban.JavaFX.Sprite.TileImg;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

public class LevelGenSceneBis extends SceneTool {
    private final static GridPane root = new GridPane();

    private static GamePane gamePane = null;
    private static TileImg currModifier = TileImg.EMPTY;
    private static boolean containPlayer = false;

    private static GamePane gamePaneToTry = null;
    private static PlayerEvent eventToTry = null;

    private static Button resetButton = null;

    private final static EventHandler<MouseEvent> filter = event -> {
        System.out.println("Filtering out event " + event.getEventType());
        event.consume();
    };

    public static void makeScene(){
        root.addRow(0, topGenesis());
        root.addRow(1, centerRowGenesis(centerLeftGenesis(), centerRightGenesis()));
        root.addRow(2, bottomGenesis());

        root.setBackground(new Background(bgFillLightBlue));
        root.setStyle("-fx-padding: 20 20 20 20");

        Scene scene = new Scene(root); //
        SceneList.LVL_GEN.setScene(scene);
        setCurrSize(Size.MEDIUM);
    }

    private final static int MARGIN = 20;

    private static HBox centerRowGenesis(GamePane gamePane, VBox tilePane){
        HBox centerRow = new HBox();

        centerRow.getChildren().addAll(gamePane, tilePane);
        return centerRow;
    }

    private static GamePane centerLeftGenesis() {
        GamePane centerLeft = new GamePane(new Grid(currSize));
        // TO ADD centerLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, new LevelGenEvent());
        gamePane = centerLeft;
        centerLeft.initiate();
        centerLeft.setStyle("-fx-border-color: red; -fx-border-width: 5");

        return centerLeft;
    }

    private static VBox centerRightGenesis() {

        VBox centerRight = new VBox();

        Button boxButton = new Button("Box");
        Button flagButton = new Button("Flag");
        Button playerButton = new Button("Player");
        Button wallButton = new Button("Wall");
        Button eraseButton = new Button("Erase");

        boxButton.setGraphic(SpriteTile.getTileImg(TileImg.BOX));
        flagButton.setGraphic(SpriteTile.getTileImg(TileImg.FLAG));
        playerButton.setGraphic(SpriteTile.getTileImg(TileImg.PLAYER));
        wallButton.setGraphic(SpriteTile.getTileImg(TileImg.WALL));
        eraseButton.setGraphic(SpriteIcon.getIconImg(IconImg.RESET));

        final String UnpressedButtonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";

        final String PressedButtonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #E86A17;-fx-cursor: hand";

        boxButton.setStyle(UnpressedButtonCSS);
        flagButton.setStyle(UnpressedButtonCSS);
        wallButton.setStyle(UnpressedButtonCSS);
        playerButton.setStyle(UnpressedButtonCSS);
        eraseButton.setStyle(UnpressedButtonCSS);

        centerRight.setSpacing(20);
        centerRight.setStyle("-fx-padding: 10 10 10 10");
        centerRight.setAlignment(Pos.CENTER);

        centerRight.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        return centerRight;
    }

    private static GridPane bottomGenesis() {
        GridPane bottomSide = new GridPane();

        Button generate = new Button();
        Button reset = new Button();
        resetButton = reset;
        Button save = new Button();
        Button play = new Button();
        Button stop = new Button();

        TextField fileOutput = new TextField();
        fileOutput.setFont(new Font("arial", 20));

        //TODO adapt list view to size enum
        ObservableList<Size> diffSize = FXCollections.observableArrayList(Size.values());
        ListView<Size> sizePicker = new ListView<Size>(diffSize);

        sizePicker.setMaxHeight(93);
        sizePicker.setFixedCellSize(29);
        sizePicker.setMaxWidth(200);
        sizePicker.setScaleY(1.2);
        sizePicker.setScaleX(1.2);
        sizePicker.setStyle("-fx-border-insets: 1000");

        generate.setGraphic(SpriteIcon.getIconImg(IconImg.RELOAD));
        reset.setGraphic(SpriteIcon.getIconImg(IconImg.RESET));
        play.setGraphic(SpriteIcon.getIconImg(IconImg.PLAY));
        save.setGraphic(SpriteIcon.getIconImg(IconImg.SAVE));
        stop.setGraphic(SpriteIcon.getIconImg(IconImg.STOP));

        generate.setBackground(new Background(bgFillGray));
        reset.setBackground(new Background(bgFillDarkOrange));
        save.setBackground(new Background(bgFillGray));
        play.setBackground(new Background(bgFillGreen));
        stop.setBackground(new Background(bgFillDarkOrange));

        final String buttonCSS = "-fx-padding: 5 5 5 5;-fx-cursor: hand";
        generate.setStyle(buttonCSS);
        save.setStyle(buttonCSS);
        reset.setStyle(buttonCSS);
        play.setStyle(buttonCSS);
        stop.setStyle(buttonCSS);

        bottomSide.add(generate, 0, 0);
        bottomSide.add(reset, 0, 1);
        bottomSide.add(sizePicker, 1, 0);
        bottomSide.add(save, 2, 0);
        bottomSide.add(play, 2, 1);
        bottomSide.add(fileOutput, 3, 0);
        bottomSide.add(stop, 3, 1);

        bottomSide.getColumnConstraints().add(new ColumnConstraints());
        bottomSide.getColumnConstraints().add(new ColumnConstraints((currSize.getCol() * SpriteTile.getSize()) / 2.0));
        bottomSide.setPadding(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
        bottomSide.setHgap(20);

        GridPane.setHalignment(sizePicker, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.LEFT);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(save, HPos.CENTER);
        GridPane.setHalignment(fileOutput, HPos.LEFT);

        return bottomSide;
    }

    private static HBox topGenesis(){
        // Making things pretty
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(Font.font("impact", 35));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = makeToMenuButton();

        ImageView playerHead = SpriteTile.getTileImg(TileImg.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, SpriteTile.getTileImg(TileImg.HEAD), title);
        topSide.setSpacing(50);

        return topSide;
    }
}
