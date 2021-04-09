package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Tile;
import be.ac.umons.Sokoban.Entities.TileType;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;

public class LevelGenEvent implements EventHandler<MouseEvent> {
    private final LevelGenScene lvlScene;

    public LevelGenEvent(LevelGenScene levelGenScene){
        this.lvlScene = levelGenScene;
    }

    @Override
    public void handle(MouseEvent event) {
        if (event.getY() < lvlScene.getVisualGrid().getHeight()) {
            int row = (int) event.getY() / lvlScene.getVisualGrid().getSize();
            int col = (int) event.getX() / lvlScene.getVisualGrid().getSize();
            Tile aimedTile = lvlScene.getVisualGrid().getLgGridAt(row, col);
            if (aimedTile.isPlayer()) {
                lvlScene.setContainPlayer(false);
            }
            lvlScene.getVisualGrid().setAt(TileType.EMPTY, row, col);
            switch (lvlScene.getCurrModifier()) {
                case BOX:
                    //logic
                    aimedTile.setMovableObject(TileType.BOX);
                    aimedTile.setImmovableObject(TileType.EMPTY);
                    // visual
                    lvlScene.getVisualGrid().setAt(TileType.BOX_ICON, row, col);
                    break;
                case FLAG:
                    aimedTile.setImmovableObject(TileType.FLAG);
                    aimedTile.setMovableObject(TileType.EMPTY);

                    lvlScene.getVisualGrid().setAt(TileType.FLAG, row, col);
                    break;
                case WALL:
                    aimedTile.setMovableObject(TileType.EMPTY);
                    aimedTile.setImmovableObject(TileType.WALL);

                    lvlScene.getVisualGrid().setAt(TileType.WALL, row, col);
                    break;
                case PLAYER:
                    aimedTile.setImmovableObject(TileType.EMPTY);
                    aimedTile.setMovableObject(TileType.PLAYER);
                    lvlScene.setContainPlayer(true);
                    lvlScene.getVisualGrid().lG.setPlayerLocation(col, row);

                    lvlScene.getVisualGrid().setAt(TileType.HEAD, row, col);
                    break;
                case EMPTY:
                    aimedTile.setMovableObject(TileType.EMPTY);
                    aimedTile.setImmovableObject(TileType.EMPTY);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value");
            }

            if (aimedTile.isPlayer()) {
                lvlScene.resetCurrModifier();
            }
        }
    }
}
