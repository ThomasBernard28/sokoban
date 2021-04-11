package be.ac.umons.Sokoban.Entities;

import be.ac.umons.Sokoban.MapGeneration.PatternGenerator;

import java.util.Arrays;

/*
This class is made to initiate a grid, the class takes the different parameters of
a classical grid and translate them into a representable grid
 */
public class Grid {
    // A grid is a 2D Array of tiles
    private final Tile [][] grid;
    private final boolean[][] walkable;

    public final int col;
    public final int row;

    private final int [] player = new int[2];

    public Grid (int col, int row)
    {
        this.col = col;
        this.row = row;

        //Create an instance of Grid that is an Array with a "row" width and "col" length
        grid = new Tile[row][col];
        // For each row
        for (int i = 0; i < row; i++)
        {
         // For each column fill with a Tile
            for (int j =0; j < col; j++)
            {
                grid[i][j] = new Tile(j, i);

            }
        }

        walkable = new boolean[row][col];
    }

    public Tile[][] getGrid(){
        return grid;
    }

    public Tile getGridAt(int x, int y){
        return grid[y][x];
    }

    public Tile getGridFromPlayer(int dX, int dY){
        return grid[player[1] + dY][player[0] + dX];
    }

    public Tile getGridFromPlayer(){
        return grid[player[1]][player[0]];
    }

    public Tile getGridFromPlayer(Direction direction){
        return grid[player[1] + direction.y][player[0] + direction.x];
    }

    public int getPlayerX(){
        return player[0];
    }

    public int getPlayerY(){
        return player[1];
    }

    private void setPlayerLocation(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if(getGridAt(j, i).isPlayer()){
                    player[0] = j;
                    player[1] = i;
                }
            }
        }
    }
    public void setPlayerLocation(int x, int y){
        player[0] = x;
        player[1] = y;
    }
    public void movePlayer(Direction direction){
        player[0] += direction.x;
        player[1] += direction.y;
    }

    // Set the border walls with loops and calling methods from Tile class to set mobility
    public void set_default_walls()
    {
        for (int i = 0; i < col; i++)
        {
            grid[0][i].setImmovableContent(ImmovableContent.WALL);
            grid[row -1][i].setImmovableContent(ImmovableContent.WALL);
        }
        for (int j = 0; j< row; j++)
        {
            grid[j][0].setImmovableContent(ImmovableContent.WALL);
            grid[j][col -1].setImmovableContent(ImmovableContent.WALL);
        }
    }
    //TODO upgrade reset function
    public void resetGrid(){
        for (Tile[] line : grid){
            for (Tile tile : line){
                tile.setImmovableContent(ImmovableContent.EMPTY);
                tile.setMovableContent(MovableContent.EMPTY);
            }
        }
        player[0] = 0;
        player[1] = 0;
    }
    // This method set the player at position (x,y)
    public void set_player(int x, int y)
    {
        grid[y][x].setMovableContent(MovableContent.PLAYER);
        player[0] = x;
        player[1] = y;
    }
    public void set_boxes(int x, int y)
    {
        grid[y][x].setMovableContent(MovableContent.BOX);
    }
    public void set_flag(int x, int y)
    {
        grid[y][x].setImmovableContent(ImmovableContent.FLAG);
    }

    /*
     *
     * Generation Part
     *
     */

    private Direction[] unexploredNeighbour(int startX, int startY){
        Direction[] res = new Direction[4];
        int i = 0;
        for (Direction dir : Direction.values()){
            if (inIndexRange(startX, startY, dir) && walkable[startY + dir.y][startX + dir.x]) {
                res[i] = dir;
            }
        }
        return res;
    }

    private boolean inIndexRange(int startX, int startY, Direction dir){
        return (0 <= startX + dir.x && startX + dir.x < col) && (0 <= startY + dir.y && startY + dir.y <= row);
    }

    private void resetWalkable(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkable[i][j] = !grid[i][j].isWall();
            }
        }
    }

    private void pathFinder(int startX, int startY){
        // https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
        walkable[startY][startX] = false;
        for (Direction dir : unexploredNeighbour(startX, startY)){
            if (dir != null && walkable[startY + dir.y][startX + dir.x]){
                pathFinder(startX + dir.x, startY + dir.y);
            }
        }
    }


    private boolean solvable(int playerX, int playerY){
        resetWalkable();
        pathFinder(playerX, playerY);
        for (boolean[] line : walkable){
            for (boolean square : line){
                if (square){
                    return false;
                }
            }
        }
        return true;
    }

    private void patternIntegration(int setX, int setY, char[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'e') {
                    grid[setY + i][setX + j].setImmovableContent(ImmovableContent.EMPTY);
                }else{
                    grid[setY + i][setX + j].setImmovableContent(ImmovableContent.WALL);
                }
            }
        }
    }

    public void generateRandomWalls() {
        PatternGenerator patternGiver = new PatternGenerator();
        for (int i = 0; i < row / 3; i++) {
            for (int j = 0; j < col / 3; j++) {
                if(i == 0 && j == 0){
                    patternIntegration(1, 1, PatternGenerator.getEmpty());
                    continue;
                }
                patternIntegration(j * 3 + 1, i * 3 + 1, patternGiver.getRandomPattern());
                if ( ! solvable(1, 1)){
                    patternIntegration(j * 3 + 1, i * 3 + 1, PatternGenerator.getEmpty());
                }
            }
        }
    }
    public char[][] toCharArray(){
        char [][] charGrid = new char[row][col+1];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (j == col - 1) {
                    charGrid[i][j+1] = '\n';
                }
                charGrid[i][j] = grid[i][j].toString().charAt(0);
            }
        }
        return charGrid;
    }

    public static void main(String[] args)
    {
        Grid myGrid = new Grid(5, 5);
        myGrid.set_default_walls();
        myGrid.set_boxes(1,1);
        for (Tile[] line :
                myGrid.grid) {
            System.out.println(Arrays.toString(line));
        }


    }

}
