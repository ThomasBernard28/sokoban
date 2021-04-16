package be.ac.umons.Sokoban.Test;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;

import java.io.FileNotFoundException;

public class UnitTest {


    public static char[] loadMov(String fileName, Path path){
        char []listChar=new char[1];
        //encore à faire mais comme ça ça met pas d'erreurs dans le programme
        return listChar;
    }


    public static Grid applyMov(String testFile, String movFile) throws FileNotFoundException {
        /*
        Load the files as a Grid and a char[] and apply the movements
         */
        Grid testGrid= Load.loadFile(Path.UNIT_TEST_IN,testFile);
        char[] listMov=loadMov(movFile,Path.UNIT_TEST_IN);
        for (char ch: listMov){
            switch (ch){
                case('z'):
                    testGrid.getGridFromPlayer().move(testGrid, Direction.UP);
                    break;
                case('q'):
                    testGrid.getGridFromPlayer().move(testGrid, Direction.LEFT);
                    break;
                case('s'):
                    testGrid.getGridFromPlayer().move(testGrid, Direction.DOWN);
                    break;
                case('d'):
                    testGrid.getGridFromPlayer().move(testGrid, Direction.RIGHT);
                    break;
            }

        }
        return testGrid;
    }
}
