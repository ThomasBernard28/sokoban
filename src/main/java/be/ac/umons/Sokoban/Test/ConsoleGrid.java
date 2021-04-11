package be.ac.umons.Sokoban.Test;
import java.util.*;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;


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
        String fileName;
        while(playing){
            printConsole(grid);
            move = sc.nextLine();
            switch (move)
            {
                case "z":
                    if(grid.getGridFromPlayer().checkMove(grid, Direction.UP))
                    {
                        grid.getGridFromPlayer().move(grid, Direction.UP);
                    }
                    break;
                case "q":
                    if(grid.getGridFromPlayer().checkMove(grid, Direction.LEFT))
                    {
                        grid.getGridFromPlayer().move(grid, Direction.LEFT);
                    }
                    break;
                case "s":
                    if(grid.getGridFromPlayer().checkMove(grid, Direction.DOWN))
                    {
                        grid.getGridFromPlayer().move(grid, Direction.DOWN);
                    }
                    break;
                case "d":
                    if(grid.getGridFromPlayer().checkMove(grid, Direction.RIGHT))
                    {
                        grid.getGridFromPlayer().move(grid, Direction.RIGHT);
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
                System.out.print(grid.getGridAt(j, i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
