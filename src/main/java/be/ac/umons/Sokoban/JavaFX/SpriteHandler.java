package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;

public class SpriteHandler {
    private final Image sprite;
    private final int cellSize;

    public SpriteHandler(String imagePath, int cellSize){
        this.sprite = new Image(imagePath);
        this.cellSize = cellSize;
    }
}
