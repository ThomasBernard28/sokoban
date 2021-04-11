package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.ImmovableContent;
import be.ac.umons.Sokoban.Entities.MovableContent;
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
        if (event.getY() < lvlScene.getVisualGrid().getHeight()) {
            int row = (int) event.getY() / SpriteTile.getSize();
            int col = (int) event.getX() / SpriteTile.getSize();
            Tile aimedTile = lvlScene.getVisualGrid().getGridAt(col, row);
            if (aimedTile.isPlayer()) {
                lvlScene.setContainPlayer(false);
            }
            lvlScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.EMPTY), col, row);
            switch (lvlScene.getCurrModifier()) {
                case BOX:
                    //logic
                    aimedTile.setMovableContent(MovableContent.BOX);
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
                    // visual
                    lvlScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.BOX), col, row);
                    break;
                case FLAG:
                    aimedTile.setImmovableContent(ImmovableContent.FLAG);
                    aimedTile.setMovableContent(MovableContent.EMPTY);

                    lvlScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.FLAG), col, row);
                    break;
                case WALL:
                    aimedTile.setMovableContent(MovableContent.EMPTY);
                    aimedTile.setImmovableContent(ImmovableContent.WALL);

                    lvlScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.FLAG), col, row);
                    break;
                case PLAYER:
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
                    aimedTile.setMovableContent(MovableContent.PLAYER);
                    lvlScene.setContainPlayer(true);
                    lvlScene.getVisualGrid().getGrid().setPlayerLocation(col, row);

                    lvlScene.getVisualGrid().setAt(SpriteTile.getTileImg(TileImg.HEAD), col, row);
                    break;
                case EMPTY:
                    aimedTile.setMovableContent(MovableContent.EMPTY);
                    aimedTile.setImmovableContent(ImmovableContent.EMPTY);
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
