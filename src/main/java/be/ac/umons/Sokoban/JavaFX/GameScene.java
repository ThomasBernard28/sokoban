package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;

public class GameScene extends BorderPaneScene{
    public GameScene(int patRow, int patCol){
        centerGenesis(patRow * 3 + 2, patCol * 3 + 2);
        rightGenesis();
        leftGenesis();
        bottomGenesis();
        topGenesis();
    }

    @Override
    protected void centerGenesis(int row, int col){
        // TODO change the resize factor of initiate method
        Grid grid = new Grid(col, row);
        grid.set_default_walls();
        grid.set_player(1,1);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2) + 1, (grid.row/2) + 1);

        SpecialPane gamePane = new SpecialPane(grid);
        gamePane.initiate();

        root.setCenter(gamePane);
        rootScene.addEventHandler(KeyEvent.KEY_PRESSED, new PlayerEvent(grid, gamePane));
    }

    @Override
    protected void rightGenesis(){
        VBox rightSide = new VBox();

        rightSide.setMinWidth(LEFT_MARGIN);

        root.setRight(rightSide);
    }

    @Override
    protected void leftGenesis(){
        VBox leftSide = new VBox();

        leftSide.setMinWidth(LEFT_MARGIN);

        root.setLeft(leftSide);
    }

    @Override
    protected void bottomGenesis(){
        TilePane bottomSide = new TilePane();

        bottomSide.setMinHeight(LEFT_MARGIN);

        root.setBottom(bottomSide);
    }

    @Override
    protected void topGenesis(){
        TilePane topSide = new TilePane();

        Label title = new Label("Sokoban");
        title.setFont(getFont(30));
        title.setStyle("-fx-padding: 20 20 20 20;");

        Button exitButton = makeExitButton(SceneSwitcher.UniqueScene.MENU);

        topSide.getChildren().addAll(exitButton, title);
        root.setTop(topSide);

    }
}
