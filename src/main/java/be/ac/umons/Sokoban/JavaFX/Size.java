package be.ac.umons.Sokoban.JavaFX;

import be.ac.umons.Sokoban.JavaFX.Scenes.SceneTool;

import java.awt.*;

public enum Size {
    SMALL(20, 32), // 20, 32
    MEDIUM(14,23), // 14, 23
    LARGE(11,17); //11, 17

    private final int row;
    private final int col;

    protected final static int windowX = Toolkit.getDefaultToolkit().getScreenSize().width;
    protected final static int windowY = Toolkit.getDefaultToolkit().getScreenSize().height;

    Size( int row, int col){

        this.col = col;
        this.row = row;
    }

    public int getAdaptiveSize(){
         int sizeForY = (int) ((windowY / row) * 0.60);
         int sizeForX = (int) ((windowX / col) * 0.60);
         return Math.min(sizeForX, sizeForY);
    }

    public int getRow(){
        return row;
    }

    public int getCol() {
        return col;
    }

    public static Size determineSize(int row, int col){
        if(row <= LARGE.row && col <= LARGE.col){
            return LARGE;
        }else if(row <= MEDIUM.row && col <= MEDIUM.col){
            return MEDIUM;
        }else if(row <= SMALL.row && col <= SMALL.col){
            return SMALL;
        }else{
            System.out.println("null");
            return null;
        }
    }
}
