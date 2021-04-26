package be.ac.umons.Sokoban.Save;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public enum ProfileList {
    PROFILE1,
    PROFILE2,
    PROFILE3;

    private final Profile linkedProfile = new Profile();

    public Profile getProfile() {
        return linkedProfile;
    }

    public void incrementLvlCompleted(){
        linkedProfile.incrementLvlCompleted();
        saveProfileList();
    }

    public boolean isNew(){
        return linkedProfile.thisIsANewProfile();
    }

    public void setBestMov(int move, int lvl) {
        linkedProfile.setBestMov(move, lvl);
        saveProfileList();
    }

    public void setUsername(String username) {
        linkedProfile.setUsername(username);
        saveProfileList();
    }

    /*
     *
     * static part
     *
     */

    public static void initiateProfileList() {
        Profile[] newProfiles = Profile.loadProfiles();
        for (int i = 0; i < 3; i++) {
            values()[i].getProfile().setUsername(newProfiles[i].getUsername());
            values()[i].getProfile().setLvlCompleted(newProfiles[i].getLvlCompleted());
            for (int j = 0; j < 10; j++) {
                values()[i].getProfile().getBestMov()[j] =
                        newProfiles[i].getBestMov()[j];
            }
        }
    }

    public static void saveProfileList() {
        Profile[] currProfiles = new Profile[3];

        int i = 0;
        for (ProfileList profile : values()){
            currProfiles[i] = profile.getProfile();
            i++;
        }

        try{
            Profile.writeJsonFile(currProfiles);
        } catch (IOException e){
            throw new IllegalStateException("File is missing");
        }
    }

    public static void deleteProfile(ProfileList toDel){
        toDel.getProfile().reset();
        saveProfileList();
    }

    public static int getBestMoveForLvl(int lvl) {
        int bestMove = 0;
        for (ProfileList profile : values()) {
            if (bestMove < profile.getProfile().getBestMov()[lvl] || bestMove == 0) {
                bestMove = profile.getProfile().getBestMov()[lvl];
            }
        }

        return bestMove;
    }

    public static String getBestPofileName(int lvl) {
        ProfileList best = PROFILE1;
        int bestMove = getBestMoveForLvl(lvl);

        if (bestMove == 0) {
            return "Not Played";
        }

        for (ProfileList profile : values()) {
            if (profile.getProfile().getBestMov()[lvl] < bestMove && profile.getProfile().getBestMov()[lvl] != 0) {
                best = profile;
            }
        }

        return best.getProfile().getUsername();
    }
}
