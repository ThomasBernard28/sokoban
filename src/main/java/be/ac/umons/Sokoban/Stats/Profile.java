package be.ac.umons.Sokoban.Stats;

public class Profile {

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
        createProfile(profileNumber, profileName);

    }
    public void createProfile(ProfileNumber profileNumber, String profileName){

    }
}
