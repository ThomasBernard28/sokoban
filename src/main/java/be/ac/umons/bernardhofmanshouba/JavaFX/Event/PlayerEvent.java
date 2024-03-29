package be.ac.umons.bernardhofmanshouba.JavaFX.Event;

import be.ac.umons.bernardhofmanshouba.Entities.Direction;
import be.ac.umons.bernardhofmanshouba.JavaFX.Scenes.*;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
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

        Direction dir = Direction.match(event.getText());
        if(dir != null) {
            if (logicGrid.getGridFromPlayer().checkMove(logicGrid, dir) && playerAnimation.isNotRunning()) {
                playerAnimation.setDirection(dir);
                playerAnimation.setAnimation(
                        logicGrid.getGridFromPlayer(dir).hasBox()
                );
                logicGrid.getGridFromPlayer().move(logicGrid, dir);
                playerAnimation.play();
                if (SceneTool.getWINDOW().getScene() == SceneTool.SceneList.GAME.getScene()){
                    GameScene.history(event.getText());
                }
                else {
                    GameGenScene.history(event.getText());
                }

            }
        }

        if (logicGrid.checkWin()){
            if (SceneTool.getWINDOW().getScene() == SceneTool.SceneList.GAME.getScene()){
                GameScene.victory();
            }
            else{
                GameGenScene.victory();
            }

        }
    }
}
