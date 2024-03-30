package model;

import java.util.Date;

public class Free extends Listener{
    private static final int maxAddSongToPlaylist = 10;
    private static final int maxPlaylistMade = 5;

    public Free(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate);
    }

    public int getMaxAddSongToPlaylist(){
        return maxAddSongToPlaylist;
    }

    public static int getMaxPlaylistMade() {
        return maxPlaylistMade;
    }
}
