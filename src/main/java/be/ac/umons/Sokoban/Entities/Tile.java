package be.ac.umons.Sokoban.Entities;
/*
This Class defines the grid and the different entities.
With this class we can control the movements of the different entities and check if the movement
we want to make is possible.
 */

enum Load {
    BOX,
    PLAYER,
    WALL,
    FLAG,
    EMPTY
}

public class Tile {
    private immovableInterface immovableObject = new EmptyImmovable();
    private movableInterface movableObject = new EmptyMovable();

    private int x;
    private int y;
    // Defines the mobility of the objects
    public Tile(int x, int y, Load content){
        switch (content){
            case WALL:
                this.immovableObject = new Wall();
                break;
            case FLAG:
                this.immovableObject = new Flag();
                break;
            case BOX:
                this.movableObject = new Box();
                break;
            case PLAYER:
                this.movableObject = new Player();
        }
        this.x = x;
        this.y = y;
    }

    public Tile(int x, int y){
        this.x = x;
        this.y = y;
    }
    /* This method is used in ConsoleGrid for the console interface
    The method gives each entity a letter to represent it
     */
    @Override
    public String toString(){
        if(this.isWall()){
            return "w";
        }
        if(this.isFlag()){
            return "f";
        }
        if(this.isPlayer() || this.isFlaggedPlayer()){
            return "p";
        }
        if(this.isBox()){
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
        return immovableObject.getNature() == Load.WALL;
    }

    public boolean isFlag(){
        return immovableObject.getNature() == Load.FLAG && movableObject.getNature() == Load.EMPTY;
    }

    public boolean isBox(){
        return movableObject.getNature() == Load.BOX && immovableObject.getNature() != Load.FLAG;
    }

    public boolean isFlaggedBox(){
        return movableObject.getNature() == Load.BOX && immovableObject.getNature() == Load.FLAG;
    }

    public boolean isPlayer(){
        return movableObject.getNature() == Load.PLAYER && immovableObject.getNature() != Load.FLAG;
    }

    public boolean isFlaggedPlayer(){
        return movableObject.getNature() == Load.PLAYER && immovableObject.getNature() == Load.FLAG;
    }

    public boolean isEmpty(){
        return movableObject.getNature() == Load.EMPTY && immovableObject.getNature() == Load.EMPTY;
    }

    // mutator methods
    public void setX(int x){
        this.x = x;
    }

    public void setY(int y){
        this.y = y;
    }

    public void setMovableObject(Load movable){
        switch(movable){
            case PLAYER:
                this.movableObject = new Player();

                break;
            case BOX:
                this.movableObject = new Box();
                break;
        }
    }

    public void setImmovableObject(Load immovable){
        switch (immovable){
            case WALL:
                this.immovableObject = new Wall();
                break;
            case FLAG:
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
        myTile.setMovableObject(Load.PLAYER);

        Tile[][] tableau = new Tile[2][2];
        tableau[0][0] = myTile;
        tableau[0][1] = tile;

        myTile.Move(tableau, 1, 0);
        System.out.println(tableau[0][0]);
        System.out.println(tableau[0][1]);

    }
}
