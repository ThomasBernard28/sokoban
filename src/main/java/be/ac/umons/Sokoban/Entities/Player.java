package be.ac.umons.Sokoban.Entities;

public class Player implements  movableInterface
{

    @Override
    public boolean checkMove(Grid grid, int x, int y, int directionX, int directionY)
    {
        if(grid.grid[directionY + y][x + directionX].isFlaggedBox() || grid.grid[directionY + y][x + directionX].isBox())
        {
            return grid.grid[directionY + y][x + directionX].checkMove(grid, directionX, directionY);
        }
        return !grid.grid[directionY + y][x + directionX].isWall();
    }

    @Override
    public void Move(Grid grid, int x, int y, int directionX, int directionY)
    {
        if(grid.grid[directionY + y][x + directionX].isFlaggedBox() || grid.grid[directionY + y][x + directionX].isBox())
        {
            grid.grid[directionY + y][x + directionX].Move(grid, directionX, directionY);
        }
        grid.grid[y + directionY][x + directionX].setMovableObject(Load.PLAYER);
        grid.player[0] += directionX;
        grid.player[1] += directionY;
    }

    @Override
    public Load getNature()
    {
        return Load.PLAYER;
    }

}
