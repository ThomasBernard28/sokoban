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
    public final int SIZE;
    public final Image SPRITE;
    public final Grid lG;


    public SpecialPane(Grid grid, int SIZE, Image SPRITE){
        this.SIZE = SIZE;
        this.SPRITE = SPRITE;
        this.lG = grid;
    }

    public ImageView getCell(ImageType type){
        // TODO throw an error when no image has been located
        ImageView sprite = new ImageView(SPRITE);
        switch (type){
            case BOX:
                sprite.setViewport(new Rectangle2D(SIZE * 6,0, SIZE, SIZE ));
                return sprite;
            case FLAGGED_BOX:
                sprite.setViewport(new Rectangle2D(SIZE * 6,SIZE, SIZE, SIZE ));
                return sprite;
            case PLAYER:
                sprite.setViewport(new Rectangle2D(0, SIZE * 5, SIZE, SIZE));
                return sprite;
            case FLAG:
                sprite.setViewport(new Rectangle2D(SIZE * 11,SIZE * 7, SIZE, SIZE ));
                return sprite;
            case WALL:
                sprite.setViewport(new Rectangle2D(SIZE * 6,SIZE * 7, SIZE, SIZE ));
                return sprite;
            case EMPTY:
                sprite.setViewport(new Rectangle2D(SIZE * 11,SIZE * 6, SIZE, SIZE ));
                return sprite;

        }
        return new ImageView(SPRITE);
    }
    public ImageView getBgCell(int i, int j){
        if(lG.grid[i][j].hasFlag()){
            return getCell(ImageType.FLAG);
        }else {
            return getCell(ImageType.EMPTY);
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
                if(lG.grid[i][j].isBox()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.BOX), i, j);
                }
                else if(lG.grid[i][j].isFlaggedBox()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.FLAGGED_BOX), i, j);
                }
                else if(lG.grid[i][j].isWall()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.WALL), i, j);
                }
                else if(lG.grid[i][j].isPlayer()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.PLAYER), i, j);
                }
                else if(lG.grid[i][j].isFlag()){
                    setAt(getCell(ImageType.FLAG), i, j);
                }
                else if(lG.grid[i][j].isEmpty()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                }
            }
        }
    }
    public void translationPlayer(ImageView imageView, Direction dir, int length){
        // player has already been moved ie current visual coord = playerY - dirY, playerX - dirX
        setAt(getCell(ImageType.EMPTY), lG.player[1] - dir.y, lG.player[0] - dir.x);
        setAt(getCell(ImageType.EMPTY), lG.player[1], lG.player[0]);
        setAt(
                imageView,
                new int[] {lG.player[0] *SIZE - length * dir.x, lG.player[1] * SIZE - length * dir.y}
              );
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
            System.out.println("Anim with box");
            // player
            setAt(
                    imageView,
                    new int[] {lG.player[0] *SIZE - length * dir.x, lG.player[1] * SIZE - length * dir.y}
            );
            // box
            setAt(
                    getCell(ImageType.BOX),
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

}
