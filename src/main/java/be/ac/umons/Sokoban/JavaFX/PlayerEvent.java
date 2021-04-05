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
    private final SpriteAnimation playerAnimation;

    public PlayerEvent(Grid grid, SpecialPane gamePane){
        this.grid = grid;
        playerAnimation = new SpriteAnimation(Duration.millis(300), 8, 3,
                                                gamePane.SIZE, gamePane.SIZE, gamePane);
        playerAnimation.setCycleCount(1);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())

        {
            //TODO place the animation method an find a way to know if there's a box in the direction
            case "z":
                if(grid.getGridFromPlayer().checkMove(grid, Direction.UP) && playerAnimation.isNotRunning())
                {

                    playerAnimation.setDirection(Direction.UP);
                    playerAnimation.setAnimation(grid.getGridFromPlayer(Direction.UP).hasBox());
                    grid.getGridFromPlayer().Move(grid, Direction.UP);
                    playerAnimation.play();

                }
                break;
            case "q":
                if(grid.getGridFromPlayer().checkMove(grid, Direction.LEFT) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.LEFT);
                    playerAnimation.setAnimation(
                            grid.getGridFromPlayer(Direction.LEFT).hasBox()
                    );
                    grid.getGridFromPlayer().Move(grid, Direction.LEFT);
                    playerAnimation.play();
                }
                break;
            case "s":
                if(grid.getGridFromPlayer().checkMove(grid, Direction.DOWN) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.DOWN);
                    playerAnimation.setAnimation(
                            grid.getGridFromPlayer(Direction.DOWN).hasBox()
                    );
                    grid.getGridFromPlayer().Move(grid, Direction.DOWN);
                    playerAnimation.play();
                }
                break;
            case "d":
                if(grid.getGridFromPlayer().checkMove(grid, Direction.RIGHT) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.RIGHT);
                    playerAnimation.setAnimation(
                            grid.getGridFromPlayer(Direction.RIGHT).hasBox()
                    );
                    grid.getGridFromPlayer().Move(grid, Direction.RIGHT);
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
    }
}
