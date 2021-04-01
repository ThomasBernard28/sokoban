package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Grid;
import com.sun.security.jgss.GSSUtil;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;

public class PlayerEvent implements EventHandler<KeyEvent> {
    private Grid grid;
    private GridPane GUIGrid;
    private Resources resources;
    public PlayerEvent(Grid grid, GridPane GUIGrid, Resources resources){
        this.grid = grid;
        this.GUIGrid = GUIGrid;
        this.resources = resources;
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())
        {
            case "z":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, -1))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, -1);
                    move_renderer(0, -1);
                }
                break;
            case "q":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, -1, 0))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, -1, 0);
                    move_renderer(-1, 0);
                }
                break;
            case "s":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, 1))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, 1);
                    move_renderer(0, 1);
                }
                break;
            case "d":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 1, 0))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, 1, 0);
                    move_renderer(1, 0);
                }
                break;
            case " ":
                grid = new Grid(grid.grid.length, grid.grid.length);
                grid.set_default_walls();
                grid.set_player(1,6);
                grid.set_boxes(grid.col/2, grid.row/2);
                grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
                (new MyWindow()).renderer(GUIGrid, grid, resources);
                break;
        }
    }
    private void move_renderer(int directionX, int directionY){
        GUIGrid.add(new ImageView(resources.getPlayer()), grid.player[0], grid.player[1]);
        int j = grid.player[0] - directionX;
        int i = grid.player[1] - directionY;

        if(grid.grid[grid.player[1] + directionY][grid.player[0] + directionX].isBox()){
            GUIGrid.add(new ImageView(resources.getBox()), grid.player[0] + directionX, grid.player[1] + directionY);
        }
        if(grid.grid[grid.player[1] + directionY][grid.player[0] + directionX].isFlaggedBox()){
            GUIGrid.add(new ImageView(resources.getBoxFlagged()), grid.player[0] + directionX, grid.player[1] + directionY);
        }
        if(grid.grid[i][j].isFlag()){
            GUIGrid.add(new ImageView(resources.getFlag()), j, i);
        }
        if(grid.grid[i][j].isEmpty()){
            GUIGrid.add(new ImageView(resources.getBg()), j, i);
        }
    }
}
