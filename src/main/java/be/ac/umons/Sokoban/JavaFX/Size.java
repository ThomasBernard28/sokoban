package be.ac.umons.Sokoban.JavaFX;

public enum Size {
    SMALL(32, 19, 32), // 19, 32
    MEDIUM(48, 14,21), // 14, 21
    LARGE(64, 11,16); //11, 16

    private final int cellSize;
    private final int row;
    private final int col;

    Size(int cellSize, int row, int col){
        this.cellSize = cellSize;
        this.col = col;
        this.row = row;
    }

    public int getSize(){
       return cellSize;
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
        }else if(row < SMALL.row && col <= SMALL.col){
            return SMALL;
        }else{
            return null;
        }
    }
}
