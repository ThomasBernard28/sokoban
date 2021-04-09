package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteUI extends  SpriteAbstract{
    private final String YELLOW = "yellow";
    private final String RED = "red";
    private final String BLUE = "blue";
    private final String GREEN = "green";

    public enum TYPE{
        YELLOW_BUTTON02
    }
    public SpriteUI(String imagePath, int cellSize) {
        super(imagePath, cellSize);
    }

    private void setSPRITE(String color){
        this.SPRITE = new Image("images/"+ color + "Sheet.png");
    }

    public ImageView getUiPart(TYPE part){
        switch (part){
            case YELLOW_BUTTON02:
                setSPRITE(YELLOW);
                return getCell(0, 188, 190, 49);
            default:
                throw new IllegalStateException("Wrong part of ui");
        }
    }

}
