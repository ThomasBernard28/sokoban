package be.ac.umons.Sokoban.Entities;


public interface movableInterface
{


    boolean checkMove(Grid grid, int x, int y, Direction direction);


    void Move(Grid grid, int x, int y, Direction direction);


    TileType getNature();

}
