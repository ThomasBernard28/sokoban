package be.ac.umons.Sokoban.Test;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import be.ac.umons.Sokoban.Save.Save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class to perform some unitTest
 */

public class UnitTest {

    /**
     * Method to load a .mov file type wich contains the movements to apply on a game instance
     *
     * @param fileName file name of the .mov
     * @param path     the path where we can find it
     * @return return a char array wich contains the different movement in a "z,q,s,d" type
     */
    public static String loadMov(Path path, String fileName) {
        File file = new File(path.getPath() + fileName);
        String movString = "";
        try {
            Scanner scanner = new Scanner(file);
            movString += scanner.next();
            System.out.println(movString);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return movString;
    }

    /**
     * Method to apply the movements coming from .mov to a grid coming from .xsb file
     *
     * @param testIN file name of the .xsb (game instance)
     * @param movFile  file name of the .mov (movements to apply)
     * @param testFileOUT file name of the .xsb w/ .mov apllied
     * @return return a grid with the movements applied
     * @throws FileNotFoundException throw error if the file doesn't exist at the correct location
     */

    public static void applyMov(String testIN, String movFile, String testFileOUT) throws FileNotFoundException {
        /*
        Load the files as a Grid and a char[] and apply the movements
         */
        Grid testGrid = Load.loadFile(Path.UNIT_TEST_IN, testIN);
        String listMov = loadMov(Path.UNIT_TEST_IN, movFile);
        for (int i = 0; i < listMov.length(); i++) {
            if (Pattern.matches("[zqsd]", listMov.substring(i, i+1))){
                switch (listMov.substring(i,i+1)) {
                    case ("z"):
                        if (testGrid.getGridFromPlayer().checkMove(testGrid, Direction.UP)){
                            testGrid.getGridFromPlayer().move(testGrid, Direction.UP);
                        }
                        break;
                    case ("q"):
                        if (testGrid.getGridFromPlayer().checkMove(testGrid, Direction.LEFT)){
                            testGrid.getGridFromPlayer().move(testGrid, Direction.LEFT);
                        }
                        break;
                    case ("s"):
                        if (testGrid.getGridFromPlayer().checkMove(testGrid, Direction.DOWN)){
                            testGrid.getGridFromPlayer().move(testGrid, Direction.DOWN);
                        }
                        break;
                    case ("d"):
                        if (testGrid.getGridFromPlayer().checkMove(testGrid, Direction.RIGHT)){
                            testGrid.getGridFromPlayer().move(testGrid, Direction.RIGHT);
                        }

                        break;
                    default: throw new IllegalStateException("Not an agreed movement");
                }
            }
        }
        try{
            Save.saving(testGrid, Path.UNIT_TEST_OUT, testFileOUT);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}