package model.user;

import java.util.Date;

public class FreeListener extends Listener {
    private static int playListLimit=3;
    private static int addSongToPLaylistLimit=10;
    public FreeListener(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit) {
        super(password, firstName, lastName, emailAddress, phoneNumber, birthDate, accountCredit);
    }
    public static int getPlayListLimit() {
        return playListLimit;
    }
    public static int getAddSongToPLaylistLimit() {
        return addSongToPLaylistLimit;
    }
       
}
