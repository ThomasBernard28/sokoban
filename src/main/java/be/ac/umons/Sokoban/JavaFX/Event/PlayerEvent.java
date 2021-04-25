package be.ac.umons.Sokoban.JavaFX.Event;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.JavaFX.Scenes.AnimationPlayerMove;
import be.ac.umons.Sokoban.JavaFX.Scenes.GamePane;
import be.ac.umons.Sokoban.JavaFX.Scenes.GameScene2;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.util.Duration;

import java.util.ArrayList;

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

        Direction dir = Direction.match(event.getText());
        if(dir != null) {
            if (logicGrid.getGridFromPlayer().checkMove(logicGrid, dir) && playerAnimation.isNotRunning()) {
                playerAnimation.setDirection(dir);
                playerAnimation.setAnimation(
                        logicGrid.getGridFromPlayer(dir).hasBox()
                );
                logicGrid.getGridFromPlayer().move(logicGrid, dir);
                playerAnimation.play();
                GameScene2.history(event.getText());
            }
        }

        if (logicGrid.checkWin()){
            GameScene2.victory();
        }
    }
}
