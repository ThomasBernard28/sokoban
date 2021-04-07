package be.ac.umons.Sokoban.Save;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.MyWindow;
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


    public static void createNewFile(String fileName) throws IOException{
        String fileSeparator = System.getProperty("file.separator");
        String absoluteFilePath =  fileName+".xsb";
        File file = new File(absoluteFilePath);
        if(file.createNewFile()){
            System.out.println(absoluteFilePath+" File Created");
        }
        else{
            System.out.println("File " + absoluteFilePath + "already exists");
        }
    }

    public static void saving(Grid test, String fileName) {
        try {

            CharArrayWriter caw = new CharArrayWriter();
            for(char[] line : test.toCharArray()){
                caw.write(line);
            }

            FileWriter fw = new FileWriter("src/main/resources/saves/"+fileName+".xsb");
            caw.writeTo(fw);

            fw.flush();
            caw.flush();
            caw.close();


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        saving(MyWindow.logicGridGenesis(8), "test");
    }
}

