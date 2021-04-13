package be.ac.umons.Sokoban.JavaFX.Scenes;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.Tile;
import be.ac.umons.Sokoban.JavaFX.Sprite.SpriteTile;
import be.ac.umons.Sokoban.JavaFX.Sprite.TileImg;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;

public class GamePane extends Pane {

    private final Grid logicGrid;

    public GamePane(Grid logicGrid){
        this.logicGrid = logicGrid;
    }

    public Grid getGrid(){
        return logicGrid;
    }

    public Tile getGridAt(int x, int y){
        return logicGrid.getGridAt(x, y);
    }

    public Tile getGridFromPlayer(Direction direction){
        return logicGrid.getGridFromPlayer(direction);
    }

    public int getPlayerX(){
        return logicGrid.getPlayerX();
    }

    public int getPlayerY(){
        return logicGrid.getPlayerY();
    }

    public void setAt(ImageView imageView, int x, int y){
        this.getChildren().add(imageView);
        imageView.relocate(SpriteTile.getSize() * x, SpriteTile.getSize() * y);
    }

    public void setAt(ImageView imageView, int[] pixel){
        this.getChildren().add(imageView);
        imageView.relocate(pixel[0], pixel [1]);
    }

    public void setTileBg(int x, int y){
        if(logicGrid.getGridAt(x, y).isWall()){
            throw new IllegalStateException("Method used on an invalid tile (can't be a wall");
        }
        if(logicGrid.getGridAt(x, y).hasFlag()){
            setAt(SpriteTile.getTileImg(TileImg.FLAG), x, y);
        }else{
            setAt(SpriteTile.getTileImg(TileImg.EMPTY), x, y);
        }
    }

    public void initiate() {
        if(SpriteTile.getSize(true) != logicGrid.getSize()){
            SpriteTile.setGameSheet(logicGrid.getSize());
        }
        for (int i = 0; i < logicGrid.getSize().getRow(); i++) {
            for (int j = 0; j < logicGrid.getSize().getCol(); j++) {

                setAt(SpriteTile.getTileImg(TileImg.EMPTY), j, i);

                switch (logicGrid.getGridAt(j, i).getVisualType()) {
                    case BOX:
                        setAt(SpriteTile.getTileImg(TileImg.BOX), j, i);
                        break;
                    case FLAGGED_BOX:
                        setAt(SpriteTile.getTileImg(TileImg.FLAGGED_BOX), j, i);
                        break;
                    case WALL:
                        setAt(SpriteTile.getTileImg(TileImg.WALL), j, i);
                        break;
                    case PLAYER:
                        setAt(SpriteTile.getTileImg(TileImg.PLAYER), j, i);
                        break;
                    case FLAG:
                        setAt(SpriteTile.getTileImg(TileImg.FLAG), j, i);
                        break;
                    case EMPTY:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");

                }
            }
        }
        // clip
    }

    public void initiateLvlGen(){
        if(SpriteTile.getSize(true) != logicGrid.getSize()){
            SpriteTile.setGameSheet(logicGrid.getSize());
        }
        for (int i = 0; i < logicGrid.getSize().getRow(); i++) {
            for (int j = 0; j < logicGrid.getSize().getCol(); j++) {

                setAt(SpriteTile.getTileImg(TileImg.EMPTY), j, i);

                switch (logicGrid.getGridAt(j, i).getVisualType()) {
                    case BOX:
                        setAt(SpriteTile.getTileImg(TileImg.BOX_ICON), j, i);
                        break;
                    case FLAGGED_BOX:
                        setAt(SpriteTile.getTileImg(TileImg.FLAGGED_BOX), j, i);
                        break;
                    case WALL:
                        setAt(SpriteTile.getTileImg(TileImg.WALL), j, i);
                        break;
                    case PLAYER:
                        setAt(SpriteTile.getTileImg(TileImg.HEAD), j, i);
                        break;
                    case FLAG:
                        setAt(SpriteTile.getTileImg(TileImg.FLAG), j, i);
                        break;
                    case EMPTY:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");

                }
            }
        }
    }

    public void translation(ImageView imageView, Direction dir, int length, boolean withBox){
        setTileBg(logicGrid.getPlayerX() - dir.x, logicGrid.getPlayerY() - dir.y);
        setTileBg(logicGrid.getPlayerX(), logicGrid.getPlayerY());
        if(withBox){
            // player
            setAt(
                    imageView,
                    new int[] {logicGrid.getPlayerX() * SpriteTile.getSize() - length * dir.x,
                            logicGrid.getPlayerY() * SpriteTile.getSize() - length * dir.y}
            );
            // box
            setAt(
                    SpriteTile.getTileImg(TileImg.BOX),
                    new int[] {(logicGrid.getPlayerX() + dir.x) * SpriteTile.getSize() - length * dir.x,
                            (logicGrid.getPlayerY() + dir.y) * SpriteTile.getSize() - length * dir.y}
            );
        }else{
            setAt(
                    imageView,
                    new int[] {logicGrid.getPlayerX() * SpriteTile.getSize() - length * dir.x,
                            logicGrid.getPlayerY() * SpriteTile.getSize() - length * dir.y}
            );
        }
    }

    private static void clipChildren(Region region, double arc) {

        final Rectangle outputClip = new Rectangle();
        outputClip.setArcWidth(arc);
        outputClip.setArcHeight(arc);
        region.setClip(outputClip);

        region.layoutBoundsProperty().addListener((ov, oldValue, newValue) -> {
            outputClip.setWidth(newValue.getWidth());
            outputClip.setHeight(newValue.getHeight());
        });
    }

}
