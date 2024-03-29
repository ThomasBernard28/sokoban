package be.ac.umons.bernardhofmanshouba.JavaFX.Sprite;

import javafx.geometry.Rectangle2D;

public enum UIImg{
    YELLOW_BUTTON02(0, 188, 190, 49, "yellow"),
    RED_BUTTON01(0, 45, 190, 49, "red"),
    YELLOW_BUTTON06(190, 194, 49, 49, "yellow"),
    RED_BUTTON06(288,198, 49, 49, "red" ),
    BLUE_BUTTON00(0, 94, 190, 49, "blue"),
    GREEN_BUTTON00(0, 0, 190, 49, "green"),
    YELLOW_BUTTON09(290, 94, 49,49, "yellow"),
    GREEN_BUTTON09(190,194,49,49,"green");

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
