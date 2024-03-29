package be.ac.umons.bernardhofmanshouba.Entities;
/*
This Class defines the grid and the different entities.
With this class we can control the movements of the different entities and check if the movement
we want to make is possible.
 */

import be.ac.umons.bernardhofmanshouba.JavaFX.Sprite.TileImg;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;

/**
 * Defines a square of the game grid
 */
public class Tile
{
    private ImmovableContent immovableContent = ImmovableContent.EMPTY;
    private MovableContent movableContent = MovableContent.EMPTY;

    private final int x;
    private final int y;

    /**
     * The tile is initiate empty
     * (x,y) indicates its position in the grid
     * @param x x coordinate in the grid
     * @param y y coordinate in the grid
     */
    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        if(this.isWall())
        {
            return "#";
        }
        if(this.isFlag())
        {
            return ".";
        }
        if(this.isPlayer())
        {
            return "@";
        }
        if (this.isFlaggedPlayer())
        {
            return "+";
        }
        if(this.isBox())
        {
            return "$";
        }
        if(this.isFlaggedBox())
        {
            return "*";
        }

        return " ";
    }

    // accessor methods

    public boolean isWall() {
        return immovableContent == ImmovableContent.WALL;
    }

    public boolean isFlag() {
        return immovableContent == ImmovableContent.FLAG && movableContent == MovableContent.EMPTY;
    }

    public boolean hasFlag(){
        return immovableContent == ImmovableContent.FLAG;
    }

    public boolean isBox() {
        return movableContent == MovableContent.BOX && immovableContent == ImmovableContent.EMPTY;
    }

    public boolean isFlaggedBox() {
        return movableContent == MovableContent.BOX && immovableContent == ImmovableContent.FLAG;
    }

    public boolean hasBox(){
        return movableContent == MovableContent.BOX;
    }

    public boolean isPlayer() {
        return movableContent == MovableContent.PLAYER && immovableContent == ImmovableContent.EMPTY;
    }

    public boolean isFlaggedPlayer() {
        return movableContent == MovableContent.PLAYER && immovableContent == ImmovableContent.FLAG;
    }

    public boolean isEmpty() {
        return movableContent == MovableContent.EMPTY && immovableContent == ImmovableContent.EMPTY;
    }

    /**
     * Says which image needs to be placed to represent this tile
     * @return TileImg enum
     */
    public TileImg getVisualType(){
        if(this.isPlayer()){
            return TileImg.PLAYER;
        }
        else if(this.isBox()){
            return TileImg.BOX;
        }
        else if(this.isFlaggedBox()){
            return TileImg.FLAGGED_BOX;
        }
        else if(this.isWall()){
            return TileImg.WALL;
        }
        else if(this.isFlag()){
            return TileImg.FLAG;
        }
        else if(this.isEmpty()){
            return TileImg.EMPTY;
        }
        else {
            throw new IllegalStateException("Unexpected value" + this.getImmovableContent() + this.getMovableContent());
        }
    }

    public ImmovableContent getImmovableContent(){
        return immovableContent;
    }

    public MovableContent getMovableContent() {
        return movableContent;
    }

    // mutator methods

    public void setMovableContent(MovableContent content)
    {
        movableContent = content;
    }

    public void setImmovableContent(ImmovableContent content)
    {
        immovableContent = content;
    }

    // movement methods

    /**
     * Checks the validity of the target in the given direction
     * @param grid grid in which the tile is located
     * @param direction direction of the target
     * @return true if valid, false if not
     */
    public boolean checkMove(Grid grid, Direction direction) {
        return movableContent.checkMove(grid,direction);
    }

    /**
     * Move the content of this tile in the given direction
     * @param grid grid in which the tile is located
     * @param direction direction of the target
     */
    public void move(Grid grid, Direction direction) {
        movableContent.move(grid, direction);
        grid.getGridAt(x, y).setMovableContent(MovableContent.EMPTY);
    }
}
