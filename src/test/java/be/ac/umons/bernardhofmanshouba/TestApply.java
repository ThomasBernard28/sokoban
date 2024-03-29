package be.ac.umons.bernardhofmanshouba;

import be.ac.umons.bernardhofmanshouba.Entities.Direction;
import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.Save.Load;
import be.ac.umons.bernardhofmanshouba.Save.Path;
import be.ac.umons.bernardhofmanshouba.Save.Save;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Class to perform some unitTest
 */

public class TestApply {

    /**
     * Method to load a .mov file type wich contains the movements to apply on a game instance
     *
     * @param fileName file name of the .mov
     * @param path     the path where we can find it
     * @return return a char array wich contains the different movement in a "z,q,s,d" type
     */
    public static String loadMov(Path path, String fileName) throws FileNotFoundException {
        File file = new File(path.getPath() + fileName);
        String movString = "";
        Scanner scanner = new Scanner(file);
        movString += scanner.next();

        return movString;
    }

    /**
     * Method to apply the movements coming from .mov to a grid coming from .xsb file
     *
     * @param testIN file name of the .xsb (game instance)
     * @param movFile  file name of the .mov (movements to apply)
     * @return return a grid with the movements applied
     * @throws FileNotFoundException throw error if the file doesn't exist at the correct location
     */

    public static Grid applyMov(String testIN, String movFile) throws FileNotFoundException {
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
        return testGrid;
    }

    public static void applyMov(Grid testGrid, String listMov) {
        /*
        Load the files as a Grid and a char[] and apply the movements
         */

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
    }
}
