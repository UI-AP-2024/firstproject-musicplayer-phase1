package Model;

import java.util.ArrayList;
public class FreeListener extends Listener {
    final static int maxAudio = 10;
    final static int maxPlaylists = 3;

    public static int getMaxPlaylists() {
        return maxPlaylists;
    }

    public static int getMaxAudio() {
        return maxAudio;
    }

    public FreeListener(String userName, String password, String firstAndLastName, String email, String phoneNumber,
                        int year, int month, int day, long accountCredit, ArrayList<Genre> favoriteGenres) {
        super(userName, password, firstAndLastName, email, phoneNumber,
                year, month, day, accountCredit, favoriteGenres, null);
    }

    public String toString() {
        return super.toString();
    }
}
