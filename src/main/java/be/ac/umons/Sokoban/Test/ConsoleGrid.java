package be.ac.umons.Sokoban.Test;
import java.util.*;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.Wall;


public class ConsoleGrid
{

    public static void main(String[] args)
    {

        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream


        Grid grid = new Grid(7,8);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);

        System.out.println("To exit press 'e'");


        boolean playing = true;
        String move;
        while(playing){
            printConsole(grid);
            move = sc.nextLine();
            switch (move)
            {
                case "z":
                    if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, -1))
                    {
                        grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, -1);
                    }
                    break;
                case "q":
                    if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, -1, 0))
                    {
                        grid.grid[grid.player[1]][grid.player[0]].Move(grid, -1, 0);
                    }
                    break;
                case "s":
                    if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 0, 1))
                    {
                        grid.grid[grid.player[1]][grid.player[0]].Move(grid, 0, 1);
                    }
                    break;
                case "d":
                    if(grid.grid[grid.player[1]][grid.player[0]].checkMove(grid, 1, 0))
                    {
                        grid.grid[grid.player[1]][grid.player[0]].Move(grid, 1, 0);
                    }
                    break;
                case " ":
                    grid = new Grid(7,8);
                    grid.set_default_walls();
                    grid.set_player(1,6);
                    grid.set_boxes(grid.col/2, grid.row/2);
                    grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
                    break;
                case "e":
                    playing = false;
                    break;
            }
        }

    }
    public static void printConsole(Grid grid)
    {
        for(int i = 0; i < grid.row; i++)
        {
            for (int j = 0; j < grid.col; j++)
            {
                System.out.print(grid.grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
