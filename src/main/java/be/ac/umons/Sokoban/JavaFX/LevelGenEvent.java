package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.ImmovableContent;
import be.ac.umons.Sokoban.Entities.MovableContent;
import be.ac.umons.Sokoban.Entities.Tile;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class LevelGenEvent implements EventHandler<MouseEvent> {

    @Override
    public void handle(MouseEvent event) {
        if (event.getY() < LevelGenScene.getVisualGrid().getHeight()) {
            int row = (int) event.getY() / SpriteTile.getSize();
            int col = (int) event.getX() / SpriteTile.getSize();
            Tile aimedTile = LevelGenScene.getVisualGrid().getGridAt(col, row);
            if (aimedTile.isPlayer()) {
                LevelGenScene.setContainPlayer(false);
            }
            LevelGenScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.EMPTY), col, row);
            switch (LevelGenScene.getCurrModifier()) {
                case BOX:
                    //logic
                    aimedTile.setMovableContent(MovableContent.BOX);
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
                    // visual
                    LevelGenScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.BOX_ICON), col, row);
                    break;
                case FLAG:
                    aimedTile.setImmovableContent(ImmovableContent.FLAG);
                    aimedTile.setMovableContent(MovableContent.EMPTY);

                    LevelGenScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.FLAG), col, row);
                    break;
                case WALL:
                    aimedTile.setMovableContent(MovableContent.EMPTY);
                    aimedTile.setImmovableContent(ImmovableContent.WALL);

                    LevelGenScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.WALL), col, row);
                    break;
                case PLAYER:
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
                    aimedTile.setMovableContent(MovableContent.PLAYER);
                    LevelGenScene.setContainPlayer(true);
                    LevelGenScene.getVisualGrid().getGrid().setPlayerLocation(col, row);

                    LevelGenScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.HEAD), col, row);
                    break;
                case EMPTY:
                    aimedTile.setMovableContent(MovableContent.EMPTY);
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value");
            }

            if (aimedTile.isPlayer()) {
                LevelGenScene.resetCurrModifier();
            }
        }
    }
}
