package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LevelGenEvent implements EventHandler<MouseEvent> {
    private final LevelGenScene lvlScene;
    public LevelGenEvent(LevelGenScene levelGenScene){
        this.lvlScene = levelGenScene;

    }

    @Override
    public void handle(MouseEvent event) {
        int row = (int) event.getY() / lvlScene.getVisualGrid().getSize();
        int col = (int) event.getX() / lvlScene.getVisualGrid().getSize();
        Tile aimedTile = lvlScene.getVisualGrid().getLgGridAt(col, row);
        switch (lvlScene.getCurrModifier()){
            case BOX:
            case FLAG:
            case WALL:
            case PLAYER:
            case EMPTY:
        }
    }
}
