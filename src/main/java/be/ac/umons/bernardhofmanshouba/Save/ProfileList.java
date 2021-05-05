package be.ac.umons.bernardhofmanshouba.Save;

import java.io.IOException;

/**
 * This class manage the list of the profiles
 * There is a enumeration to get every profile separately
 */

public enum ProfileList {
    PROFILE1,
    PROFILE2,
    PROFILE3;

    private final Profile linkedProfile = new Profile();

    /**
     * Accessor method to a profile
     * @return return the profile linked to the button
     */
    public Profile getProfile() {
        return linkedProfile;
    }

    /**
     * Method to increment the number of completed level after each
     * campaign method complete
     * @param lvlNumber The number of level that has been completed
     */
    public void incrementLvlCompleted(int lvlNumber){
        linkedProfile.setLvlCompleted(lvlNumber);
        saveProfileList();
    }

    /**
     * Check if it's a new profile
     * @return boolean true/false for the new profile
     */
    public boolean isNew(){
        return linkedProfile.thisIsANewProfile();
    }

    /**
     * Mutator method to update the best move of a player
     * in a specific level of the game. Used for the Stats
     * @param move Number of moves
     * @param lvl Level number
     */
    public void setBestMov(int move, int lvl) {
        linkedProfile.setBestMov(move, lvl);
        saveProfileList();
    }

    /**
     * Mutator method to set the Username when creating a profile.
     * @param username Username given by the new player
     */
    public void setUsername(String username) {
        linkedProfile.setUsername(username);
        saveProfileList();
    }

    /*
     *
     * static part
     *
     */

    /**
     * Method to create the profile list
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

    /**
     * Method to saves the different values of the profiles
     * inside the ProfileList
     */
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

    /**
     * Method to delete a specific profile
     * @param toDel The profile that has to be deleted
     */
    public static void deleteProfile(ProfileList toDel){
        toDel.getProfile().reset();
        saveProfileList();
    }

    /**
     * Method used in the StatScene to determine wich was the best moves number
     * for a specific level.
     * @param lvl Level number
     * @return the lowest number of moves for the level
     */
    public static int getBestMoveForLvl(int lvl) {
        int bestMove = 0;
        for (ProfileList profile : values()) {
            if (bestMove < profile.getProfile().getBestMov()[lvl] || bestMove == 0) {
                bestMove = profile.getProfile().getBestMov()[lvl];
            }
        }

        return bestMove;
    }

    /**
     * Method used in the StatScene to determine who was the best player
     * for a specific level.
     * @param lvl Level number
     * @return Name of the best player for the level
     */
    public static String getBestPofileName(int lvl) {
        ProfileList best = PROFILE1;
        int bestMove = getBestMoveForLvl(lvl);

        //Display Not played if no one played that level
        if (bestMove == 0) {
            return "Not Played";
        }

        //If the level was played we check who made the best score
        for (ProfileList profile : values()) {
            if (profile.getProfile().getBestMov()[lvl] < bestMove && profile.getProfile().getBestMov()[lvl] != 0) {
                best = profile;
            }
        }

        return best.getProfile().getUsername();
    }
}
