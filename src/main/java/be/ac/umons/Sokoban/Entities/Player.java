package be.ac.umons.Sokoban.Entities;

public class Player implements  movableInterface
{

    @Override
    public boolean checkMove(Grid grid, int x, int y, Direction direction)
    {
        if(grid.grid[direction.y + y][x + direction.x].isFlaggedBox() || grid.grid[direction.y + y][x + direction.x].isBox())
        {
            return grid.grid[direction.y + y][x + direction.x].checkMove(grid, direction);
        }
        return !grid.grid[direction.y + y][x + direction.x].isWall();
    }

    @Override
    public void Move(Grid grid, int x, int y, Direction direction)
    {
        if(grid.grid[direction.y + y][x + direction.x].isFlaggedBox() || grid.grid[direction.y + y][x + direction.x].isBox())
        {
            grid.grid[direction.y + y][x + direction.x].Move(grid, direction);
        }
        grid.grid[y + direction.y][x + direction.x].setMovableObject(Load.PLAYER);
        grid.player[0] += direction.x;
        grid.player[1] += direction.y;
    }

    @Override
    public Load getNature()
    {
        return Load.PLAYER;
    }

}
