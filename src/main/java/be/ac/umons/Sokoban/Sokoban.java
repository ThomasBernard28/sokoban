

package be.ac.umons.Sokoban;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Test;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import be.ac.umons.Sokoban.Save.Save;
import be.ac.umons.Sokoban.Test.UnitTest;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Main class of the game whe the is launched
 * You can also start unitTest by using commands line using the args
 */
public class Sokoban
{
    /**
     * Main method wich launch the game
     * @param args args given by lauching the game
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Switch used to know if the user wants to play the game or launch a unit test
        switch(args.length){
            //Run the game
            case 0:
                Test.main(args);
                break;
            //Run unit Test
            case 3:
                Grid movedGrid= UnitTest.applyMov(args[0],args[1]);
                Save.saving(movedGrid, Path.UNIT_TEST_OUT,args[2]);
                break;
            default : throw new IllegalStateException("Veuillez entrer des arguments valables");
       }
    }
}
