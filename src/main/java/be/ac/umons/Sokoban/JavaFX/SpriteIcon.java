package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.ImageView;

public class SpriteIcon extends SpriteAbstract{
    public enum IconType{
        PLAY,
        STOP,
        EXIT,
        RELOAD,
        SAVE,
        RESET
    }

    public SpriteIcon(String imagePath, int cellSize) {
        super(imagePath, cellSize);
    }

    public ImageView getIcon(IconType icon){
        ImageView imgIcon;
        switch (icon){
            case EXIT:
                imgIcon = getCell(2, 8);
                break;
            case PLAY:
                imgIcon = getCell(7, 7);
                break;
            case STOP:
                imgIcon = getCell(8, 2);
                break;
            case RELOAD:
                imgIcon = getCell(1, 4);
                break;
            case SAVE:
                imgIcon = getCell(9,3);
                break;
            case RESET:
                imgIcon = getCell(8,8);
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + icon);
        }
        return imgIcon;
    }

}
