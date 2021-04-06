package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URL;

public class MyWindow extends Application
{

    private static final int COLUMNS  =   3;
    private static final int COUNT    =  8;

    public static void main(String[] args)
    {
        launch(args);
    }

    //Method to create an image and ;oad it with absolute path
    public static Image createImage(Object context, String resourceName)
    {
        URL _url = context.getClass().getResource(resourceName);
        return new Image(_url.toExternalForm());
    }

    public void start(Stage theStage){
        /*
        // Level Gen scene
        */

        final int sizeX = 16;
        final int sizeY = 9;
        final int SIZE = 32;

        final Image imageHead = new Image("images/Head.png");

        final Paint lightOrangePaint = Color.valueOf("#FA8132");
        final Paint darkOrangePaint = Color.valueOf("#E86A17");
        final Paint lightBluePaint = Color.valueOf("#1EA7E1");
        final Paint darkBluePaint = Color.valueOf("#808080");

        BackgroundFill bgFillLightBlue = new BackgroundFill(lightBluePaint, new CornerRadii(1), null);
        BackgroundFill bgFillDarkOrange = new BackgroundFill(darkOrangePaint, new CornerRadii(1), null);
        BackgroundFill bgFillDarkBlue = new BackgroundFill(darkBluePaint, new CornerRadii(1), null);

        SpriteIcon iconGiver = new SpriteIcon("images/sheet_black1x.png", 50);
        SpriteGame cellGiver = new SpriteGame("images/tile_sheet_" + SIZE + ".png", SIZE);

        // root
        BorderPane root = new BorderPane();

        // Center
        Grid visualGrid = new Grid(sizeX, sizeY);
        SpecialPane visualPane = new SpecialPane(visualGrid, SIZE, new Image("images/tile_sheet_" + SIZE + ".png"));
        visualPane.initiate();

        // Right
        TilePane rightSide = new TilePane();
        rightSide.setOrientation(Orientation.VERTICAL);
        rightSide.setAlignment(Pos.CENTER);

        Button boxButton = new Button("Box   ");
        Button flagButton = new Button("Flag  ");
        Button playerButton = new Button("Player");
        Button wallButton = new Button("Wall  ");
        Button eraseButton = new Button("Erase ");


        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);

        //Bottom
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

        /*
        generate.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white; -fx-wrap-text: true;");
        reset.setStyle("-fx-padding: 20 40 40 30; -fx-wrap-text: true;-fx-font-weight: 700;-fx-font-size: 43px;");
        validate.setStyle("-fx-border-width: 3;-fx-min-width: 90px;-fx-cursor: hand;" +
                "-fx-border-color: transparent #E8E8E8 transparent transparent;");

         */

        bottomSide.add(generate, 0, 0);
        bottomSide.add(reset, 0, 1);
        bottomSide.add(sizePicker, 1,1);
        bottomSide.add(validate,2,0);
        bottomSide.add(play,2, 1);


        bottomSide.setBackground(new Background(bgFillLightBlue));


        //Left
        Rectangle leftSide = new Rectangle(0, 0, 20, visualGrid.getGrid().length * SIZE);
        leftSide.setFill(lightBluePaint);
        //Top
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

        ImageView playerHead = new ImageView(imageHead);
        playerHead.setScaleY(0.8);
        playerHead.setScaleX(0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, playerHead, title);
        topSide.setSpacing(40);

        topSide.setBackground(new Background(bgFillLightBlue));
        // Assembly
        root.setCenter(visualPane);
        root.setTop(topSide);
        root.setLeft(leftSide);
        root.setRight(rightSide);
        root.setBottom(bottomSide);

        Scene levelGenScene = new Scene(root);

        /*
        // Game scene
        */
        Scene gameScene = makeGameScene(4, 4, 48);

        // Stage part
        theStage.setTitle("Sokoban");
        theStage.getIcons().add(imageHead);

        theStage.setScene(levelGenScene);
        theStage.show();
    }

    public static Grid logicGridGenesis(int size){
        Grid grid = new Grid(size, size);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
        return grid;
    }

    public static Scene makeGameScene(int patRow, int patCol, int size){
        Grid logicGrid = logicGridGenesis(patRow * 3 + 2);
        SpecialPane gamePane = new SpecialPane(logicGrid, size, new Image("images/tile_sheet_" + size +".png"));
        gamePane.initiate();

        Scene gameScene = new Scene(gamePane);
        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(logicGrid, gamePane));

        return gameScene;
    }

    public static void setAt(Node child, int setX, int setY, Pane thePane){
        thePane.getChildren().add(child);
        child.relocate(setX, setY);
    }
}
enum CellSize{
    SMALL,
    MEDIUM,
    LARGE
}