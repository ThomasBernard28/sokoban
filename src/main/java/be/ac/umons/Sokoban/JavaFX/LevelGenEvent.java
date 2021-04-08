package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.Entities.Tile;
import be.ac.umons.Sokoban.Entities.TileType;
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
        Tile aimedTile = lvlScene.getVisualGrid().getLgGridAt(row, col);
        switch (lvlScene.getCurrModifier()){
            case BOX:
                //logic
                aimedTile.setMovableObject(TileType.BOX);
                aimedTile.setImmovableObject(TileType.EMPTY);
                // visual
                lvlScene.getVisualGrid().setAt(TileType.BOX, row, col);
                System.out.println("Clicked for a box at " + row + "," + col);
                break;
            case FLAG:
                aimedTile.setImmovableObject(TileType.FLAG);
                aimedTile.setMovableObject(TileType.EMPTY);
                lvlScene.getVisualGrid().setAt(TileType.FLAG, row, col);
                System.out.println("Clicked for a flag at " + row + "," + col);
                break;
            case WALL:
                aimedTile.setMovableObject(TileType.EMPTY);
                aimedTile.setImmovableObject(TileType.WALL);
                System.out.println("Clicked for a wall at " + row + "," + col);
                break;

            case PLAYER:
                aimedTile.setImmovableObject(TileType.EMPTY);
                aimedTile.setMovableObject(TileType.PLAYER);
                System.out.println("Clicked for a player at " + row + "," + col);
                break;
            case EMPTY:
                aimedTile.setMovableObject(TileType.EMPTY);
                aimedTile.setImmovableObject(TileType.EMPTY);
                System.out.println("Clicked for a empty at " + row + "," + col);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        lvlScene.getVisualGrid().setAt(TileType.EMPTY, row, col);
        lvlScene.getVisualGrid().setAt(lvlScene.getCurrModifier(), row, col);
    }
}
