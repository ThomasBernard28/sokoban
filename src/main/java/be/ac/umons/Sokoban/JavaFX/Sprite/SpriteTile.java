package be.ac.umons.Sokoban.JavaFX.Sprite;

import be.ac.umons.Sokoban.JavaFX.Size;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class SpriteTile {

    private static Image gameSheet = new Image("images/tile_sheet_48.png");
    private static Size cellSize = Size.MEDIUM;

    public static int getSize(){
        return cellSize.getSize();
    }
    public static Size getSize(boolean trueSize){
        return cellSize;
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

    public static ImageView getTileImg(TileImg tile, int cellSize, boolean nothing){
        ImageView tileView = getTileImg(tile);
        tileView.setFitHeight(cellSize);
        tileView.setFitWidth(cellSize);
        return tileView;
    }

    public static Image getGameSheet() {
        return gameSheet;
    }

    public static void setGameSheet(Size size){
        gameSheet = new Image("images/tile_sheet_" + size.getSize() + ".png");
        cellSize = size;
        TileImg.setCellSize(size);
    }
}
