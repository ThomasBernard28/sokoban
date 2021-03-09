package be.ac.umons.Sokoban.Test;

import be.ac.umons.Sokoban.Entities.Grid;

public class ConsoleGrid{

    public static void main(String[] args){
        Grid grid = new Grid(7,8);
        grid.set_default_walls();
        grid.set_player(1,7);
        grid.set_boxes(grid.length/2, grid.width/2);
        grid.set_flag((grid.length/2)+1, (grid.width/2)+1);

    }
}
