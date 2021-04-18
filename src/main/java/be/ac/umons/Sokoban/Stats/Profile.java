package be.ac.umons.Sokoban.Stats;
import be.ac.umons.Sokoban.Save.Path;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

import org.json.simple.JSONObject;

public class Profile {
    // https://stackabuse.com/reading-and-writing-json-in-java/

    public enum ProfileNumber{
        PROFILE_1(1),
        PROFILE_2(2),
        PROFILE_3(3);

        private final int profileNumber;

        ProfileNumber(int profileNumber) {
            this.profileNumber = profileNumber;
        }
        public int getProfileNumber(){
            return profileNumber;
        }


    }
    public Profile(ProfileNumber profileNumber, String profileName){
        //createProfile(profileNumber, profileName);

    }
    public void createProfile(ProfileNumber profileNumber, String profileName)  {


    }

    /*
     *
     *instance part
     *
     */


    private String username;
    private int lvlCompleted;
    private double[] bestTime;

    private final static String path = Path.PROFILE.getPath() + "/profileList.json";

    /**
     * Constructor used to create a new profile
     */
    private Profile(String username){
        this.username = username;
        this.lvlCompleted = 0;
        this.bestTime = new double[10];

    }

    /**
     * Constructor used to create a profile already save
     * @param lvlCompleted nb of lvl already completed (int ranging from 0 to 10)
     * @param bestTime list with the best_time for every level those not already succeeded will be 0
     */
    private Profile(String username, int lvlCompleted, double[] bestTime){
        this.username = username;
        this.lvlCompleted = lvlCompleted;
        this.bestTime = bestTime;
    }

    /**
     * Constructor used only by the jackson module
     */
    public Profile(){}

    public String getUsername() {
        return username;
    }

    public int getLvlCompleted() {
        return lvlCompleted;
    }

    public double[] getBestTime() {
        return bestTime;
    }

    public void setUsername(String username){
        this.username = username;
    }
    /**
     * Save the Profile in the json file
     * @throws IOException
     * @throws ParseException
     */
    public void register() throws IOException {
        // we retrieve the profile already saved
        Profile[] currProfileList = readJsonFile();

        // new list with space for new profile
        Profile[] newProfileList = new Profile[currProfileList.length + 1];

        // copy the already existent profile
        System.arraycopy(currProfileList, 0, newProfileList, 0, currProfileList.length);

        // add the new profile
        newProfileList[currProfileList.length] = this;
        // save changes into the file
        writeJsonFile(newProfileList);

    }

    @Override
    public String toString(){
        String jsonString = "";

        jsonString += "{";
        jsonString += "username:" + username;
        jsonString += ", lvlCompleted:" + lvlCompleted;
        jsonString += ", bestTime:" + Arrays.toString(bestTime);
        jsonString += "}";

        return jsonString;
    }

    /*
     *
     *static part
     *
     */

    /**
     * Read the json file located in resources/profileInfo
     * @return an array with the Profile saved in it
     * @throws IOException file is missing
     */
    public static Profile[] readJsonFile() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(path), Profile[].class);
    }

    /**
     * Write an array of Profile in a json file located in resources/profileInfo
     * @param profile array of Profile
     * @throws IOException file is missing
     */
    public static void writeJsonFile(Profile[] profile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), profile);
    }

    /**
     * Delete the profile save in the file
     */
    public static void resetProfile(){
        try {
            writeJsonFile(new Profile[0]);
        } catch (IOException e){
            e.printStackTrace();
        }
    }


    /*
     *
     *
     *Practice part
     *
     *
     */
    public static void main(String[] args) throws IOException, ParseException {
        Profile[] users = readJsonFile();
        System.out.println(Arrays.toString(users));
    }


    public static void writeJsonFile(String filename) throws IOException{
        JSONObject profiles = new JSONObject();
        profiles.put("name", "Augustin");
        profiles.put("levelCompleted", 0);
        profiles.replace("levelCompleted", ((int) profiles.get("levelCompleted") + 1) % 10);

        JSONArray msg = new JSONArray();
        msg.add("It's me");
        msg.add("MARIO !");

        profiles.put("msg", msg);
        Files.write(Paths.get(Path.PROFILE.getPath() +"/" + filename), profiles.toJSONString().getBytes());
    }

    public static Object readJsonFile(String filename) throws ParseException, IOException {
        FileReader reader = new FileReader(Path.PROFILE.getPath() +"/" + filename);
        JSONParser parser = new JSONParser();
        return parser.parse(reader);
    }

    public static Profile[] readFileJackson(String filename) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(Path.PROFILE.getPath() + "/" + filename), Profile[].class);
    }

    public static void writeFileJackson(String filename, Profile[] profile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(Path.PROFILE.getPath() + "/" + filename), profile);
    }



}
