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
    public final Grid logicGrid;

    public SpecialPane(Grid grid, int SIZE, Image SPRITE){
        this.SIZE = SIZE;
        this.SPRITE = SPRITE;
        this.logicGrid = grid;
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

    public void setAt(ImageView imageView, int row, int col){
        this.getChildren().add(imageView);
        imageView.relocate(col * SIZE, row * SIZE);
    }

    public void setAt(ImageView imageView, int [] pixel){
        this.getChildren().add(imageView);
        imageView.relocate(pixel[0], pixel[1]);
    }

    public void initiate(){
        for(int i = 0; i < logicGrid.row; i++){
            for(int j = 0; j <logicGrid.col; j++){
                if(logicGrid.grid[i][j].isBox()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.BOX), i, j);
                }
                else if(logicGrid.grid[i][j].isFlaggedBox()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.FLAGGED_BOX), i, j);
                }
                else if(logicGrid.grid[i][j].isWall()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.WALL), i, j);
                }
                else if(logicGrid.grid[i][j].isPlayer()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                    setAt(getCell(ImageType.PLAYER), i, j);
                }
                else if(logicGrid.grid[i][j].isFlag()){
                    setAt(getCell(ImageType.FLAG), i, j);
                }
                else if(logicGrid.grid[i][j].isEmpty()){
                    setAt(getCell(ImageType.EMPTY), i, j);
                }
            }
        }
    }
    public void translation(ImageView imageView, Direction direction, int length){
        setAt(getCell(ImageType.EMPTY), logicGrid.player[1] - direction.y, logicGrid.player[0] - direction.x);
        setAt(getCell(ImageType.EMPTY), logicGrid.player[1], logicGrid.player[0]);
        setAt(
                imageView,
                new int[] {logicGrid.player[0] *SIZE - length * direction.x, logicGrid.player[1] * SIZE - length * direction.y}
              );
    }

}
