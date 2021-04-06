package be.ac.umons.Sokoban.JavaFX;

import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteAbstract {
    protected Image SPRITE;
    private final int cellSize;

    public SpriteAbstract(String imagePath, int cellSize){
        this.SPRITE = new Image(imagePath);
        this.cellSize = cellSize;
    }

    public int getCellSize() {
        return cellSize;
    }

    protected ImageView getCell(int row, int col){
        ImageView sprite = new ImageView(SPRITE);
        sprite.setViewport(new Rectangle2D(cellSize * col, cellSize * row, cellSize, cellSize));
        return sprite;
    }

    protected ImageView getCell(Rectangle2D rectangle2D){
        ImageView sprite = new ImageView(SPRITE);
        sprite.setViewport(rectangle2D);
        return sprite;
    }
}
