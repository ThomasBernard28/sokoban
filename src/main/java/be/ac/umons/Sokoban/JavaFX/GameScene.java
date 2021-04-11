package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Test.ConsoleGrid;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class GameScene extends BorderPaneScene{

    private final static VBox root = new VBox();
    private final static HBox superRoot = new HBox(root);

    public GameScene(int patRow, int patCol){
        super(superRoot);
        topGenesis();
        centerGenesis(patRow * 3 + 2, patCol * 3 + 2);
        root.setStyle("-fx-padding: 0 100 100 100");
        superRoot.setBackground(new Background(bgFillLightBlue));
        superRoot.setAlignment(Pos.CENTER);
        root.setSpacing(100);

    }

    @Override
    protected void centerGenesis(int row, int col){
        // TODO change the resize factor of initiate method
        Grid grid = new Grid(col, row);
        grid.set_default_walls();
        grid.set_player(1,1);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2) + 1, (grid.row/2) + 1);

        GamePane gamePane = new GamePane(grid);
        gamePane.initiate();

        root.getChildren().add(gamePane);
        rootScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(grid, gamePane));
    }


    protected void rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(LEFT_MARGIN);

        //root.setRight(rightSide);
    }

    @Override
    protected void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);

        //root.setLeft(leftSide);
    }

    @Override
    protected void bottomGenesis(){
        TilePane bottomSide = new TilePane();

        bottomSide.setMinHeight(LEFT_MARGIN);

        //root.setBottom(bottomSide);
    }

    @Override
    protected void topGenesis(){
        HBox topSide = new HBox();

        Label title = new Label("Sokoban");
        title.setFont(getFont(50));
        title.setStyle("-fx-padding: 20 20 20 20;");
        topSide.setStyle("-fx-padding: 70 50 20 50");

        Button exitButton = makeToMenuButton();
        exitButton.setScaleX(1);
        exitButton.setScaleY(1);

        topSide.getChildren().addAll(exitButton, title);
        topSide.setSpacing(50);
        root.getChildren().add(topSide);

    }
}
