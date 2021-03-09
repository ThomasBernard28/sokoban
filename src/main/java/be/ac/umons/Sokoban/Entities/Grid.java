package be.ac.umons.Sokoban.Entities;

public class Grid {
    public Tile [][] grid;
    public int col;
    public int row;

    public Grid (int col, int row){
        this.col = col;
        this.row = row;

        grid = new Tile[row][col];
        // For each row
        for (int i = 0; i < row; i++){
         // For each column fill with a Tile
            for (int j =0; j < col; j++){
                grid[i][j] = new Tile(j, i);

            }
        }
    }
    public void set_default_walls(){
        for (int i = 0; i < col; i++){
            grid[0][i].setImmovableObject('w');
            grid[row -1][i].setImmovableObject('w');
        }
        for (int j = 0; j< row; j++){
            grid[j][0].setImmovableObject('w');
            grid[j][col -1].setImmovableObject('w');
        }
    }
    public void set_player(int x, int y){
        grid[y][x].setMovableObject('p');
        System.out.println(" player at " + x + " "+ y);
        System.out.println(grid[y][x]);
    }
    public void set_boxes(int x, int y){
        grid[y][x].setMovableObject('b');
    }
    public void set_flag(int x, int y){
        grid[y][x].setImmovableObject('f');
    }
}
