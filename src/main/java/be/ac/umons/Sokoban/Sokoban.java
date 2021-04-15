/** This is the main class of the game
 *
 */

package be.ac.umons.Sokoban;

import be.ac.umons.Sokoban.Entities.Grid;
import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Test;
import be.ac.umons.Sokoban.Save.Load;
import be.ac.umons.Sokoban.Save.Path;
import be.ac.umons.Sokoban.Save.Save;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Sokoban
{
    public static void main(String[] args) throws IOException {
        switch(args.length){
            case 0:
                MyWindow.main(args);
                break;
            case 3:
                //Je suis pas sur de l'appel d'erreur sur le main mais j'ai quelques lacunes avec les exepetions encore
                Grid movedGrid= Load.applyMov(args[0],args[1]);
                Save.saving(movedGrid, Path.UNIT_TEST_OUT,args[2]);
                break;
            default : throw new IllegalStateException("Veuillez entrer des arguments valables");
       }
    }

}
