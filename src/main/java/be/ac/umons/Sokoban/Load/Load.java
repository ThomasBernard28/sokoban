package be.ac.umons.Sokoban.Load;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.Entities.ImmovableContent;
import be.ac.umons.Sokoban.Entities.MovableContent;
import be.ac.umons.Sokoban.Save.Save;

import java.io.*;
import java.util.Scanner;

public class Load {

    static String line;


    public static Grid loadSavedFile(String fileName) {
        try {
            File file = new File("src/main/resources/saves/" + fileName + ".xsb");
            int[] dimension = findMaxColAndRow(file);
            Grid loadGrid = new Grid(dimension[0], dimension[1]);
            Scanner scanner = new Scanner(file);
            int j;

            for (int i = 0; i < dimension[1]; i++) {
                line = scanner.nextLine();
                j = 0;
                for (char tile : line.toCharArray()) {
                    switch (tile) {
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
                    j++;
                }
            }
            return loadGrid;
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public static int[] findMaxColAndRow(File file){
        int nbrCol = 0;
        int nbrRow = 1;

        try{
            Scanner scanner = new Scanner(file);
            line= scanner.nextLine();
            nbrCol += line.length();
            while(scanner.hasNextLine()){
                line = scanner.nextLine();
                nbrRow++;
            }

        }catch(IOException e){
            e.printStackTrace();
        }

        return new int[] {nbrCol, nbrRow};
    }


    public static void main(String[] args) {
        Save.saving(loadSavedFile("level1out"), "level1out2");
    }
}
