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

import java.util.Arrays;

public class PlayerEvent implements EventHandler<KeyEvent> {
    private final Grid grid;
    private final SpecialPane gamePane;
    private final SpriteAnimation playerAnimation;
    private final SpriteAnimation boxAnimation;

    public PlayerEvent(Grid grid, SpecialPane gamePane){
        this.grid = grid;
        this.gamePane = gamePane;
        playerAnimation = new SpriteAnimation(Duration.millis(500), 8, 3,
                                                gamePane.SIZE, gamePane.SIZE, gamePane);
        boxAnimation = new SpriteAnimation(Duration.millis(1000), 8, 1,
                                            gamePane.SIZE, gamePane.SIZE, gamePane);
        playerAnimation.setCycleCount(1);
        boxAnimation.setCycleCount(1);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())
        {
            //TODO place the animation method an find a way to know if there's a box in the direction
            case "z":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.UP))
                {
                    playerAnimation.setDirection(Direction.UP);
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.UP);
                    playerAnimation.play();

                }
                break;
            case "q":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.LEFT))
                {
                    playerAnimation.setDirection(Direction.LEFT);
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.LEFT);
                    playerAnimation.play();
                }
                break;
            case "s":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.DOWN))
                {
                    playerAnimation.setDirection(Direction.DOWN);
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.DOWN);
                    playerAnimation.play();
                }
                break;
            case "d":
                if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, Direction.RIGHT))
                {
                    playerAnimation.setDirection(Direction.RIGHT);
                    grid.grid[grid.player[1]][grid.player[0]].Move(grid, Direction.RIGHT);
                    playerAnimation.play();
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
        System.out.println(Arrays.toString(grid.player));
    }
}
