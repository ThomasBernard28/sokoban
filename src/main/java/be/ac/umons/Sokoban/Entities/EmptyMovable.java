package be.ac.umons.Sokoban.Entities;

public class EmptyMovable implements movableInterface{
    @Override
    public boolean checkMove(Grid grid, int x, int y, int directionX, int directionY)
    {
        return false;
    }

    @Override
    public void Move(Grid grid, int x, int y, int directionX, int directionY)
    {

    }

    @Override
    public Load getNature()
    {
        return Load.EMPTY;
    }
}