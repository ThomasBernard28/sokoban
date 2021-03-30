package be.ac.umons.Sokoban.Entities;


public interface movableInterface
{


    boolean checkMove(Grid grid, int x, int y, int directionX, int directionY);


    void Move(Grid grid, int x, int y, int directionX, int directionY);


    Load getNature();

}
