package be.ac.umons.Sokoban.JavaFX;
import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.Tile;
import be.ac.umons.Sokoban.Entities.TileType;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class SpecialPane extends Pane {
    public final Grid lG;
    public final SpriteGame imgGiver;


    public SpecialPane(Grid grid){
        this.lG = grid;
        this.imgGiver = new SpriteGame(64);
    }

    public ImageView getBgCell(int i, int j){
        if(lG.getGridAt(j, i).hasFlag()){
            // return getCell(ImageType.FLAG);
            return imgGiver.getTileImg(TileType.FLAG);
        }else {
            // return getCell(ImageType.EMPTY);
            return imgGiver.getTileImg(TileType.EMPTY);
        }
    }
    public Tile getLgGridAt(int row, int col){
        return lG.getGridAt(col, row);
    }

    public void setAt(ImageView imageView, int row, int col){
        this.getChildren().add(imageView);
        imageView.relocate(col * imgGiver.getCellSize(), row * imgGiver.getCellSize());
    }

    public void setAt(ImageView imageView, int [] pixel){
        this.getChildren().add(imageView);
        imageView.relocate(pixel[0], pixel[1]);
    }

    public void setAt(TileType img, int row, int col){
        setAt(imgGiver.getTileImg(img), row, col);
    }

    public void initiate() {
        for (int i = 0; i < lG.row; i++) {
            for (int j = 0; j < lG.col; j++) {

                setAt(imgGiver.getTileImg(TileType.EMPTY), i, j);

                switch (lG.getGridAt(j, i).getVisualType()) {
                    case BOX:
                        setAt(imgGiver.getTileImg(TileType.BOX), i, j);
                        break;
                    case FLAGGED_BOX:
                        setAt(imgGiver.getTileImg(TileType.FLAGGED_BOX), i, j);
                        break;
                    case WALL:
                        setAt(imgGiver.getTileImg(TileType.WALL), i, j);
                        break;
                    case PLAYER:
                        setAt(imgGiver.getTileImg(TileType.PLAYER), i, j);
                        break;
                    case FLAG:
                        setAt(imgGiver.getTileImg(TileType.FLAG), i, j);
                        break;
                    case EMPTY:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");

                }
            }
        }
    }

    public void initiateLvlGen(){
        for (int i = 0; i < lG.row; i++) {
            for (int j = 0; j < lG.col; j++) {

                setAt(imgGiver.getTileImg(TileType.EMPTY), i, j);

                switch (lG.getGridAt(j, i).getVisualType()) {
                    case BOX:
                        setAt(imgGiver.getTileImg(TileType.BOX_ICON), i, j);
                        break;
                    case FLAGGED_BOX:
                        setAt(imgGiver.getTileImg(TileType.FLAGGED_BOX), i, j);
                        break;
                    case WALL:
                        setAt(imgGiver.getTileImg(TileType.WALL), i, j);
                        break;
                    case PLAYER:
                        setAt(imgGiver.getTileImg(TileType.HEAD), i, j);
                        break;
                    case FLAG:
                        setAt(imgGiver.getTileImg(TileType.FLAG), i, j);
                        break;
                    case EMPTY:
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value");

                }
            }
        }
    }
    public void setScale(int cellSize){
        imgGiver.setSPRITE(cellSize);
        initiate();
    }

    public void translationFull(ImageView imageView, Direction dir, int length, boolean withBox){
        setAt(
                getBgCell(lG.player[1] - dir.y, lG.player[0] - dir.x),
                lG.player[1] - dir.y, lG.player[0] - dir.x
        );
        setAt(
                getBgCell(lG.player[1], lG.player[0]),
                lG.player[1], lG.player[0]
        );
        if(withBox){
            // player
            setAt(
                    imageView,
                    new int[] {lG.player[0] * imgGiver.getCellSize() - length * dir.x,
                            lG.player[1] * imgGiver.getCellSize() - length * dir.y}
            );
            // box
            setAt(
                    imgGiver.getTileImg(TileType.BOX),
                    new int[] {(lG.player[0] + dir.x) * imgGiver.getCellSize() - length * dir.x,
                               (lG.player[1] + dir.y) * imgGiver.getCellSize() - length * dir.y}
            );
        }else{
            setAt(
                    imageView,
                    new int[] {lG.player[0] * imgGiver.getCellSize() - length * dir.x,
                            lG.player[1] * imgGiver.getCellSize() - length * dir.y}
            );
        }
    }
    public void lastBoxPrint(Direction dir, int length){
        if(lG.getGridFromPlayer(dir).isFlaggedBox()){
            setAt(
                    imgGiver.getTileImg(TileType.EMPTY),
                    new int[] {(lG.player[0] + dir.x) * imgGiver.getCellSize() - length * dir.x,
                            (lG.player[1] + dir.y) * imgGiver.getCellSize() - length * dir.y}
            );
            setAt(
                    imgGiver.getTileImg(TileType.FLAGGED_BOX),
                    new int[] {(lG.player[0] + dir.x) * imgGiver.getCellSize() - length * dir.x,
                            (lG.player[1] + dir.y) * imgGiver.getCellSize() - length * dir.y}
            );
        }
    }
    public int getSize(){
        return imgGiver.getCellSize();
    }

}
