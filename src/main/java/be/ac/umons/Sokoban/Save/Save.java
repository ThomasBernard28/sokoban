package be.ac.umons.Sokoban.Save;

import be.ac.umons.Sokoban.MapGeneration.Grid;
import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Size;

import java.io.*;


public class Save {

    /**
     * Write a Grid into a file that must not yet exist
     * @param logicGrid Grid that will be copy
     * @param fileName new file in which the grid will be saved
     * @throws IOException if the file already exists
     */
    public static void saving(Grid logicGrid, Path path, String fileName) throws IOException {
        String gridToTxt = "";
        for(int i = 0; i < logicGrid.getSize().getRow(); i++){
            for (int j = 0; j < logicGrid.getSize().getCol(); j++) {
                gridToTxt += logicGrid.getGridAt(j, i);
                if(j == logicGrid.getSize().getCol() - 1){
                    gridToTxt += "\n";
                }
            }
        }

        File file = new File(path.getPath() + fileName + ".xsb");
        //before writing check if the file has been created
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
            test.setBox(2,2);
            saving(test , Path.SAVE, "testBis");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

