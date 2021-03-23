package be.ac.umons.Sokoban.Entities;

public class Player implements  movableInterface
{

    @Override
    public boolean checkMove(Tile[][] grid, int x, int y, int directionX, int directionY)
    {
        if(grid[directionY + y][x + directionX].isFlaggedBox() || grid[directionY + y][x + directionX].isBox())
        {
            return grid[directionY + y][x + directionX].checkMove(grid, directionX, directionY);
        }
        return !grid[directionY + y][x + directionX].isWall();
    }

    @Override
    public void Move(Tile[][] grid, int x, int y, int directionX, int directionY)
    {
        if(grid[directionY + y][x + directionX].isFlaggedBox() || grid[directionY + y][x + directionX].isBox())
        {
            grid[directionY + y][x + directionX].Move(grid, directionX, directionY);
        }
        grid[y + directionY][x + directionX].setMovableObject(Load.PLAYER);
    }

    @Override
    public Load getNature()
    {
        return Load.PLAYER;
    }

}
