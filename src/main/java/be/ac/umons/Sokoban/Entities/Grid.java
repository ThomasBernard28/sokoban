package be.ac.umons.Sokoban.Entities;

/*
This class is made to initiate a grid, the class takes the different parameters of
a classical grid and translate them into a representable grid
 */
public class Grid {
    // A grid is a 2D Array of tiles
    public Tile [][] grid;
    public int col;
    public int row;

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
    }
    // Set the border walls with loops and calling methods from Tile class to set mobility
    public void set_default_walls()
    {
        for (int i = 0; i < col; i++)
        {
            grid[0][i].setImmovableObject(Load.WALL);
            grid[row -1][i].setImmovableObject(Load.WALL);
        }
        for (int j = 0; j< row; j++)
        {
            grid[j][0].setImmovableObject(Load.WALL);
            grid[j][col -1].setImmovableObject(Load.WALL);
        }
    }
    // This method set the player at position (x,y)
    public void set_player(int x, int y)
    {
        grid[y][x].setMovableObject(Load.PLAYER);
    }
    public void set_boxes(int x, int y)
    {
        grid[y][x].setMovableObject(Load.BOX);
    }
    public void set_flag(int x, int y)
    {
        grid[y][x].setImmovableObject(Load.FLAG);
    }

    public static void main(String[] args)
    {
        Grid myGrid = new Grid(5, 5);
        myGrid.set_default_walls();
    }
}
