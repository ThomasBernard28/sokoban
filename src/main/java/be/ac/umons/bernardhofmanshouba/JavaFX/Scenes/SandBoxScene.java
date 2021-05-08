package be.ac.umons.bernardhofmanshouba.JavaFX.Scenes;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.JavaFX.*;
import be.ac.umons.bernardhofmanshouba.JavaFX.Event.LevelGenEvent;
import be.ac.umons.bernardhofmanshouba.JavaFX.Event.PlayerEvent;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.IconImg;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteIcon;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.SpriteTile;
import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.TileImg;
import be.ac.umons.bernardhofmanshouba.Save.Path;
import be.ac.umons.bernardhofmanshouba.Save.Save;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Font;

import java.io.IOException;
import java.util.regex.Pattern;

public class SandBoxScene extends SceneTool {

    private final static BorderPane root = new BorderPane();
    //private final static GridPane superRoot = new GridPane();

    private static GamePane visualGrid = null;
    private static TileImg currModifier = TileImg.EMPTY;
    private static boolean containPlayer = false;

    private static GamePane gridToTry = null;
    private static PlayerEvent eventToTry = null;

    private static Button resetButton = null;

    @SuppressWarnings("Convert2MethodRef")
    private final static EventHandler<MouseEvent> filter = event -> event.consume();

    private final static int MARGIN = 30;

    public static void makeScene(){
        Scene scene = new Scene(root); //
        SceneList.SANDBOX.setScene(scene);
        setCurrSize(Size.MEDIUM);

        root.setBackground(new Background(bgFillLightBlue)); //

        root.setTop(topGenesis());
        root.setBottom(bottomGenesis());
        root.setLeft(leftGenesis());

        root.setCenter(centerRowGenesis(centerLeftGenesis(), _centerRightGenesis()));

    }

    public static TileImg getCurrModifier(){
        return currModifier;
    }

    public static GamePane getVisualGrid() {
        return visualGrid;
    }

    public static void setContainPlayer(boolean hasPlayer){
        containPlayer = hasPlayer;
    }

    public static void resetCurrModifier(){
        currModifier = TileImg.EMPTY;
    }

    protected static void resetScene(){
        resetButton.fire();
    }

    private static HBox centerRowGenesis(GamePane gamePane, TilePane tilePane){
        HBox centerRow = new HBox();

        centerRow.getChildren().addAll(gamePane, tilePane);
        return centerRow;
    }

    private static GamePane centerLeftGenesis(){
        GamePane centerLeft = new GamePane(new Grid(currSize));
        centerLeft.addEventHandler(MouseEvent.MOUSE_CLICKED, new LevelGenEvent());
        visualGrid = centerLeft;
        centerLeft.initiateLvlGen();

        return centerLeft;
    }

    private static TilePane _centerRightGenesis(){

        TilePane centerRight = new TilePane();
        centerRight.setOrientation(Orientation.VERTICAL);
        centerRight.setAlignment(Pos.CENTER);

        StyleBtn boxButton = new StyleBtn(TileImg.BOX, StyleBtn.UnpressedCSS, null, "Box");
        StyleBtn flagButton = new StyleBtn(TileImg.FLAG, StyleBtn.UnpressedCSS, null, "Flag");
        StyleBtn playerButton = new StyleBtn(TileImg.PLAYER, StyleBtn.UnpressedCSS, null, "Player");
        StyleBtn wallButton = new StyleBtn(TileImg.WALL, StyleBtn.UnpressedCSS, null, "Wall");
        StyleBtn eraseButton = new StyleBtn(IconImg.CROSS, StyleBtn.UnpressedCSS, null, "Erase");

        // logic part
        boxButton.setOnAction(event -> {
            currModifier = TileImg.BOX;

            for (Node child: centerRight.getChildren()) {
                child.setStyle(StyleBtn.UnpressedCSS);
            }
            boxButton.setStyle(StyleBtn.PressedCSS);


        });
        flagButton.setOnAction(event -> {
            currModifier = TileImg.FLAG;

            for (Node child: centerRight.getChildren()) {
                child.setStyle(StyleBtn.UnpressedCSS);
            }
            flagButton.setStyle(StyleBtn.PressedCSS);
        });
        wallButton.setOnAction(event -> {
            currModifier = TileImg.WALL;

            for (Node child: centerRight.getChildren()) {
                child.setStyle(StyleBtn.UnpressedCSS);
            }
            wallButton.setStyle(StyleBtn.PressedCSS);
        });
        eraseButton.setOnAction(event -> {
            currModifier = TileImg.EMPTY;

            for (Node child: centerRight.getChildren()) {
                child.setStyle(StyleBtn.UnpressedCSS);
            }
            eraseButton.setStyle(StyleBtn.PressedCSS);
        });
        playerButton.setOnAction(event -> {
            if(!containPlayer){
                currModifier = TileImg.PLAYER;
                for (Node child: centerRight.getChildren()) {
                    child.setStyle(StyleBtn.UnpressedCSS);
                }
                playerButton.setStyle(StyleBtn.PressedCSS);
            }
        });

        // end of logic part

        centerRight.setVgap(20);
        centerRight.setMinSize(80, 0);
        centerRight.setStyle("-fx-padding: 10 10 10 10");

        centerRight.setAlignment(Pos.CENTER);

        centerRight.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        return centerRight;
    }

    private static GridPane bottomGenesis(){
        GridPane bottomSide = new GridPane();

        TextField fileOutput = new TextField();
        fileOutput.setFont(new Font("arial", 20));

        ObservableList<Size> diffSize = FXCollections.observableArrayList(Size.values());
        ListView<Size> sizePicker = new ListView<Size>(diffSize);

        sizePicker.setMaxHeight(90);
        sizePicker.setFixedCellSize(29);
        sizePicker.setMaxWidth(200);
        sizePicker.setScaleY(1.2);
        sizePicker.setScaleX(1.2);
        sizePicker.setStyle("-fx-border-insets: 1000");


        StyleBtn generate = new StyleBtn(IconImg.RELOAD, StyleBtn.classicCSS, bgFillGray, null);
        StyleBtn reset = new StyleBtn(IconImg.RESET, StyleBtn.classicCSS, bgFillDarkOrange, null);
        resetButton = reset;
        StyleBtn save = new StyleBtn(IconImg.SAVE, StyleBtn.classicCSS, bgFillGray, null);
        StyleBtn play = new StyleBtn(IconImg.PLAY, StyleBtn.classicCSS, bgFillGreen, null);
        StyleBtn stop = new StyleBtn(IconImg.STOP, StyleBtn.classicCSS, bgFillDarkOrange, null);


        // logic part
        play.setOnAction(event -> {
            if(containPlayer && gridToTry == null) {
                gridToTry = copyOfSpecialPane(visualGrid);
                eventToTry = new PlayerEvent(gridToTry);

                root.setCenter(centerRowGenesis(gridToTry, _centerRightGenesis()));
                SceneList.SANDBOX.getScene().addEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
                SceneList.SANDBOX.getScene().addEventFilter(MouseEvent.MOUSE_CLICKED, filter);

                gridToTry.initiate();
            }
        });

        stop.setOnAction(event -> {
            // if not null then play mode is active
            if(gridToTry != null){
                SceneList.SANDBOX.getScene().removeEventHandler(KeyEvent.KEY_PRESSED, eventToTry);
                SceneList.SANDBOX.getScene().removeEventFilter(MouseEvent.MOUSE_CLICKED, filter);
                root.setCenter(centerRowGenesis(visualGrid, _centerRightGenesis()));

                gridToTry = null;
                eventToTry = null;

                visualGrid.initiateLvlGen();
            }
        });

        reset.setOnAction(event -> {
            stop.fire();
            resetCurrModifier();
            containPlayer = false;
            visualGrid.getGrid().resetGrid();
            visualGrid.initiate();
        });

        save.setOnAction(event -> {
            CharSequence output = fileOutput.getCharacters();
            // https://regex101.com/
            if (Pattern.matches("^(\\w|_)+$", output)){
                try {
                    Save.saving(visualGrid.getGrid(), Path.SAVE, output.toString() + ".xsb");
                    fileOutput.clear();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }

        });

        generate.setOnAction(event -> {
            reset.fire();
            visualGrid.getGrid().constructMovables(5,150);
            visualGrid.initiateLvlGen();
            setContainPlayer(true);
        });

        sizePicker.setOnMouseClicked(event -> {
            stop.fire();
            containPlayer = false;
            Size selectedSize = sizePicker.getSelectionModel().getSelectedItem();
            setCurrSize(selectedSize);

            root.setCenter(centerRowGenesis(centerLeftGenesis(), _centerRightGenesis()));
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
        bottomSide.getColumnConstraints().add(new ColumnConstraints((currSize.getCol() * SpriteTile.getSize())/ 2.0));
        bottomSide.setPadding(new Insets(MARGIN, MARGIN, MARGIN, MARGIN));
        bottomSide.setHgap(20);

        GridPane.setHalignment(sizePicker, HPos.CENTER);
        GridPane.setHalignment(stop, HPos.LEFT);
        GridPane.setHalignment(play, HPos.CENTER);
        GridPane.setHalignment(save, HPos.CENTER);
        GridPane.setHalignment(fileOutput, HPos.LEFT);



        return bottomSide;
    }

    private static VBox leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(MARGIN);
        leftSide.setMinHeight(currSize.getRow() * SpriteTile.getSize());
        return leftSide;
    }

    private static HBox topGenesis(){
        // Making things pretty
        HBox topSide = new HBox();

        Label title = new Label("Level Build Tool");
        title.setFont(Font.font("impact", 35));
        title.setStyle("-fx-padding: 20 20 20 20;");

        ExitButton exitButton = new ExitButton(SceneList.MENU);
        exitButton.setScale(0.75);

        ImageView playerHead = SpriteTile.getTileImg(TileImg.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, SpriteTile.getTileImg(TileImg.HEAD), title);
        topSide.setAlignment(Pos.CENTER_LEFT);
        topSide.setSpacing(50);

        return topSide;
    }

}



