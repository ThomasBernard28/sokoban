package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Test.ConsoleGrid;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.util.regex.Pattern;

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
    private final static BorderPane root = new BorderPane();

    private GamePane visualGrid = null;
    private TileImg currModifier = TileImg.EMPTY;
    private boolean containPlayer = false;

    private GamePane gridToTry = null;
    private PlayerEvent eventToTry = null;

    private final EventHandler filter = (EventHandler<MouseEvent>) event -> {
        System.out.println("Filtering out event " + event.getEventType());
        event.consume();
    };

    public LevelGenScene(){
        super(root);
        root.setBackground(new Background(bgFillLightBlue));

        leftGenesis();
        topGenesis();
        bottomGenesis();
        rightGenesis();
        centerGenesis();

    }

    public TileImg getCurrModifier(){
        return currModifier;
    }

    public GamePane getVisualGrid() {
        return visualGrid;
    }

    public void setContainPlayer(boolean hasPlayer){
        containPlayer = hasPlayer;
    }

    public void resetCurrModifier(){
        currModifier = TileImg.EMPTY;
    }

    @Override
    protected void centerGenesis(){
        GamePane visualPane = new GamePane(new Grid(COLUMNS, ROWS));

        visualPane.initiateLvlGen();
        visualPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new LevelGenEvent(this));

        HBox centerSide = new HBox();
        centerSide.getChildren().addAll(visualPane, rightGenesis());

        this.visualGrid = visualPane;
        this.root.setCenter(centerSide);
    }

    protected TilePane rightGenesis(){
        TilePane rightSide = new TilePane();
        rightSide.setOrientation(Orientation.VERTICAL);
        rightSide.setAlignment(Pos.CENTER);

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

        final String PressedButtonCSS ="-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #E86A17;-fx-cursor: hand";

        boxButton.setStyle(UnpressedButtonCSS);
        flagButton.setStyle(UnpressedButtonCSS);
        wallButton.setStyle(UnpressedButtonCSS);
        playerButton.setStyle(UnpressedButtonCSS);
        eraseButton.setStyle(UnpressedButtonCSS);



        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        // logic part
        boxButton.setOnAction(event -> {
            currModifier = TileImg.BOX;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            boxButton.setStyle(PressedButtonCSS);

        });
        flagButton.setOnAction(event -> {
            currModifier = TileImg.FLAG;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            flagButton.setStyle(PressedButtonCSS);
        });
        wallButton.setOnAction(event -> {
            currModifier = TileImg.WALL;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            wallButton.setStyle(PressedButtonCSS);
        });
        eraseButton.setOnAction(event -> {
            currModifier = TileImg.EMPTY;

            for (Node child: rightSide.getChildren()) {
                child.setStyle(UnpressedButtonCSS);
            }
            eraseButton.setStyle(PressedButtonCSS);
        });
        playerButton.setOnAction(event -> {
            if(!containPlayer){
                currModifier = TileImg.PLAYER;
                for (Node child: rightSide.getChildren()) {
                    child.setStyle(UnpressedButtonCSS);
                }
                playerButton.setStyle(PressedButtonCSS);

            }
        });

        // end of logic part

        rightSide.setVgap(20);
        rightSide.setMinSize(80, 0);
        rightSide.setStyle("-fx-padding: 10 10 10 10");

        rightSide.setAlignment(Pos.CENTER);


        //root.setRight(rightSide);
        return rightSide;
    }

    @Override
    protected void bottomGenesis(){
        GridPane bottomSide = new GridPane();

        Button generate = new Button();
        Button reset = new Button();
        Button save = new Button();
        Button play = new Button();
        Button stop = new Button();
        TextField fileOutput = new TextField();
        fileOutput.setFont(new Font("arial", 20));

        ObservableList<String> diffSize = FXCollections.observableArrayList("Small", "Medium", "Large");
        ListView<String> sizePicker = new ListView<String>(diffSize);
        sizePicker.setMaxHeight(90);
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
                eventToTry = new PlayerEvent(gridToTry.getGrid(), gridToTry);

                root.setCenter(gridToTry);
                rootScene.addEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
                rootScene.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);

                gridToTry.initiate();
            }
        });
        stop.setOnAction(event -> {
            // if not null then play mode is active
            if(gridToTry != null){
                rootScene.removeEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
                rootScene.removeEventFilter(MouseEvent.MOUSE_CLICKED, filter);
                root.setCenter(visualGrid);

                gridToTry = null;
                eventToTry = null;

                visualGrid.initiateLvlGen();
            }
        });
        reset.setOnAction(event -> {
            stop.fire();
            containPlayer = false;
            visualGrid.getGrid().resetGrid();
            visualGrid.initiate();
            ConsoleGrid.printConsole(visualGrid.getGrid());
        });

        save.setOnAction(event -> {
            CharSequence output = fileOutput.getCharacters();
            // https://regex101.com/
            if (Pattern.matches("^(\\w|_)+\\.(xsb)$", output)){
                System.out.println("coooorrectttt");
            }

        });

        //end of logic part

        bottomSide.add(generate,0,0);
        bottomSide.add(reset, 0,1);
        bottomSide.add(sizePicker,1,0);
        bottomSide.add(save,2,0);
        bottomSide.add(play,2,1);
        bottomSide.add(fileOutput, 3, 0);
        bottomSide.add(stop,3,1);

        bottomSide.getColumnConstraints().add(new ColumnConstraints());
        bottomSide.getColumnConstraints().add(new ColumnConstraints((COLUMNS * SpriteTile.getSize())/ 2.0));
        bottomSide.setPadding(new Insets(LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN, LEFT_MARGIN));
        bottomSide.setHgap(20);

        GridPane.setHalignment(sizePicker, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.LEFT);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(save, HPos.CENTER);
        GridPane.setHalignment(fileOutput, HPos.LEFT);



        this.root.setBottom(bottomSide);
    }

    @Override
    protected void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);
        leftSide.setMinHeight(ROWS * SpriteTile.getSize());
        this.root.setLeft(leftSide);
    }

    @Override
    protected void topGenesis(){
        // Making things pretty
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(getFont(35));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = makeToMenuButton();

        ImageView playerHead = SpriteTile.getTileImg(TileImg.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, SpriteTile.getTileImg(TileImg.HEAD), title);
        topSide.setSpacing(50);

        this.root.setTop(topSide);
    }

    private static Grid copyOfGrid(Grid old){
        Grid copy = new Grid(old.col, old.row);
        for (int i = 0; i < old.row; i++) {
            for (int j = 0; j < old.col; j++) {
                copy.getGridAt(j, i).setMovableContent(old.getGridAt(j, i).getMovableContent());
                copy.getGridAt(j, i).setImmovableContent(old.getGridAt(j, i).getImmovableContent());
            }
        }
        copy.set_player(old.getPlayerX(), old.getPlayerY());
        return copy;
    }
    private static GamePane copyOfSpecialPane(GamePane old){
        return new GamePane(copyOfGrid(old.getGrid()));
    }
}
