package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.Player;
import com.sun.security.jgss.GSSUtil;
import javafx.animation.Animation;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class PlayerEvent implements EventHandler<KeyEvent> {
    private Grid grid;
    private SpecialPane gamePane;
    private Animation playerAnimation;
    private Animation boxAnimation;

    public PlayerEvent(Grid grid, SpecialPane gamePane){
        this.grid = grid;
        this.gamePane = gamePane;
        playerAnimation = new SpriteAnimation(Duration.millis(2000), 8, 3,
                                                gamePane.SIZE, gamePane.SIZE, gamePane);
        boxAnimation = new SpriteAnimation(Duration.millis(2000), 8, 1,
                                            gamePane.SIZE, gamePane.SIZE, gamePane);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())
        {
            //TODO place the animation method an find a way to know if there's a box in the direction
            case "z":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.UP))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.UP);
                }
                break;
            case "q":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.LEFT))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.LEFT);
                }
                break;
            case "s":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.DOWN))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.DOWN);
                }
                break;
            case "d":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.RIGHT))
                {
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.RIGHT);
                }
                break;
            case " ":
                /*
                grid = new Grid(grid.grid.length, grid.grid.length);
                grid.set_default_walls();
                grid.set_player(1,6);
                grid.set_boxes(grid.col/2, grid.row/2);
                grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
                (new MyWindow()).renderer(GUIGrid, grid, resources);*/
                break;
        }
    }
}
