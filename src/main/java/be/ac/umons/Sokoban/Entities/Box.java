package be.ac.umons.Sokoban.Entities;

public class Box  implements movableInterface{

    @Override
    public boolean checkMove(Grid grid, int x, int y, Direction direction)
    {
        return ! grid.getGridAt(x + direction.x, y + direction.y).isWall() &&
                !grid.getGridAt(x + direction.x, y + direction.y).hasBox();
    }

    @Override
    public void Move(Grid grid, int x, int y, Direction direction)
    {
        grid.getGridAt(x + direction.x, y + direction.y).setMovableObject(Load.BOX);
    }

    @Override
    public Load getNature()
    {
        return Load.BOX;
    }
}
