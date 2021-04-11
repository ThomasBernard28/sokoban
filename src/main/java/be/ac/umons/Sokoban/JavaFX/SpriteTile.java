package be.ac.umons.Sokoban.JavaFX;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteTile {
    public enum Size{
        SMALL,
        MEDIUM,
        LARGE;

        public int getSize(){
            switch (this){
                case LARGE:
                    return 64;
                case MEDIUM:
                    return 48;
                case SMALL:
                    return 32;
                default:
                    throw new IllegalStateException("Unexpected enum value");
            }
        }
    }


    private static Image gameSheet = new Image("images/tile_sheet_48.png");
    private static Size cellSize;

    public static int getSize(){
        return cellSize.getSize();
    }


    public static ImageView getTileImg(TileImg tile){
        ImageView tileView = new ImageView(gameSheet);
        tileView.setViewport(tile.getLocation());

        return tileView;
    }

    public static ImageView getTileImg(TileImg tile, double resizeFactor){
        ImageView tileView = getTileImg(tile);
        tileView.setScaleY(resizeFactor);
        tileView.setScaleX(resizeFactor);

        return tileView;
    }

    public static Image getGameSheet() {
        return gameSheet;
    }

    public static void setGameSheet(Size size){
        gameSheet = new Image("images/tile_sheet_" + size.getSize() + ".png");
        cellSize = size;
    }
}
