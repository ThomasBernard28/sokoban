package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.TileType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class LevelGenScene extends BorderPaneScene {
    /*
        CSS code example:
        generate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-wrap-text: true;");
        reset.setStyle("-fx-padding: 20 40 40 30; -fx-wrap-text: true;-fx-font-weight: 700;-fx-font-size: 43px;");
        validate.setStyle("-fx-border-width: 3;-fx-min-width: 90px;-fx-cursor: hand;" +
                "-fx-border-color: transparent #E8E8E8 transparent transparent;");
        bottomSide.setStyle("fx-border-style: solid inside;-fx-border-width: 2; -fx-border-color: black");

         */


    // Non static part
    private SpecialPane visualGrid = null;
    private TileType currModifier = TileType.EMPTY;
    private boolean containPlayer = false;

    private SpecialPane gridToTry = null;
    private PlayerEvent eventToTry = null;
    private final EventHandler filter = (EventHandler<MouseEvent>) event -> {
        System.out.println("Filtering out event " + event.getEventType());
        event.consume();
    };

    public LevelGenScene(){
        leftGenesis();
        topGenesis();
        bottomGenesis();
        rightGenesis();
        centerGenesis();
    }

    public TileType getCurrModifier(){
        return currModifier;
    }

    public SpecialPane getVisualGrid() {
        return visualGrid;
    }

    public void setContainPlayer(boolean hasPlayer){
        containPlayer = hasPlayer;
    }

    public void resetCurrModifier(){
        currModifier = TileType.EMPTY;
    }

    @Override
    protected void centerGenesis(){
        SpecialPane visualPane = new SpecialPane(new Grid(COLUMNS, ROWS));
        visualPane.initiateLvlGen();
        this.visualGrid = visualPane;
        this.root.setCenter(visualPane);
        visualPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new LevelGenEvent(this));
    }
    @Override
    protected void rightGenesis(){
        TilePane rightSide = new TilePane();
        rightSide.setOrientation(Orientation.VERTICAL);
        rightSide.setAlignment(Pos.CENTER);

        Button boxButton = new Button("Box");
        Button flagButton = new Button("Flag");
        Button playerButton = new Button("Player");
        Button wallButton = new Button("Wall");
        Button eraseButton = new Button("Erase");

        boxButton.setGraphic(cellGiver.getTileImg(TileType.BOX));
        flagButton.setGraphic(cellGiver.getTileImg(TileType.FLAG));
        playerButton.setGraphic(cellGiver.getTileImg(TileType.PLAYER));
        wallButton.setGraphic(cellGiver.getTileImg(TileType.WALL));
        eraseButton.setGraphic(iconGiver.getIcon(SpriteIcon.IconType.RESET));

        final String UnpressedButtonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";

        final String PressedButtonCSS ="-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #E86A17;-fx-cursor: hand";

        boxButton.setStyle(UnpressedButtonCSS);
        flagButton.setStyle(UnpressedButtonCSS);
        wallButton.setStyle(UnpressedButtonCSS);
        playerButton.setStyle(UnpressedButtonCSS);
        eraseButton.setStyle(UnpressedButtonCSS);



        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        // logic part
        // TODO change border style
        boxButton.setOnAction(event -> {
            currModifier = TileType.BOX;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            boxButton.setStyle(PressedButtonCSS);

        });
        flagButton.setOnAction(event -> {
            currModifier = TileType.FLAG;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            flagButton.setStyle(PressedButtonCSS);
        });
        wallButton.setOnAction(event -> {
            currModifier = TileType.WALL;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            wallButton.setStyle(PressedButtonCSS);
        });
        eraseButton.setOnAction(event -> {
            currModifier = TileType.EMPTY;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            eraseButton.setStyle(PressedButtonCSS);
        });
        playerButton.setOnAction(event -> {
            if(!containPlayer){
                currModifier = TileType.PLAYER;
                for (Node child: rightSide.getChildren()) {
                    child.setStyle(UnpressedButtonCSS);
                }
                playerButton.setStyle(PressedButtonCSS);

            }
        });

        // end of logic part

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);
        rightSide.setStyle("-fx-padding: 10 10 10 10");

        this.root.setRight(rightSide);
    }

    @Override
    protected void bottomGenesis(){
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

        // logic part
        play.setOnAction(event -> {
            if(containPlayer) {
                gridToTry = copyOfSpecialPane(visualGrid);
                eventToTry = new PlayerEvent(gridToTry.lG, gridToTry);

                root.setCenter(gridToTry);
                rootScene.addEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
                rootScene.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);

                gridToTry.initiate();
            }
        });
        stop.setOnAction(event -> {
            rootScene.removeEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
            rootScene.removeEventFilter(MouseEvent.MOUSE_CLICKED, filter);
            root.setCenter(visualGrid);

            gridToTry = null;
            eventToTry = null;

            visualGrid.initiateLvlGen();
        });

        //end of logic part

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

    @Override
    protected void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);
        leftSide.setMinHeight(ROWS * SIZE);
        leftSide.setBackground(new Background(bgFillLightBlue));
        this.root.setLeft(leftSide);
    }

    @Override
    protected void topGenesis(){
        // Making things pretty
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(getFont(25));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = makeExitButton(SceneSwitcher.UniqueScene.GAME);

        ImageView playerHead = cellGiver.getTileImg(TileType.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, cellGiver.getTileImg(TileType.HEAD), title);
        topSide.setSpacing(50);

        topSide.setBackground(new Background(bgFillLightBlue));
        this.root.setTop(topSide);
    }

    private static Grid copyOfGrid(Grid old){
        Grid copy = new Grid(old.col, old.row);
        for (int i = 0; i < old.row; i++) {
            for (int j = 0; j < old.col; j++) {
                copy.getGridAt(j, i).setMovableObject(old.getGridAt(j, i).getMovableObject().getNature());
                copy.getGridAt(j, i).setImmovableObject(old.getGridAt(j, i).getImmovableObject().getNature());
            }
        }
        copy.setPlayerLocation();
        return copy;
    }
    private static SpecialPane copyOfSpecialPane(SpecialPane old){
        return new SpecialPane(copyOfGrid(old.lG));
    }
}
