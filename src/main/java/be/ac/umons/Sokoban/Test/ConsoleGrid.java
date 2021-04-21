package be.ac.umons.Sokoban.Test;
import java.util.*;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.Size;


public class ConsoleGrid
{

    public static void main(String[] args)
    {

        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream


        Grid grid = new Grid(Size.LARGE);
        grid.set_default_walls();
        grid.setPlayer(1,6);
        grid.setBox(grid.getSize().getCol()/2, grid.getSize().getRow()/2);
        grid.set_flag((grid.getSize().getCol()/2)+1, (grid.getSize().getRow()/2)+1);

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
                    grid = new Grid(Size.LARGE);
                    grid.set_default_walls();
                    grid.setPlayer(1,6);
                    grid.setBox(grid.getSize().getCol()/2, grid.getSize().getRow()/2);
                    grid.set_flag((grid.getSize().getCol()/2)+1, (grid.getSize().getRow()/2)+1);
                    break;
                case "e":
                    playing = false;
                    break;
            }
        }

    }

    public static void printConsole(Grid grid)
    {
        for(int i = 0; i < grid.getSize().getRow(); i++)
        {
            for (int j = 0; j < grid.getSize().getCol(); j++)
            {
                System.out.print(grid.getGridAt(j, i));
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
