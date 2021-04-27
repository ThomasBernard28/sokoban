package be.ac.umons.bernardhofmanshouba.Save;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

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

    protected void incrementLvlCompleted() {
        lvlCompleted ++;
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

    /*
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
    */
    /*
    public static int getBestMovForLvl(int lvlNumber){
        Profile[] profile = getActiveProfile();
        int currentBestMove = 0;

        for (Profile profileName: profile) {
            int[] bestMoves = profileName.getBestMov();
            if ((currentBestMove == 0 || currentBestMove > bestMoves[lvlNumber]) && bestMoves[lvlNumber] != 0){
                currentBestMove = bestMoves[lvlNumber];
            }
        }
        return currentBestMove;
    }
    public static String getBestProfile(int lvlNumber){
        Profile[] profile = getActiveProfile();
        String bestProfile = "";
        boolean played = false;

        for (Profile profileName: profile) {
            int[] bestMoves = profileName.getBestMov();

            if(getBestMovForLvl(lvlNumber) == bestMoves[lvlNumber] && getBestMovForLvl(lvlNumber) != 0){
                played = true;
                bestProfile = profileName.getUsername();
            }
        }
        if(played){
            return bestProfile;
        }
        else{
            return "NOT PLAYED";
        }
    }*/

}
