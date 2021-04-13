package be.ac.umons.Sokoban.JavaFX.Sprite;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteUI {
    public static Image sheet = new Image("images/yellowSheet.png");
    private static String currColor = "yellow";

    public static ImageView getUIImg(UIImg part) {
        setSheet(part);
        ImageView partView = new ImageView(sheet);
        partView.setViewport(part.getLocation());

        return partView;
    }

    private static void setSheet(UIImg part){
        if(!currColor.equals(part.getColor())){
            sheet = new Image("images/" + part.getColor() + "Sheet.png");
            currColor = part.getColor();
        }
    }
}
