package be.ac.umons.Sokoban.JavaFX;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class SpriteGame extends SpriteAbstract{
    public enum TileType {
        BOX,
        FLAGGED_BOX,
        PLAYER,
        WALL,
        FLAG,
        EMPTY,
        HEAD,
        BOX_ICON,
    }

    public SpriteGame() {
        super("images/tile_sheet_64.png", 64);
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
            case BOX_ICON:
                tileImg = getCell(3, 1);
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
        return tileImg;
    }
    public ImageView getTileImg(TileType tile, double resizeFactor){
        ImageView tileImg =  getTileImg(tile);
        tileImg.setScaleX(resizeFactor);
        tileImg.setScaleY(resizeFactor);
        return tileImg;
    }

    public void setSPRITE(int cellSize){
        this.cellSize = cellSize;
        this.SPRITE = new Image("images/tile_sheet_" + cellSize + ".png");
    }
}
