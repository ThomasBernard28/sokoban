package be.ac.umons.Sokoban.Entities;

public class Box  implements movableInterface{

    char nature;

    @Override
    public boolean checkMove(Grid grid, int x, int y, int directionX, int directionY)
    {
        return !grid.grid[directionY + y][x + directionX].isWall() &&
                !grid.grid[directionY + y][x + directionX].isBox() &&
                !grid.grid[directionY + y][x + directionX].isFlaggedBox();
    }

    @Override
    public void Move(Grid grid, int x, int y, int directionX, int directionY)
    {
        grid.grid[y + directionY][x + directionX].setMovableObject(Load.BOX);
    }

    @Override
    public Load getNature()
    {
        return Load.BOX;
    }
}
