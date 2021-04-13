package be.ac.umons.Sokoban.JavaFX.Event;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.AnimationPlayerMove;
import be.ac.umons.Sokoban.JavaFX.Scenes.GamePane;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

public class PlayerEvent implements EventHandler<KeyEvent> {
    private final Grid logicGrid;
    private final AnimationPlayerMove playerAnimation;

    public PlayerEvent(GamePane gamePane){
        this.logicGrid = gamePane.getGrid();

        playerAnimation = new AnimationPlayerMove(gamePane, Duration.millis(300));

        playerAnimation.setCycleCount(1);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getText())

        {
            case "z":
                if(logicGrid.getGridFromPlayer().checkMove(logicGrid, Direction.UP) && playerAnimation.isNotRunning())
                {

                    playerAnimation.setDirection(Direction.UP);
                    playerAnimation.setAnimation(logicGrid.getGridFromPlayer(Direction.UP).hasBox());
                    logicGrid.getGridFromPlayer().move(logicGrid, Direction.UP);
                    playerAnimation.play();
                    System.out.println("z");

                }
                break;
            case "q":
                if(logicGrid.getGridFromPlayer().checkMove(logicGrid, Direction.LEFT) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.LEFT);
                    playerAnimation.setAnimation(
                            logicGrid.getGridFromPlayer(Direction.LEFT).hasBox()
                    );
                    logicGrid.getGridFromPlayer().move(logicGrid, Direction.LEFT);
                    playerAnimation.play();
                }
                break;
            case "s":
                if(logicGrid.getGridFromPlayer().checkMove(logicGrid, Direction.DOWN) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.DOWN);
                    playerAnimation.setAnimation(
                            logicGrid.getGridFromPlayer(Direction.DOWN).hasBox()
                    );
                    logicGrid.getGridFromPlayer().move(logicGrid, Direction.DOWN);
                    playerAnimation.play();
                }
                break;
            case "d":
                if(logicGrid.getGridFromPlayer().checkMove(logicGrid, Direction.RIGHT) && playerAnimation.isNotRunning())
                {
                    playerAnimation.setDirection(Direction.RIGHT);
                    playerAnimation.setAnimation(
                            logicGrid.getGridFromPlayer(Direction.RIGHT).hasBox()
                    );
                    logicGrid.getGridFromPlayer().move(logicGrid, Direction.RIGHT);
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
