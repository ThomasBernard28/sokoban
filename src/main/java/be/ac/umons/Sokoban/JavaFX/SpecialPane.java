package be.ac.umons.Sokoban.JavaFX;
import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

enum ImageType{
    BOX,
    FLAGGED_BOX,
    PLAYER,
    WALL,
    FLAG,
    EMPTY
}

public class SpecialPane extends Pane {
    public final int SIZE = 64;
    public final Grid lG;
    public final SpriteGame imgGiver;


    public SpecialPane(Grid grid){
        this.lG = grid;
        this.imgGiver = new SpriteGame();
    }

    public ImageView getBgCell(int i, int j){
        if(lG.getGridAt(j, i).hasFlag()){
            // return getCell(ImageType.FLAG);
            return imgGiver.getTileImg(SpriteGame.TileType.FLAG);
        }else {
            // return getCell(ImageType.EMPTY);
            return imgGiver.getTileImg(SpriteGame.TileType.EMPTY);
        }
    }

    public void setAt(ImageView imageView, int row, int col){
        this.getChildren().add(imageView);
        imageView.relocate(col * SIZE, row * SIZE);
    }

    public void setAt(ImageView imageView, int [] pixel){
        this.getChildren().add(imageView);
        imageView.relocate(pixel[0], pixel[1]);
    }

    public void initiate(){
        for(int i = 0; i < lG.row; i++){
            for(int j = 0; j <lG.col; j++){
                if(lG.getGridAt(j, i).isBox()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.BOX), i, j);
                }
                else if(lG.getGridAt(j, i).isFlaggedBox()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.FLAGGED_BOX), i, j);
                }
                else if(lG.getGridAt(j, i).isWall()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.WALL), i, j);
                }
                else if(lG.getGridAt(j, i).isPlayer()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.PLAYER), i, j);
                }
                else if(lG.getGridAt(j, i).isFlag()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.FLAG), i, j);
                }
                else if(lG.getGridAt(j, i).isEmpty()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY), i, j);
                }
            }
        }
    }
    public void initiate(double resizeFactor){
        for(int i = 0; i < lG.row; i++){
            for(int j = 0; j <lG.col; j++){
                if(lG.getGridAt(j, i).isBox()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY, resizeFactor), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.BOX, resizeFactor), i, j);
                }
                else if(lG.getGridAt(j, i).isFlaggedBox()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY, resizeFactor), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.FLAGGED_BOX, resizeFactor), i, j);
                }
                else if(lG.getGridAt(j, i).isWall()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY, resizeFactor), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.WALL, resizeFactor), i, j);
                }
                else if(lG.getGridAt(j, i).isPlayer()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY, resizeFactor), i, j);
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.PLAYER, resizeFactor), i, j);
                }
                else if(lG.getGridAt(j, i).isFlag()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.FLAG, resizeFactor), i, j);
                }
                else if(lG.getGridAt(j, i).isEmpty()){
                    setAt(imgGiver.getTileImg(SpriteGame.TileType.EMPTY, resizeFactor), i, j);
                }
            }
        }
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
                    new int[] {lG.player[0] *SIZE - length * dir.x, lG.player[1] * SIZE - length * dir.y}
            );
            // box
            setAt(
                    imgGiver.getTileImg(SpriteGame.TileType.BOX),
                    new int[] {(lG.player[0] + dir.x) * SIZE - length * dir.x,
                               (lG.player[1] + dir.y) * SIZE - length * dir.y}
            );
        }else{
            setAt(
                    imageView,
                    new int[] {lG.player[0] *SIZE - length * dir.x, lG.player[1] * SIZE - length * dir.y}
            );
        }
    }
    public void lastBoxPrint(Direction dir, int length){
        if(lG.getGridFromPlayer(dir).isFlaggedBox()){
            setAt(
                    imgGiver.getTileImg(SpriteGame.TileType.EMPTY),
                    new int[] {(lG.player[0] + dir.x) * SIZE - length * dir.x,
                            (lG.player[1] + dir.y) * SIZE - length * dir.y}
            );
            setAt(
                    imgGiver.getTileImg(SpriteGame.TileType.FLAGGED_BOX),
                    new int[] {(lG.player[0] + dir.x) * SIZE - length * dir.x,
                            (lG.player[1] + dir.y) * SIZE - length * dir.y}
            );
        }
    }

}
