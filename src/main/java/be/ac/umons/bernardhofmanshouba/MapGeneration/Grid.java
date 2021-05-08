package be.ac.umons.bernardhofmanshouba.MapGeneration;

import be.ac.umons.bernardhofmanshouba.Entities.Direction;
import be.ac.umons.bernardhofmanshouba.Entities.ImmovableContent;
import be.ac.umons.bernardhofmanshouba.Entities.MovableContent;
import be.ac.umons.bernardhofmanshouba.Entities.Tile;
import be.ac.umons.bernardhofmanshouba.JavaFX.Size;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

/**
 * This class wraps a 2D array of Tile and allow access to it. It also handles the generation
 * of the map.
 */
public class Grid {

    /*
     * @param grid the 2D array of tiles that represent the state of the game
     * @param walkable a 2D array of boolean used by the generation methods
     * @param size enum value that contains the number of rows and columns of the grid
     * @param player 1D array that contains the coordinates of the player (x, y)
     */

    private final Tile[][] grid;
    private final boolean[][] walkable;

    protected final Size size;

    private final int [] player = new int[2];

    /**
     * Initiate a grid with rows and columns corresponding to the size entered
     * @param size enum value that will represent the size of the grid
     */
    public Grid (Size size) {
        this.size = size;

        //Create an instance of Grid that is an Array with a "row" width and "col" length
        grid = new Tile[size.getRow()][size.getCol()];
        // For each row
        for (int i = 0; i < size.getRow(); i++)
        {
         // For each column fill with a Tile
            for (int j =0; j < size.getCol(); j++)
            {
                grid[i][j] = new Tile(j, i);

            }
        }

        walkable = new boolean[size.getRow()][size.getCol()];
    }

    /**
     * Initiate a grid with as much row and column of the smallest Size enum value that can contain
     * the row and column entered. If the number of rows or columns is greater than the largest Size enum
     * then an error will be launched
     * @param row number of rows in the level
     * @param col number of columns int the level
     */
    public Grid (int row, int col){
        size = Size.determineSize(row, col);

        assert size != null;
        grid = new Tile[size.getRow()][size.getCol()];

        for (int i = 0; i < size.getRow(); i++)
        {
            // For each column fill with a Tile
            for (int j =0; j < size.getCol(); j++)
            {
                grid[i][j] = new Tile(j, i);

            }
        }

        walkable = new boolean[size.getRow()][size.getCol()];

    }

    public Tile[][] getGrid(){
        return grid;
    }

    /**
     * Return the tile at the position (x, y) in the grid
     * @param x x coordinate of the tile
     * @param y y coordinate of the tile
     * @return Tile object
     */
    public Tile getGridAt(int x, int y){
        return grid[y][x];
    }

    /**
     * Return the tile at (dX, dY) coordinates relative to the tile containing the player
     * @param dX x distance from the player
     * @param dY y distance from the player
     * @return Tile object
     */
    public Tile getGridFromPlayer(int dX, int dY){
        return grid[player[1] + dY][player[0] + dX];
    }

    /**
     * Return the tile that contains the player
     * @return Tile object
     */
    public Tile getGridFromPlayer(){
        return grid[player[1]][player[0]];
    }

    /**
     * Return the tile next to the player in the given direction
     * @param direction direction the desired tile is
     * @return Tile object
     */
    public Tile getGridFromPlayer(Direction direction){
        return grid[player[1] + direction.y][player[0] + direction.x];
    }

    public int getPlayerX(){
        return player[0];
    }

    public int getPlayerY(){
        return player[1];
    }

    public Size getSize() {
        return size;
    }

    /**
     * Set the coordinates of the player
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public void setPlayerLocation(int x, int y){
        player[0] = x;
        player[1] = y;
    }

    /**
     * Move the player in a given direction (only for the coordinates not the actual player)
     * @param direction direction of the move
     */
    public void movePlayer(Direction direction){
        player[0] += direction.x;
        player[1] += direction.y;
    }

    /**
     * Set the border of the map to wall
     */
    public void setDefaultWalls() {
        for (int i = 0; i < size.getCol(); i++)
        {
            grid[0][i].setImmovableContent(ImmovableContent.WALL);
            grid[size.getRow() -1][i].setImmovableContent(ImmovableContent.WALL);
        }
        for (int j = 0; j< size.getRow(); j++)
        {
            grid[j][0].setImmovableContent(ImmovableContent.WALL);
            grid[j][size.getCol() -1].setImmovableContent(ImmovableContent.WALL);
        }
    }

    /**
     * Set every movable and immovable content of the grid to empty
     */
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

    /**
     * Set the player at position (x,y)
     * @param x x coordinate of the player
     * @param y y coordinate of the player
     */
    public void setPlayer(int x, int y) {
        grid[y][x].setMovableContent(MovableContent.PLAYER);
        player[0] = x;
        player[1] = y;
    }

    /**
     * Set a box at position (x,y)
     * @param x x coordinate of the box
     * @param y y coordinate of the box
     */
    public void setBox(int x, int y)
    {
        grid[y][x].setMovableContent(MovableContent.BOX);
    }

    /**
     * Set a flag at position (x,y)
     * @param x x coordinate of the flag
     * @param y y coordinate of the flag
     */
    public void setFlag(int x, int y)
    {
        grid[y][x].setImmovableContent(ImmovableContent.FLAG);
    }

    /**
     * Check if a map is completed ie every box is on a flag
     * @return boolean indicating if the game is won
     */
    public boolean checkWin(){
        for (int i = 0; i < size.getRow() ; i++) {
            for (int j = 0; j < size.getCol(); j++) {
                if(getGridAt(j, i).isBox()){
                    return false;
                }
            }
        }
        return true;
    }

    /*
     *
     * Generation Part
     *
     */
    //TODO make sure that the map generated has no flagged box

    protected final Random rnd = new Random();

    /**
     * Tells whether the direction is in range of the array
     * @param startX x coordinate of the tile
     * @param startY y coordinate of the tile
     * @param dir direction to check
     * @return boolean indicating if it's within range
     */
    protected boolean inIndexRange(int startX, int startY, Direction dir){
        return (0 <= startX + dir.x && startX + dir.x < size.getCol()) &&
                (0 <= startY + dir.y && startY + dir.y < size.getRow());
    }

    /**
     * Set the walkable grid according to the tile grid
     * Every wall will correspond to a false and empty will be true
     */
    protected void resetWalkable(){
        for (int i = 0; i < size.getRow(); i++) {
            for (int j = 0; j < size.getCol(); j++) {
                walkable[i][j] = !grid[i][j].isWall() && !grid[i][j].hasBox();
            }
        }
    }

    /**
     * Find which of the four neighbour aren't yet explored by the pathfinding process
     * by looking at the walkable array
     * @param startX x coordinate of the tile
     * @param startY y coordinate of the tile
     * @return ArrayList of Direction that aren't yet explored
     */
    protected ArrayList<Direction> unexploredNeighbour(int startX, int startY){
        ArrayList<Direction> res = new ArrayList<>();
        int i = 0;
        for (Direction dir : Direction.values()){
            if (inIndexRange(startX, startY, dir)){
                if(walkable[startY + dir.y][startX + dir.x]){
                    res.add(dir);
                }
            }
            i++;
        }
        return res;
    }



    /**
     * Takes a given pattern of char (value must be 'w' or 'e') an copy it into the tile grid
     * with a wall for a 'w' and and empty for 'e'
     * @param setX x coordinate of the top left corner of where the pattern will be implemented
     * @param setY y coordinate of the top left corner of where the pattern will be implemented
     * @param matrix pattern which is a matrix of char either 'w' or 'e'
     */
    protected void patternIntegration(int setX, int setY, char[][] matrix){
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
     * https://fr.wikipedia.org/wiki/Algorithme_de_parcours_en_profondeur
     * (Source wikipedia)
     * Find every explorable tile in the grid, if a tile is not explorable its value will stay true in the
     * walkable grid
     * @param startX x coordinate of the start of the pathfinding
     * @param startY y coordinate of the start of the pathfinding
     */
    protected void explorer(int startX, int startY){
        //
        walkable[startY][startX] = false;
        for (Direction dir : unexploredNeighbour(startX, startY)){
            if (walkable[startY + dir.y][startX + dir.x]){
                explorer(startX + dir.x, startY + dir.y);
            }
        }
    }

    /**
     * Check if a grid is walkable by using the explorer method
     * @param playerX x coordinate of the start of the pathfinding
     * @param playerY y coordinate of the start of the pathfinding
     * @return boolean telling whether a grid is solvable
     */
    protected boolean solvable(int playerX, int playerY){
        resetWalkable();
        explorer(playerX, playerY);
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
     * Repetitively integrate randomly generated pattern into the grid and check at each iteration
     * if there's a contradiction(when the map is not solvable)
     */
    public void generateRandomWalls() {
        PatternGenerator patternGiver = new PatternGenerator();
        for (int i = 0; i < size.getRow() / 3; i++) {
            for (int j = 0; j < size.getCol() / 3; j++) {
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

    /*
     * Boxes, player and flags placement
     */

    /**
     * Find which of the four neighbour are explorable
     * by looking at the walkable array
     * @param startX x coordinate of the tile
     * @param startY y coordinate of the tile
     * @return ArrayList of Direction that aren't yet explored
     */
    protected ArrayList<Direction> explorableNeighbours(int startX, int startY){
        ArrayList<Direction> res = new ArrayList<>();
        for (Direction dir : Direction.values()){
            if (inIndexRange(startX, startY, dir)){
                if(!walkable[startY + dir.y][startX + dir.x] && !grid[startY + dir.y][startX + dir.x].hasBox() &&
                        !grid[startY + dir.y][startX + dir.x].isWall()){
                    res.add(dir);
                }
            }
        }
        return res;
    }

    protected ShuffledBox[] shuffledBoxes;

    /**
     * This function randomly place a player and n boxes in the grid
     * @param n number of boxes that will be placed
     */
    protected void placeRandomBox(int n){
        int[][] currBoxes = new int[n][2];

        resetWalkable();

        int i = 0;
        while(i < n){
            int rndRow = rnd.nextInt(size.getRow() - 1);
            int rndCol = rnd.nextInt(size.getCol() - 1);

            if(walkable[rndRow][rndCol]){
                walkable[rndRow][rndCol] = false;
                currBoxes[i][0] = rndCol;
                currBoxes[i][1] = rndRow;
                setBox(rndCol, rndRow);
                i++;
            }

        }

        while (i == n) {
            int rndRow = rnd.nextInt(size.getRow() - 1);
            int rndCol = rnd.nextInt(size.getCol() - 1);

            if(walkable[rndRow][rndCol]){
                walkable[rndRow][rndCol] = false;
                setPlayer(rndCol, rndRow);
                i++;
            }
        }
        // new version
        shuffledBoxes = new ShuffledBox[n];
        for (int j = 0; j < n; j++) {
            shuffledBoxes[j] = new ShuffledBox(currBoxes[j]);
        }
    }

    /**
     * This function explores the grid and find tiles adjacent of boxes that are explorable
     * and that doesn't make the following patterns [adjacent box - concerned tile - wall]
     * or [adjacent box - concerned tile - other box]
     */
    public void _suitableNextBox(){
        resetWalkable();
        explorer(getPlayerX(), getPlayerY());

        for (ShuffledBox box : shuffledBoxes) {
            // this contains the directions in which there's an empty and explorable tile next to that box
            ArrayList<Direction> directions = explorableNeighbours(box.getX(), box.getY());
            for (Direction dir : directions) {
                // here we watch out for the pattern [adjacent box -> concerned tile -> wall]
                if (!getGridAt(box.getX() + dir.x * 2, box.getY() + dir.y * 2).isWall() &&
                        !getGridAt(box.getX() + dir.x * 2, box.getY() + dir.y * 2).hasBox()) {
                    box.addSuitor(dir);
                }
            }
        }
    }

    /**
     * Recursive function that'll repeat itself s times and will move a box by
     * 1 tile at each iteration
     * @param s nb of iterations for this recursive
     */

    public boolean _shuffleBoxes(int s) {
        while (s > 0){

            _suitableNextBox();
            ShuffledBox chosenOne;
            int iteration = 0;
            boolean correctState;
            do {
                int iterationBis = 0;
                do {
                    // we select a random box that will be move
                    chosenOne = shuffledBoxes[(new Random()).nextInt(shuffledBoxes.length)];
                    // the loop makes sure that the box can be moved
                    iterationBis++;
                    if (iterationBis > 30) {
                        return false;
                    }
                } while (chosenOne.nbOfSuitor() < 1);
                // we move the box to one of its possible state
                correctState = chosenOne.moveToRandom(this);
                iteration++;
                if (iteration > 30) {
                    return false;
                }
            }while (!correctState);

            for (ShuffledBox box : shuffledBoxes){
                box.clearSuitor();
            }
            s--;
        }
        return true;
    }

    /**
     * This method generate the whole map
     * @param n number of boxes to be placed
     * @param s number of shuffle that will be effectuated on the boxes
     */
    public void constructMovables(int n, int s){
        boolean done;
        do {
            resetGrid();
            setDefaultWalls();
            generateRandomWalls();
            placeRandomBox(n);
            done = _shuffleBoxes(s);
        }while (!done);


        // placing the flags
        for (ShuffledBox box : shuffledBoxes) {
            getGridAt(box.getInitialX(), box.getInitialY()).setImmovableContent(ImmovableContent.FLAG);
        }
        // if the player sits on a flag at the end it'll raise an error
        if(getGridFromPlayer().isFlaggedPlayer()){
            for(Direction dir : Direction.values()) {
                if(getGridFromPlayer().checkMove(this, dir)){
                    getGridFromPlayer().move(this, dir);
                    break;
                }
            }
        }
    }

    protected static class ShuffledBox{
        private final static Random rnd = new Random();

        private final int[] initialPos = new int[2];

        private final int[] box;
        private final ArrayList<Direction> suitors = new ArrayList<>();

        // to compute distance to flag
        private int distanceMax = 0;
        // to not go in a circle
        private final HashMap<Direction, Double> probability = new HashMap<>();

        /**
         * Creates a box that can be moved with different method to shuffle it around the map
         * @param box array with x and y coordinates of the box
         */
        private ShuffledBox(int[] box){
            this.box = box;
            this.initialPos[0] = box[0];
            this.initialPos[1] = box[1];
            for (Direction dir : Direction.values()) {
                probability.put(dir, 1.0);
            }
        }

        private int getX(){
            return box[0];
        }

        private int getY(){
            return box[1];
        }

        private int getInitialX() {
            return initialPos[0];
        }

        private int getInitialY() {
            return initialPos[1];
        }

        private void addSuitor(Direction suitor){
            suitors.add(suitor);
        }

        private void clearSuitor(){
            suitors.clear();
        }

        private int nbOfSuitor(){
            return suitors.size();
        }

        /**
         * Move a box to one of it's possible state
         * @param grid the grid where the box is located
         */
        private boolean moveToRandom(Grid grid){
            //Direction dir = pickRandomSuitor();
            //Direction dir = pickFarAwaySuitor();
            Direction dir = pickMostProbableSuitor();

            // we move the player to the correct position
            //(needs to be done beforehand so that the player doesn't get deleted)
            grid.getGridFromPlayer().setMovableContent(MovableContent.EMPTY);
            grid.setPlayer(getX() + dir.x * 2, getY() + dir.y * 2);
            // we delete this box from the grid
            grid.getGridAt(getX(), getY()).setMovableContent(MovableContent.EMPTY);
            // actual moving of the box
            box[0] += dir.x;
            box[1] += dir.y;
            grid.getGridAt(getX(), getY()).setMovableContent(MovableContent.BOX);
            return true;
        }

        /**
         * Picks a random state given it's probability
         * @return direction in which the box will move
         */
        private Direction pickMostProbableSuitor(){
            double probabilitySum = getProbabilitySum();
            probabilitySum *= rnd.nextDouble();
            for (Direction suitor : suitors) {
                probabilitySum -= probability.get(suitor);
                if( probabilitySum < 0) {
                    adjustProbability(suitor);
                    return suitor;
                }
            }
            throw new IllegalStateException("Not supposed to be here");
        }

        /**
         * Picks the farthest possible state from the initial position
         * @return direction in which the box will move
         */
        private Direction pickFarAwaySuitor(){
            Direction res = null;

            for (Direction suitor : suitors ) {
                int x = calculateDistance(suitor);
                if(x > distanceMax) {
                    distanceMax = x;
                    res = suitor;
                }
            }
            if(res == null){
                res = suitors.get((new Random()).nextInt(suitors.size()));
                distanceMax = calculateDistance(res);
            }
            return res;
        }

        /**
         * Picks a random state
         * @return direction in which the box will move
         */
        private Direction pickRandomSuitor(){
            return suitors.get(rnd.nextInt(suitors.size()));
        }

        /**
         * @param dir direction of the possible state
         * @return distance from the possible state to the initial position
         */
        private int calculateDistance(Direction dir){
            return Math.abs(initialPos[0] - (dir.x + box[0])) + Math.abs(initialPos[1] - (dir.y + box[1]));
        }

        /**
         * Establish the sum of probability for every direction in the box
         * @return double with the sum of the probability
         */
        private double getProbabilitySum(){
            double sum = 0;

            for(Direction suitor : suitors) {
                sum += probability.get(suitor);
            }
            return sum;
        }

        /**
         * Change the probability of the directions given the direction in which the box has moved
         * @param dir direction of the current movement
         */
        private void adjustProbability(Direction dir){
            double p = probability.get(Direction.getOpposedOf(dir));
            p *= 0.8;
            probability.put(Direction.getOpposedOf(dir), p);
            probability.put(dir, probability.get(dir) * 0.9);
        }
    }

    public static void main(String[] args) {
        Grid myGrid = new Grid(Size.SMALL);
        PatternGenerator patternGiver = new PatternGenerator();
        myGrid.resetWalkable();
        myGrid.explorer(6, 6);
    }

}
