/** This is the main class of the game
 *
 */

package be.ac.umons.Sokoban;

import be.ac.umons.Sokoban.JavaFX.MyWindow;
import be.ac.umons.Sokoban.JavaFX.Test;

public class Sokoban
{
    public static void main(String[] args)
    {
        switch(args.length){
            case 0:
                MyWindow.main(args);
                break;
            case 3:
                // TODO implement unit test
                break;
            default : throw new IllegalStateException("Veuillez entrer des arguments valables");
       }
    }

}
