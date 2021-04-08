package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;

public class GameScene extends BorderPaneScene{
    public GameScene(int patRow, int patCol){
        centerGenesis(patRow * 3 + 2, patCol * 3 + 2);
    }

    private void centerGenesis(int row, int col){
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
    private void rightGenesis(){

    }
    private void leftGenesis(){

    }
    private void bottomGenesis(){

    }
    private void topGenesis(){

    }
}
