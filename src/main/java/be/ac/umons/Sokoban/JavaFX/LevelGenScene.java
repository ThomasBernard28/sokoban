package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.TileType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
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

    private void centerGenesis(){
        SpecialPane visualPane = new SpecialPane(new Grid(COLUMNS, ROWS));
        visualPane.initiate();
        this.visualGrid = visualPane;
        this.root.setCenter(visualPane);
        visualPane.addEventHandler(MouseEvent.MOUSE_CLICKED, new LevelGenEvent(this));
    }

    private void rightGenesis(){
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

        final String buttonCSS = "-fx-background-color: transparent;fx-border-style: solid;" +
                "-fx-border-width: 2; -fx-border-color: #1989B8;-fx-cursor: hand";

        boxButton.setStyle(buttonCSS);
        flagButton.setStyle(buttonCSS);
        wallButton.setStyle(buttonCSS);
        playerButton.setStyle(buttonCSS);
        eraseButton.setStyle(buttonCSS);

        // logic part
        boxButton.setOnAction(event -> { currModifier = TileType.BOX;
        });
        flagButton.setOnAction(event -> { currModifier = TileType.FLAG;
        });
        wallButton.setOnAction(event -> { currModifier = TileType.WALL;
        });
        playerButton.setOnAction(event -> { currModifier = TileType.PLAYER;
        });
        eraseButton.setOnAction(event -> { currModifier = TileType.EMPTY;
        });

        // end of logic part

        rightSide.getChildren().addAll(boxButton, flagButton, wallButton, playerButton, eraseButton);

        rightSide.setVgap(20);
        rightSide.setBackground(new Background(bgFillLightBlue));
        rightSide.setMinSize(80, 0);
        rightSide.setStyle("-fx-padding: 10 10 10 10");

        this.root.setRight(rightSide);
    }

    public void bottomGenesis(){
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


    public void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);
        leftSide.setMinHeight(ROWS * SIZE);
        leftSide.setBackground(new Background(bgFillLightBlue));
        this.root.setLeft(leftSide);
    }

    public void topGenesis(){
        // Making things pretty
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
        // little complexity
        exitButton.setOnAction(event -> SceneSwitcher.switchScene(SceneSwitcher.gameScene));
        // making things pretty again

        ImageView playerHead = cellGiver.getTileImg(TileType.HEAD, 0.8);
        playerHead.setStyle("-fx-padding: 30 20 20 20;");

        topSide.getChildren().addAll(exitButton, cellGiver.getTileImg(TileType.HEAD), title);
        topSide.setSpacing(50);

        topSide.setBackground(new Background(bgFillLightBlue));
        this.root.setTop(topSide);
    }
}
