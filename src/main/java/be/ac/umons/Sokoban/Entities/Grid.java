package be.ac.umons.Sokoban.Entities;

public class Grid {
    private char movableObject;
    private char immovableObject;
    private int x;
    private int y;

    public Grid(int x, int y, char content){
        if(content == 'w' || content == 'f'){
            this.immovableObject = content;
        }
        this.movableObject = content;
        this.x = x;
        this.y = y;
    }

    public Grid(int x, int y){
        this.x = x;
        this.y = y;
    }

    // accessor methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWall(){
        return immovableObject == 'w';
    }

    public boolean isFlag(){
        return immovableObject == 'f';
    }

    public boolean isBox(){
        return movableObject == 'b' & immovableObject != 'f';
    }

    public boolean isFlaggedBox(){
        return movableObject == 'b' & immovableObject == 'f';
    }

    public boolean isPlayer(){
        return movableObject == 'p' & immovableObject != 'f';
    }

    public boolean isFlaggedPlayer(){
        return movableObject == 'p' & immovableObject == 'f';
    }

    public boolean isEmpty(){
        return movableObject == '\u0000' & immovableObject == '\u0000';
    }

    // mutator methods
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setMovableObject(char movable){
        this.movableObject = movable;
    }

    public void setImmovableObject(char immovable){
        this.immovableObject = immovable;
    }

    public void clearMovable(){
        this.movableObject = '\u0000';
    }

    // movement methods
    public boolean checkMoveBox(Grid[][] grid, int directionX, int directionY){
        return !grid[directionY + y][x + directionX].isWall() &&
                !grid[directionY + y][x + directionX].isBox() &&
                !grid[directionY + y][x + directionX].isFlaggedBox();
    }

    public void MoveBox(Grid[][] grid, int directionX, int directionY){
        grid[y + directionY][x + directionX].setMovableObject('b');
        System.out.println("box is now at (" + (x + directionX) +"," +  (y + directionY) + ")");
        this.clearMovable();
    }

    public boolean checkMovePlayer(Grid[][] grid, int directionX, int directionY){
        if(grid[directionY + y][x + directionX].isFlaggedBox() || grid[directionY + y][x + directionX].isBox()){
            return grid[directionY + y][x + directionX].checkMoveBox(grid, directionX, directionY);
        }
        return !grid[directionY + y][x + directionX].isWall();
    }
    public void MovePlayer(Grid[][] grid, int directionX, int directionY){
        grid[y + directionY][x + directionX].setMovableObject('b');
        System.out.println("player is now at (" + (x + directionX) +"," +  (y + directionY) + ")");
        this.clearMovable();
    }
}
