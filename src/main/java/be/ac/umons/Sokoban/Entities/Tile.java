package be.ac.umons.Sokoban.Entities;
/*
This Class defines the grid and the different entities.
With this class we can control the movements of the different entities and check if the movement
we want to make is possible.
 */

public class Tile {
    private immovableInterface immovableObject = new EmptyImmovable();
    private movableInterface movableObject = new EmptyMovable();

    private int x;
    private int y;
    // Defines the mobility of the objects
    public Tile(int x, int y, char content){
        switch (content){
            case 'w':
                this.immovableObject = new Wall();
                break;
            case 'f':
                this.immovableObject = new Flag();
                break;
            case 'b':
                this.movableObject = new Box();
                break;
            case 'p':
                this.movableObject = new Player();
        }
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString(){
        if(immovableObject.getNature() == 'w'){
            return "w";
        }
        if(immovableObject.getNature() == 'f' && movableObject.getNature() == 'e'){
            return "f";
        }
        if(movableObject.getNature() == 'p'){
            return "p";
        }
        if(movableObject.getNature() == 'b'){
            return "b";
        }
        return ".";
    }


    // accessor methods
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isWall(){
        return immovableObject.getNature() == 'w';
    }

    public boolean isFlag(){
        return immovableObject.getNature() == 'f' && movableObject.getNature() == 'e';
    }

    public boolean isBox(){
        return movableObject.getNature() == 'b' && immovableObject.getNature() != 'f';
    }

    public boolean isFlaggedBox(){
        return movableObject.getNature() == 'b' && immovableObject.getNature() == 'f';
    }

    public boolean isPlayer(){
        return movableObject.getNature() == 'p' && immovableObject.getNature() != 'f';
    }

    public boolean isFlaggedPlayer(){
        return movableObject.getNature() == 'p' && immovableObject.getNature() == 'f';
    }

    public boolean isEmpty(){
        return movableObject.getNature() == 'e' && immovableObject.getNature() == 'e';
    }

    // mutator methods
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setMovableObject(char movable){
        switch(movable){
            case 'p':
                this.movableObject = new Player();

                break;
            case 'b':
                this.movableObject = new Box();
                break;
        }
    }

    public void setImmovableObject(char immovable){
        switch (immovable){
            case 'w':
                this.immovableObject = new Wall();
                break;
            case 'f':
                this.immovableObject = new Flag();
        }
    }

    public void clearMovable(){
        this.movableObject = new EmptyMovable();
    }

    // movement methods

    public boolean checkMove(Tile[][] grid, int directionX, int directionY){
        return this.movableObject.checkMove(grid, x, y, directionX, directionY);
    }
    public void Move(Tile[][] grid, int directionX, int directionY){
        this.movableObject.Move(grid, x, y, directionX, directionY);
        grid[y][x].clearMovable();
    }

    public static void main(String[] args) {
        Tile myTile = new Tile(0, 0);
        Tile tile = new Tile(1, 0);
        myTile.setMovableObject('p');

        Tile[][] tableau = new Tile[2][2];
        tableau[0][0] = myTile;
        tableau[0][1] = tile;

        myTile.Move(tableau, 1, 0);
        System.out.println(tableau[0][0]);
        System.out.println(tableau[0][1]);

    }
}
