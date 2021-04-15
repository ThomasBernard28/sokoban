package be.ac.umons.Sokoban.JavaFX.Sprite;

import be.ac.umons.Sokoban.JavaFX.Size;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteTile;
import javafx.geometry.Rectangle2D;

public enum TileImg {
    PLAYER(5, 0),
    BOX(0, 6),
    FLAGGED_BOX(4, 6),
    BOX_ICON(3, 1),
    FLAG(7, 11),
    WALL(7, 6),
    EMPTY(6, 11),
    HEAD(5, 7);

    private final int col;
    private final int row;

    TileImg(int row, int col){
        this.col = col;
        this.row = row;
    }

    public Rectangle2D getLocation() {
        return new Rectangle2D(48 * col, 48 * row, 48, 48);
    }
}
