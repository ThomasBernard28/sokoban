package be.ac.umons.Sokoban.Save;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Size;
import be.ac.umons.Sokoban.Test.ConsoleGrid;
import be.ac.umons.Sokoban.Entities.Tile;

import java.io.*;
import java.nio.Buffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;


public class Save {

    public static void saving(Grid test, String fileName) throws IOException {

        CharArrayWriter caw = new CharArrayWriter();
        for(char[] line : test.toCharArray()){
            caw.write(line);
        }

        FileWriter fw = new FileWriter("src/main/resources/saves/"+fileName+".xsb");
        caw.writeTo(fw);

        fw.flush();
        caw.flush();
        caw.close();
    }

    /**
     * Write a Grid into a file that must not yet exist
     * @param logicGrid Grid that will be copy
     * @param fileName new file in which the grid will be saved the extension must be .xsb
     * @throws IOException if the file already exists
     */
    public static void savingBis(Grid logicGrid, String fileName) throws IOException {
        String gridToTxt = "";
        for(int i = 0; i < logicGrid.getSize().getRow(); i++){
            for (int j = 0; j < logicGrid.getSize().getCol(); j++) {
                gridToTxt += logicGrid.getGridAt(j, i);
                if(j == logicGrid.getSize().getCol() - 1){
                    gridToTxt += "\n";
                }
            }
        }

        File file = new File("src/main/resources/saves/" + fileName);
        boolean success = file.createNewFile();
        if(success){
            FileWriter writer = new FileWriter(file);

            writer.write(gridToTxt);
            writer.flush();
            writer.close();
        }
    }

    public static void main(String[] args) {
        try {
            Grid test = MyWindow.logicGridGenesis(Size.SMALL);
            test.set_boxes(2,2);
            savingBis(test , "testBis.xsb");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

