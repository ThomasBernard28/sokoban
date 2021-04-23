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
    private int lvlCompleted = 0;
    private int[] bestMov = new int[10];

    private final static String path = Path.PROFILE.getPath() + "/profileList.json";

    /**
     * Constructor used to create a profile already save
     * @param lvlCompleted nb of lvl already completed (int ranging from 0 to 10)
     * @param bestMov list with the best_mov for every level those not already succeeded will be 0
     */
    private Profile(String username, int lvlCompleted, int[] bestMov){
        this.username = username;
        this.lvlCompleted = lvlCompleted;
        this.bestMov = bestMov;
    }

    private Profile(String username){
        this.username = username;
    }

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

    public void setBestMov(int nbrMov, int lvlNbr){
        if (this.bestMov[lvlNbr] == 0){
            this.bestMov[lvlNbr] = nbrMov;
        }
        else if (this.bestMov[lvlNbr] > nbrMov){
            this.bestMov[lvlNbr] = nbrMov;
        }
        try{
            writeJsonFile(activeProfile);
        }catch (IOException e){
            throw new IndexOutOfBoundsException("File is missing");
        }
    }

    public void setUsername(String username){
        this.username = username;
        try{
            writeJsonFile(activeProfile);
        }catch (IOException e){
            throw new IndexOutOfBoundsException("File is missing");
        }
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
        jsonString += ", bestMov:" + Arrays.toString(bestMov);
        jsonString += "}";

        return jsonString;
    }

    private void reset(){
        this.username = "New Profile";
        this.lvlCompleted = 0;
        this.bestMov = new int[10];
    }

    public boolean thisIsANewProfile(){
        return username.equals("New Profile");
    }

    /*
     *
     *static part
     *
     */
    /**
     * List of active profile that is initiate at the start of the program by reading the json file
     * and saved in that file at every modification
     */
    private static Profile[] activeProfile = new Profile[3];

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
    private static void writeJsonFile(Profile[] activeProfile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(path), activeProfile);
    }


    public static void deleteProfile(Profile toDel){
        toDel.reset();
        try{
            writeJsonFile(activeProfile);
        }catch (IOException e){
            throw new IndexOutOfBoundsException("File is missing");
        }
    }


    /**
     * update the activeProfile by copying the info contained in the json file
     * @return the active profile updated
     */
    public static Profile[] getActiveProfile() {
        Profile[] saved = new Profile[3];
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
                activeProfile[saved.length - k] = profile;
                k++;
            }else{
                activeProfile[i] = profile;
                i++;
            }
        }
        return activeProfile;
    }

    /*
     *
     *
     *Practice part
     *
     *
     */
    public static void main(String[] args) throws IOException, ParseException {
        Profile[] defaultProfile = {
                new Profile(),
                new Profile(),
                new Profile()
        };
        writeFileJackson("profileList.json", defaultProfile);
        defaultProfile = readJsonFile();
        System.out.println(Arrays.toString(defaultProfile));
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
