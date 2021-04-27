package be.ac.umons.bernardhofmanshouba.Save;

/**
 * Enum made in order to determine in wich directory we have to load/save file
 */

public enum Path {
    LVL("src/main/resources/levels/"),
    SAVE("src/main/resources/saves/"),
    UNIT_TEST_IN("src/test/resources/input/"),
    MOV("src/main/resources/history"),
    PROFILE("src/main/resources/profileInfo");

    private final String path;

    /**
     * Constructor of Path
     * @param path the absolute path used to create the enum
     */
    Path(String path){
        this.path = path;
    }

    public String getPath(){
        return path;
    }
}

