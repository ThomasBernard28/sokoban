package be.ac.umons.bernardhofmanshouba.Save;

import be.ac.umons.bernardhofmanshouba.MapGeneration.Grid;

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
                //add every entity symbol to the string
                gridToTxt += logicGrid.getGridAt(j, i);
                if(j == logicGrid.getSize().getCol() - 1){
                    gridToTxt += "\n";
                }
            }
        }

        File file = new File(path.getPath() + fileName);
        //before writing check if the file has been created
        boolean success = file.createNewFile();
        if(success){
            FileWriter writer = new FileWriter(file);

            writer.write(gridToTxt);
            writer.flush();
            writer.close();
        }
    }
}

