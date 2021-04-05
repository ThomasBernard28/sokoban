package be.ac.umons.Sokoban.Entities;

public class Player implements  movableInterface
{

    @Override
    public boolean checkMove(Grid grid, int x, int y, Direction direction)
    {
        if(grid.getGridAt(x + direction.x, y + direction.y).isFlaggedBox() || grid.getGridAt(x + direction.x, y + direction.y).isBox())
        {
            return grid.getGridAt(x + direction.x, y + direction.y).checkMove(grid, direction);
        }
        return !grid.getGridAt(x + direction.x, y + direction.y).isWall();
    }

    @Override
    public void Move(Grid grid, int x, int y, Direction direction)
    {
        if(grid.getGridAt(x + direction.x, y + direction.y).isFlaggedBox() || grid.getGridAt(x + direction.x, y + direction.y).isBox())
        {
            grid.getGridAt(x + direction.x, y + direction.y).Move(grid, direction);
        }
        grid.getGridAt(x + direction.x, y + direction.y).setMovableObject(Load.PLAYER);
        grid.player[0] += direction.x;
        grid.player[1] += direction.y;
    }

    @Override
    public Load getNature()
    {
        return Load.PLAYER;
    }

}
