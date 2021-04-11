package be.ac.umons.Sokoban.JavaFX;

import javafx.geometry.Rectangle2D;

public enum IconImg {
    PLAY(7, 7),
    STOP(8, 2),
    EXIT(2, 8),
    RELOAD(1, 4),
    SAVE(9, 3),
    RESET(8, 8);

    private final static int cellSize = 50;

    private final int col;
    private final int row;

    IconImg(int row, int col){
        this.row = row;
        this.col = col;
    }

    public Rectangle2D getLocation(){
        return new Rectangle2D(cellSize * col, cellSize * row, cellSize, cellSize);
    }
}