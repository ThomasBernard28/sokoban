package be.ac.umons.Sokoban.Save;

public enum Path {
    LVL("src/main/resources/levels/"),
    SAVE("src/main/resources/saves/"),
    UNIT_TEST("");

    private final String path;

    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}

