package be.ac.umons.Sokoban.Entities;

public class Grid {
    Tile [][] grid;
    public int length;
    public int width;

    public Grid (int length, int width){
        this.length = length;
        this.width = width;

        grid = new Tile[width][length];
        // For each row
        for (int i = 0; i < width; i++){
         // For each column fill with a Tile
            for (int j =0; j < length; j++){
                grid[i][j] = new Tile(i, j);

            }
        }
    }
    public void set_default_walls(){
        for (int i = 0; i < length; i++){
            grid[0][i].setImmovableObject('w');
            grid[length -1][i].setImmovableObject('w');
        }
        for (int j = 0; j< width; j++){
            grid[j][0].setImmovableObject('w');
            grid[j][width -1].setImmovableObject('w');
        }
    }
    public void set_player(int x, int y){
        grid[x][y].setMovableObject('p');
    }
    public void set_boxes(int x, int y){
        grid[x][y].setMovableObject('b');
    }
    public void set_flag(int x, int y){
        grid[x][y].setImmovableObject('f');
    }
}
