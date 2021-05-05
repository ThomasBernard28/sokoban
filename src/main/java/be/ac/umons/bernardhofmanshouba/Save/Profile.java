package be.ac.umons.bernardhofmanshouba.Save;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/**
 * This class create Profile instance with it's different information
 */

public class Profile {
    private String username;
    private int lvlCompleted = 0;
    private int[] bestMov = new int[10];

    /**
     * Constructor used by the jackson module and to create an empty profile
     */
    public Profile(){}

    public String getUsername() {
        if(username == null){
            return "New Profile";
        }
        return username;
    }

    public int getLvlCompleted() {
        return lvlCompleted;
    }

    public int[] getBestMov() {
        return bestMov;
    }

    protected void setBestMov(int nbrMov, int lvlNbr){
        if (this.bestMov[lvlNbr-1] == 0 || this.bestMov[lvlNbr-1] > nbrMov){
            this.bestMov[lvlNbr-1] = nbrMov;
        }
    }

    protected void setUsername(String username){
        this.username = username;
    }

    protected void setLvlCompleted (int lvl) {
        lvlCompleted = lvl;
    }


    protected void reset(){
        this.username = "New Profile";
        this.lvlCompleted = 0;
        this.bestMov = new int[10];
        System.out.println("ok");
    }

    protected boolean thisIsANewProfile(){
        return username.equals("New Profile");
    }

    @Override
    public String toString(){
        String jsonString = "";

        jsonString += "{";
        jsonString += "username:" + username;
        jsonString += ", lvlCompleted:" + lvlCompleted;
        jsonString += ", bestMov:" + Arrays.toString(bestMov);
        jsonString += "}";

        return jsonString;
    }

    /*
     *
     *static part
     *
     */

    private final static String path = Path.PROFILE.getPath() + "/profileList.json";

    /**
     * Read the json file located in resources/profileInfo
     * @return an array with the Profile saved in it
     * @throws IOException file is missing
     */
    private static Profile[] readJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Profile[] temp = objectMapper.readValue(new File(path), Profile[].class);
        writeJsonFile(temp);
        return temp;
    }

    /**
     * Write an array of Profile in a json file located in resources/profileInfo
     * @throws IOException file is missing
     */
    protected static void writeJsonFile(Profile[] activeProfile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), activeProfile);
    }

    // copy of active but for enum
    protected static Profile[] loadProfiles() {
        Profile[] saved = new Profile[3];
        Profile[] sorted = new Profile[3];
        try {
            saved = readJsonFile();
        } catch (IOException e){
            e.printStackTrace();
            // TODO create new file
        }


        // Making sure that the new profile will be at the end of the list
        int i = 0;
        int k = 1;
        for (Profile profile: saved){
            if(profile.thisIsANewProfile()){
                sorted[saved.length - k] = profile;
                k++;
            }else{
                sorted[i] = profile;
                i++;
            }
        }
        return sorted;
    }


    /*
     *
     *
     *Practice part
     *
     *
     */
    //To reset the profiles if an error occur
    public static void main(String[] args) throws IOException, ParseException {
        Profile[] defaultProfile = {
                new Profile(),
                new Profile(),
                new Profile()
        };
        writeJsonFile(defaultProfile);
        defaultProfile = readJsonFile();
        System.out.println(Arrays.toString(defaultProfile));
    }

}
