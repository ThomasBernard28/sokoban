package be.ac.umons.Sokoban.JavaFX.Sprite;

import javafx.geometry.Rectangle2D;

public enum UIImg{
    YELLOW_BUTTON02(0, 188, 190, 49, "yellow"),
    RED_BUTTON01(0, 45, 190, 49, "red");

    private final Rectangle2D location;
    private final String color;

    UIImg(int minX, int minY, int width, int height, String color){
        location = new Rectangle2D(minX, minY, width, height);
        this.color = color;
    }

    public Rectangle2D getLocation() {
        return location;
    }

    public String getColor() {
        return color;
    }
}
