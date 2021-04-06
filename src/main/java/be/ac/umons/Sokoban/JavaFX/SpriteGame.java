package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteGame extends SpriteAbstract{
    public enum TileType {
        BOX,
        FLAGGED_BOX,
        PLAYER,
        WALL,
        FLAG,
        EMPTY,
        HEAD
    }

    public SpriteGame(String imagePath, int cellSize) {
        super(imagePath, cellSize);
    }

    public ImageView getTileImg(TileType tile){
        ImageView tileImg;
        switch (tile){
            case EMPTY:
                tileImg = getCell(6, 11);
                break;
            case BOX:
                tileImg = getCell(0, 6);
                break;
            case FLAGGED_BOX:
                tileImg = getCell(4, 6);
                break;
            case PLAYER:
                tileImg = getCell(5, 0);
                break;
            case FLAG:
                tileImg = getCell(7, 11);
                break;
            case WALL:
                tileImg = getCell(7, 6);
                break;
            case HEAD:
                tileImg = getCell(5,7);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        return tileImg;
    }

    public void setSprite(int size){
        this.SPRITE = new Image("tile_sheet_" + size +".png");
    }
}
