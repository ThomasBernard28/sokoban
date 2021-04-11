package be.ac.umons.Sokoban.Entities;
/**
This class defines a tile in the game grid
It can handle the movement from a tile to an other if given a matrix of this instance
 */

public class Tile
{
    /**
     * @param immovableObject Default value is set to empty
     * @param movableObject Default value is set to empty
     * @param x x coordinates of the tile (equivalent to col and j)
     * @param y y coordinates of the tile (equivalent to row and y)
     */
    private immovableInterface immovableObject = new EmptyImmovable();
    private movableInterface movableObject = new EmptyMovable();

    private final int x;
    private final int y;

    /**
     * This constructor initiate a tile with a coordinate of (x,y)
     * @param x x coordinate
     * @param y y coordinate
     */
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * The tile is converted to a string of 1 char depending on its content
     * It follows the convention for the xsb file format
     * '#' for a wall
     * '.' for a flag
     * '@' for a player
     * '+' for a player above a flag
     * '$' for a box
     * '*' for a box over a flag
     * ' ' for an empty tile
     */
    @Override
    public String toString()
    {
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

    public boolean isWall()
    {
        return immovableObject.getNature() == TileType.WALL;
    }

    public boolean isFlag()
    {
        return immovableObject.getNature() == TileType.FLAG && movableObject.getNature() == TileType.EMPTY;
    }

    public boolean hasFlag(){
        return immovableObject.getNature() == TileType.FLAG;
    }

    public boolean isBox()
    {
        return movableObject.getNature() == TileType.BOX && immovableObject.getNature() != TileType.FLAG;
    }

    public boolean isFlaggedBox()
    {
        return movableObject.getNature() == TileType.BOX && immovableObject.getNature() == TileType.FLAG;
    }

    public boolean hasBox(){
        return movableObject.getNature() == TileType.BOX;
    }

    public boolean isPlayer()
    {
        return movableObject.getNature() == TileType.PLAYER && immovableObject.getNature() != TileType.FLAG;
    }

    public boolean isFlaggedPlayer()
    {
        return movableObject.getNature() == TileType.PLAYER && immovableObject.getNature() == TileType.FLAG;
    }

    public boolean isEmpty()
    {
        return movableObject.getNature() == TileType.EMPTY && immovableObject.getNature() == TileType.EMPTY;
    }

    /**
     * @return an enumerate depending of the content of the tile
     */
    public TileType getVisualType(){
        if(this.isPlayer()){
            return TileType.PLAYER;
        }
        else if(this.isBox()){
            return TileType.BOX;
        }
        else if(this.isFlaggedBox()){
            return TileType.FLAGGED_BOX;
        }
        else if(this.isWall()){
            return TileType.WALL;
        }
        else if(this.isFlag()){
            return TileType.FLAG;
        }
        else if(this.isEmpty()){
            return TileType.EMPTY;
        }
        else {
            throw new IllegalStateException("Unexpected value");
        }
    }

    /**
     * @return The movable content of the tile
     */
    public movableInterface getMovableObject() {
        return movableObject;
    }

    /**
     * @return The immovable content of the tile
     */
    public immovableInterface getImmovableObject() {
        return immovableObject;
    }

    // mutator methods

    /**
     * Change the movable content of this tile
     * @param movable New value of the tile the value must either be PLAYER, BOX or EMPTY
     */
    public void setMovableObject(TileType movable)
    {
        switch(movable)
        {
            case PLAYER:
                this.movableObject = new Player();
                break;
            case BOX:
                this.movableObject = new Box();
                break;
            case EMPTY:
                this.movableObject = new EmptyMovable();
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    /**
     * Change the immovable content of the tile
     * @param immovable New value of the tile the value must either be WALL, FLAG or EMPTY
     */
    public void setImmovableObject(TileType immovable)
    {
        switch (immovable)
        {
            case WALL:
                this.immovableObject = new Wall();
                break;
            case FLAG:
                this.immovableObject = new Flag();
                break;
            case EMPTY:
                this.immovableObject = new EmptyImmovable();
                break;
            default:
                throw new IllegalStateException("Unexpected value");
        }
    }

    /**
     * Uses the method of the same name of its movable content
     * @param grid Grid object containing the matrix of tile
     * @param direction tells which direction to check (UP, DOWN, LEFT or RIGHT)
     * @return boolean telling if the movement is valid
     */
    public boolean checkMove(Grid grid, Direction direction)
    {
        return this.movableObject.checkMove(grid, x, y, direction);
    }

    /**
     * Execute the movement method of its movable content and erase this tile movable content
     * @param grid Grid object containing the matrix of tile
     * @param direction tells which direction to go (UP, DOWN, LEFT or RIGHT)
     */
    public void Move(Grid grid, Direction direction)
    {
        this.movableObject.Move(grid, x, y, direction);
        grid.getGridAt(x, y).setMovableObject(TileType.EMPTY);
    }
}
