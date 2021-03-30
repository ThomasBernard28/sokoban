package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class PlayerEvent implements EventHandler<KeyEvent> {
    private Grid grid;
    public PlayerEvent(Grid grid){
        this.grid = grid;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())
        {
            case "z":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, -1))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, -1);
                }
                break;
            case "q":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, -1, 0))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, -1, 0);
                }
                break;
            case "s":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, 1))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, 1);
                }
                break;
            case "d":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 1, 0))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 1, 0);
                }
                break;
            case " ":
                grid = new Grid(7,8);
                grid.set_default_walls();
                grid.set_player(1,6);
                grid.set_boxes(grid.col/2, grid.row/2);
                grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
                break;
        }
    }
}
