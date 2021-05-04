

package be.ac.umons.bernardhofmanshouba;

import be.ac.umons.bernardhofmanshouba.JavaFX.MyWindow;
import be.ac.umons.bernardhofmanshouba.Test.ApplyHistory;

import java.io.IOException;

/**
 * Main class of the game whe the is launched
 * You can also start the Console Mode by using commands line using the args
 */
public class Sokoban
{
    /**
     * Main method wich launch the game
     * @param args args given by lauching the game
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        //Switch used to know if the user wants to play the game or launch the console mode
        switch(args.length){
            //Run the game
            case 0:
                MyWindow.main(args);
                break;
            //Run console mode
            case 3:
                ApplyHistory.applyMov(args[0], args[1], args[2]);
                break;
            default : throw new IllegalStateException("Veuillez entrer des arguments valables");
       }
    }
}
