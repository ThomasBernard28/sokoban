package be.ac.umons.Sokoban.Entities;

public class Box  implements movableInterface{

    char nature;

    @Override
    public boolean checkMove(Tile[][] grid, int x, int y, int directionX, int directionY) {
        return !grid[directionY + y][x + directionX].isWall() &&
                !grid[directionY + y][x + directionX].isBox() &&
                !grid[directionY + y][x + directionX].isFlaggedBox();
    }

    @Override
    public void Move(Tile[][] grid, int x, int y, int directionX, int directionY) {
        grid[y + directionY][x + directionX].setMovableObject('b');
        System.out.println("box is now at (" + (x + directionX) +"," +  (y + directionY) + ")");
        grid[y + directionY][x + directionX].clearMovable();
    }

    @Override
    public char getNature() {
        return 'b';
    }
}
