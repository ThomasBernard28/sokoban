package be.ac.umons.Sokoban.Entities;
/*
This Class defines the grid and the different entities.
With this class we can control the movements of the different entities and check if the movement
we want to make is possible.
 */

public class Tile
{
    private immovableInterface immovableObject = new EmptyImmovable();
    private movableInterface movableObject = new EmptyMovable();

    private final int x;
    private final int y;

    // Defines the mobility of the objects
    public Tile(int x, int y, Load content)
    {
        switch (content)
        {
            case WALL:
                this.immovableObject = new Wall();
                break;
            case FLAG:
                this.immovableObject = new Flag();
                break;
            case BOX:
                this.movableObject = new Box();
                break;
            case PLAYER:
                this.movableObject = new Player();
        }
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /* This method is used in ConsoleGrid for the console interface
    The method gives each entity a letter to represent it
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
        return immovableObject.getNature() == Load.WALL;
    }

    public boolean isFlag()
    {
        return immovableObject.getNature() == Load.FLAG && movableObject.getNature() == Load.EMPTY;
    }

    public boolean hasFlag(){
        return immovableObject.getNature() == Load.FLAG;
    }

    public boolean isBox()
    {
        return movableObject.getNature() == Load.BOX && immovableObject.getNature() != Load.FLAG;
    }

    public boolean isFlaggedBox()
    {
        return movableObject.getNature() == Load.BOX && immovableObject.getNature() == Load.FLAG;
    }

    public boolean hasBox(){
        return movableObject.getNature() == Load.BOX;
    }

    public boolean isPlayer()
    {
        return movableObject.getNature() == Load.PLAYER && immovableObject.getNature() != Load.FLAG;
    }

    public boolean isFlaggedPlayer()
    {
        return movableObject.getNature() == Load.PLAYER && immovableObject.getNature() == Load.FLAG;
    }

    public boolean isEmpty()
    {
        return movableObject.getNature() == Load.EMPTY && immovableObject.getNature() == Load.EMPTY;
    }

    // mutator methods

    public void setMovableObject(Load movable)
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
        }
    }

    public void setImmovableObject(Load immovable)
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
        }
    }

    public void clearMovable()
    {
        this.movableObject = new EmptyMovable();
    }

    // movement methods

    public boolean checkMove(Grid grid, Direction direction)
    {
        return this.movableObject.checkMove(grid, x, y, direction);
    }
    public void Move(Grid grid, Direction direction)
    {
        this.movableObject.Move(grid, x, y, direction);
        grid.getGridAt(x, y).clearMovable();
    }
}
