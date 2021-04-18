package be.ac.umons.Sokoban.Stats;

public class Profile {

    public enum ProfileNumber{
        PROFILE_1("New Profile", 1),
        PROFILE_2("New Profile", 2),
        PROFILE_3("New Profile", 3);

        private final String profileName;
        private final int profileNumber;

        ProfileNumber(String profileName, int profileNumber) {
            this.profileName = profileName;
            this.profileNumber = profileNumber;
        }
    }
}
