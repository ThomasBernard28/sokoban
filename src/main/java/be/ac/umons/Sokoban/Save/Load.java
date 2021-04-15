package be.ac.umons.Sokoban.Save;

import be.ac.umons.Sokoban.Entities.Direction;
import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.ImmovableContent;
import be.ac.umons.Sokoban.Entities.MovableContent;
import be.ac.umons.Sokoban.JavaFX.Size;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Load {





    public static Grid loadFile(Path path ,String fileName) throws FileNotFoundException {
        Grid loadGrid;

        File file = new File(path.getPath() + fileName + ".xsb");

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
    public static char[] loadMov(String fileName, Path path){
        char []listChar=new char[1];
        //encore à faire mais comme ça ça met pas d'erreurs dans le programme
        return listChar;
    }

    public static Grid applyMov(String testFile, String movFile) throws FileNotFoundException {
        /*
        Load the files as a Grid and a char[] and apply the movements
        dite moi si j'ai écris un truc mauvais
         */
        Grid testGrid=loadFile(Path.UNIT_TEST_IN,testFile);
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



    public static void main(String[] args) {
        try {
            Grid test = loadFile(Path.SAVE,"testBis");
            Save.saving(test,Path.SAVE,"testBis2");
        } catch (IOException e){
            e.printStackTrace();
        }

    }
}
