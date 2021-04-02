package be.ac.umons.Sokoban.Entities;

public class Box  implements movableInterface{

    char nature;

    @Override
    public boolean checkMove(Grid grid, int x, int y, Direction direction)
    {
        return !grid.grid[direction.y + y][x + direction.x].isWall() &&
                !grid.grid[direction.y + y][x + direction.x].isBox() &&
                !grid.grid[direction.y + y][x + direction.x].isFlaggedBox();
    }

    @Override
    public void Move(Grid grid, int x, int y, Direction direction)
    {
        grid.grid[y + direction.y][x + direction.x].setMovableObject(Load.BOX);
    }

    @Override
    public Load getNature()
    {
        return Load.BOX;
    }
}
