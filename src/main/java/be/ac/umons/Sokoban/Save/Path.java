package be.ac.umons.Sokoban.Save;

public enum Path {
    LVL("src/main/resources/levels/"),
    SAVE("src/main/resources/saves/"),
    UNIT_TEST_IN("src/main/resources/unitTest/input"),
    UNIT_TEST_OUT("src/main/resources/unitTest/output"),
    PROFILE("src/main/resources/profileInfo");

    private final String path;

    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}

