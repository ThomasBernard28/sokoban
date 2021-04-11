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

    /**
     * Find which of the four neighbour aren't yet explored by the pathfinding process
     * @param startX x coordinate of the tile
     * @param startY y coordinate of the tile
     * @return Array of Direction that aren't yet explored. The array is of length 4 and may contain null values
     */
    private Direction[] unexploredNeighbour(int startX, int startY){
        Direction[] res = new Direction[4];
        int i = 0;
        for (Direction dir : Direction.values()){
            if (inIndexRange(startX, startY, dir)){
                if(walkable[startY + dir.y][startX + dir.x]){
                    res[i] = dir;
                }
            }
            i++;
        }
        return res;
    }

    /**
     * Tells whether the direction is in range of the array
     * @param startX x coordinate of the tile
     * @param startY y coordinate of the tile
     * @param dir direction to check
     * @return boolean indicating if it's within range
     */
    private boolean inIndexRange(int startX, int startY, Direction dir){
        return (0 <= startX + dir.x && startX + dir.x < col) && (0 <= startY + dir.y && startY + dir.y < row);
    }

    /**
     * Set the walkable grid according to the tile grid
     * Every wall will correspond to a false and empty will be true
     */
    private void resetWalkable(){
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                walkable[i][j] = !grid[i][j].isWall();
            }
        }
    }

    /**
     * https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
     * (Source wikipedia)
     * Find every explorable tile in the grid, if a tile is not explorable its value will stay true in the
     * walkable grid
     * @param startX x coordinate of the start of the pathfinding
     * @param startY y coordinate of the start of the pathfinding
     */
    private void pathFinder(int startX, int startY){
        //
        walkable[startY][startX] = false;
        for (Direction dir : unexploredNeighbour(startX, startY)){
            if (dir != null && walkable[startY + dir.y][startX + dir.x]){
                pathFinder(startX + dir.x, startY + dir.y);
            }
        }
    }

    /**
     * Check if a grid is walkable by using the pathfinding method
     * @param playerX x coordinate of the start of the pathfinding
     * @param playerY y coordinate of the start of the pathfinding
     * @return boolean telling whether a grid is solvable
     */
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

    /**
     * Takes a given pattern of char (value must be 'w' or 'e') an copy it into the tile grid
     * with a wall for a 'w' and and empty for 'e'
     * @param setX x coordinate of the top left corner of where the pattern will be implemented
     * @param setY y coordinate of the top left corner of where the pattern will be implemented
     * @param matrix pattern which is a matrix of char either 'w' or 'e'
     */
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

    /**
     * Repetitively integrate randomly generated pattern into the grid and check at each iteration
     * if there's a contradiction(when the map is not solvable)
     */
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

    /**
     * Convert the Tile grid into a char grid the char representing the tile is the same used in the toString method
     * of the class Tile
     * @return a char matrix representing the current tile grid
     */
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
        Grid myGrid = new Grid(8, 8);
        PatternGenerator patternGiver = new PatternGenerator();

        myGrid.patternIntegration(0, 0, PatternGenerator.Pattern.CROSS.getPattern());
        myGrid.resetWalkable();
        printWalkable(myGrid.walkable);
        myGrid.pathFinder(6, 6);
        printWalkable(myGrid.walkable);

    }

    public static void printWalkable(boolean[][] matrix){
        for (boolean[] line :
                matrix) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }

    public static void printGrid(Tile[][] matrix){
        for (Tile[] line:
             matrix) {
            System.out.println(Arrays.toString(line));
        }
    }

}
