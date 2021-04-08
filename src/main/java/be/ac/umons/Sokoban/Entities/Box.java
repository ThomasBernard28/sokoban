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
        grid.getGridAt(x + direction.x, y + direction.y).setMovableObject(TileType.BOX);
    }

    @Override
    public TileType getNature()
    {
        return TileType.BOX;
    }
}
