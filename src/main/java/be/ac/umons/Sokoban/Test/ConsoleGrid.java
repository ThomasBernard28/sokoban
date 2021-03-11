package be.ac.umons.Sokoban.Test;
import java.util.*;

import be.ac.umons.Sokoban.Entities.Grid;

/*
This Class create an interface in the console. We use it to test the code before the implementation
of the Java FX. We will re-use some of the methods that are defined here later in the implementation of
Java FX
 */
public class ConsoleGrid{

    public static void main(String[] args){
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream

        // Create a new grid using some methods in Grid Class.
        Grid grid = new Grid(7,8);
        grid.set_default_walls();
        grid.set_player(1,6);
        grid.set_boxes(grid.col/2, grid.row/2);
        grid.set_flag((grid.col/2)+1, (grid.row/2)+1);

        // Create a loop that takes inputs from the user and execute them
        boolean playing = true;
        int[] pos = {1, 6};
        String move;
        while(playing){
            //Print the actual grid
            printConsole(grid);
            //The next move is the new input
            move = sc.nextLine();
            System.out.println("ok");

            //Check if the input is one of the agreed keys and then move the entities
            switch (move){
                case "z":
                    if(grid.grid[pos[1]][pos[0]].checkMovePlayer(grid.grid, 0, -1)){
                        grid.grid[pos[1]][pos[0]].MovePlayer(grid.grid, 0, -1);
                        pos[1] -= 1;
                    }
                    break;
                case "q":
                    if(grid.grid[pos[1]][pos[0]].checkMovePlayer(grid.grid, -1, 0)){
                        grid.grid[pos[1]][pos[0]].MovePlayer(grid.grid, -1, 0);
                        pos[0] -= 1;
                    }
                    break;
                case "s":
                    if(grid.grid[pos[1]][pos[0]].checkMovePlayer(grid.grid, 0, 1)){
                        grid.grid[pos[1]][pos[0]].MovePlayer(grid.grid, 0, 1);
                        pos[1] += 1;
                    }
                    break;
                case "d":
                    if(grid.grid[pos[1]][pos[0]].checkMovePlayer(grid.grid, 1, 0)){
                        grid.grid[pos[1]][pos[0]].MovePlayer(grid.grid, 1, 0);
                        pos[0] += 1;
                    }
                    break;
                case " ":
                    grid = new Grid(7,8);
                    grid.set_default_walls();
                    grid.set_player(1,6);
                    grid.set_boxes(grid.col/2, grid.row/2);
                    grid.set_flag((grid.col/2)+1, (grid.row/2)+1);
                    pos = new int []{1, 6};
                    break;
                // Stop playing
                case "p":
                    playing = false;
                    break;
            }
        }

    }
    // This method print the console interface
    public static void printConsole(Grid grid){
        for(int i = 0; i < grid.row; i++){
            for (int j = 0; j < grid.col; j++){
                System.out.print(grid.grid[i][j]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
