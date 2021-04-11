package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteIcon {
    private final static Image iconSheet = new Image("images/icon_sheet.png");

    public static ImageView getIconImg(IconImg icon){
        ImageView iconView = new ImageView(iconSheet);
        iconView.setViewport(icon.getLocation());

        return iconView;
    }

    public static ImageView getIconImg(IconImg icon, double resizeFactor){
        ImageView iconView = getIconImg(icon);
        iconView.setScaleX(resizeFactor);
        iconView.setScaleY(resizeFactor);

        return iconView;
    }
}
