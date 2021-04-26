package be.ac.umons.bernardhofmanshouba.Save;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;
import be.ac.umons.bernardhofmanshouba.Entities.ImmovableContent;
import be.ac.umons.bernardhofmanshouba.Entities.MovableContent;
import be.ac.umons.bernardhofmanshouba.JavaFX.Size;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * This Class has for objective to load a file and to return it as a game instance
 */

public class Load {
    /**
     * Method used to load a file
     * @param path a path coming from Path enum to know in wich resources directory we have to search
     * @param fileName the name of the file the game has to open
     * @return returning the instance of the Grid wich we be used to create the game then
     * @throws FileNotFoundException if the file game doesn't exist
     */


    public static Grid loadFile(Path path ,String fileName) throws FileNotFoundException {
        Grid loadGrid;
        
        File file = new File(path.getPath() + fileName);

        //Call the method find size to know th size of the grid
        Size size = findSize(file);
        loadGrid = new Grid(size);


        //Scanning the file we open
        Scanner scanner = new Scanner(file);
        char[] line;
        int i = 0;

        while (scanner.hasNextLine()){
            line = scanner.nextLine().toCharArray();
            for (int j = 0; j < line.length; j++) {
                //Switch made to load each entity on the grid
                switch (line[j]) {
                    case '#':
                        loadGrid.getGridAt(j, i).setImmovableContent(ImmovableContent.WALL);
                        break;
                    case ' ':
                        break;
                    case '@':
                        loadGrid.getGridAt(j, i).setMovableContent(MovableContent.PLAYER);
                        loadGrid.setPlayerLocation(j, i);
                        break;
                    case '$':
                        loadGrid.getGridAt(j, i).setMovableContent(MovableContent.BOX);
                        break;
                    case '.':
                        loadGrid.getGridAt(j, i).setImmovableContent(ImmovableContent.FLAG);
                        break;
                    case '+':
                        loadGrid.getGridAt(j, i).setMovableContent(MovableContent.PLAYER);
                        loadGrid.getGridAt(j, i).setImmovableContent(ImmovableContent.FLAG);
                        loadGrid.setPlayerLocation(j, i);
                        break;
                    case '*':
                        loadGrid.getGridAt(j, i).setImmovableContent(ImmovableContent.FLAG);
                        loadGrid.getGridAt(j, i).setMovableContent(MovableContent.BOX);
                        break;
                    default:
                        throw new IllegalStateException("Bad file format must be .xsb");
                }
            }
            i++;
        }
        return loadGrid;
    }


    /**
     * Take a file an return the size that the grid needs to be in order to contain it
     * if the file is too big for the grid (row > 19 or col > 32 : size determined in Size enum) it returns null
     * @param file xsb file that contains a map of a sokoban level
     * @return Size value of the Grid that must be constructed to contain the lvl or null if the lvl is too big
     */
    public static Size findSize(File file) throws FileNotFoundException {
        int nbCol = 0;
        int nbRow = 0;

        String line;

        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()){
            line = scanner.nextLine();
            nbRow++;
            if(nbCol < line.length()){
                nbCol = line.length();
            }
        }
        System.out.println( "size : " + nbCol + ','+ nbRow);

        return Size.determineSize(nbRow, nbCol);
    }


    public static void main(String[] args) {
        try {
            Grid test = loadFile(Path.SAVE,"testBis");
            Save.saving(test,Path.SAVE,"testBis2");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
