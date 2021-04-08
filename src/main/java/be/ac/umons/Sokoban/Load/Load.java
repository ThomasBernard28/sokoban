package be.ac.umons.Sokoban.Load;

import be.ac.umons.Sokoban.Entities.Tile;
import be.ac.umons.Sokoban.Save.Save;

import java.io.*;
import java.util.Scanner;

public class Load {
    static char[][] charArray;
    static char[] tempArray;
    static String line;
    static int nbrCol = 0;
    static int nbrRow = 0;

    public static void loadSavedFile(String fileName) {
        try {
            File file = new File("src/main/resources/saves/" + fileName + ".xsb");
            Scanner scanner = new Scanner(file);

            line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                line = line + "\n" + scanner.nextLine();
            }


            tempArray = line.toCharArray();


            //Get the nbr of row and column for the char[][]
            int i = 0;
            while(i < tempArray.length){
                // If the char is the symbol of end of line
                if (tempArray[i] == '_'){
                    //the number of column is fixed
                    nbrCol = i;
                    nbrRow = (tempArray.length / (nbrCol+1));
                    break;
                }
                else{
                    i++;
                }

            }
            charArray = new char[nbrRow][nbrCol+1];
            int row=0;
            int col =0;

            for(int j = 0; j < tempArray.length; j++){
                if (row == 0){
                    if (tempArray[j] == '_'){
                        charArray[row][col] = ' ';
                        row++;
                        col = 0;
                    }
                    else{
                        charArray[row][col] = tempArray[j];
                        col++;
                    }
                }
                else{
                    if (tempArray[j] == '_'){
                        row++;
                        col =0;
                    }
                    else{
                        charArray[row][col] = tempArray[j];
                        col++;
                    }
                }
            }


        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void loadLevelFile(String fileName){
        try{
            File file = new File("src/main/resources/levels/" + fileName + ".xsb");
            Scanner scanner = new Scanner(file);

            line = scanner.nextLine();
            while (scanner.hasNextLine()){
                line = line + "\n" + scanner.nextLine();
            }
            tempArray = line.toCharArray();
            //Get the nbr of row and column for the char[][]
            int i = 0;
            while(i < tempArray.length){
                // If the char is the symbol of end of line
                if (tempArray[i] == '_'){
                    //the number of column is fixed
                    nbrCol = i;
                    nbrRow = (tempArray.length / nbrCol);
                    break;
                }
                else{
                    i++;
                }

            }
            charArray = new char[nbrRow][nbrCol];
            int row=0;
            int col =0;

            for(int j = 0; j < tempArray.length; j++){
                if (row == 0){
                    if (tempArray[j] == '_'){
                        charArray[row][col] = ' ';
                        row++;
                        col = 0;
                    }
                    else{
                        charArray[row][col] = tempArray[j];
                        col++;
                    }
                }
                else{
                    if (tempArray[j] == '_'){
                        row++;
                        col =0;
                    }
                    else{
                        charArray[row][col] = tempArray[j];
                        col++;
                    }
                }
            }

        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static void savingCharArray(char[][] test2, String fileName2){
        try{
            CharArrayWriter caw = new CharArrayWriter();
            for (char[] line: charArray) {
                System.out.println(line);
                caw.write(line);
            }
            FileWriter fw = new FileWriter("src/main/resources/saves/" + fileName2 + ".xsb");
            caw.writeTo(fw);

            fw.flush();
            caw.flush();
            caw.close();

        }catch(IOException e){
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        loadSavedFile("test");
        savingCharArray(charArray, "test2");
    }
}
