package be.ac.umons.Sokoban.Entities;

public interface movableInterface {


    boolean checkMove(Tile[][] grid, int x, int y, int directionX, int directionY);


    void Move(Tile[][] grid, int x, int y, int directionX, int directionY);


    char getNature();

}
