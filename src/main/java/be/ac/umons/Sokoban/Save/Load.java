package be.ac.umons.Sokoban.Save;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.ImmovableContent;
import be.ac.umons.Sokoban.Entities.MovableContent;
import be.ac.umons.Sokoban.JavaFX.Size;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Load {

    public static Grid loadSavedFile(String fileName) throws FileNotFoundException {
        Grid loadGrid = null;

        File file = new File("src/main/resources/saves/" + fileName);

        Size size = findSize(file);
        loadGrid = new Grid(size);

        Scanner scanner = new Scanner(file);
        char[] line;
        int i = 0;

        while (scanner.hasNextLine()){
            line = scanner.nextLine().toCharArray();
            for (int j = 0; j < line.length; j++) {
                switch (line[j]) {
                    case '#':
                        loadGrid.getGridAt(j, i).setImmovableContent(ImmovableContent.WALL);
                        break;
                    case ' ':
                        break;
                    case '@':
                        loadGrid.getGridAt(j, i).setMovableContent(MovableContent.PLAYER);
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
            Grid test = loadSavedFile("testBis.xsb");
            Save.savingBis(test,"testBis2.xsb");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
