package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Free extends Listener{
    final static private int limitAddSong=10;
    final static private int limitNumberPlaylist=15;

    public Free(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberOfPlays, ArrayList<Gener> favoriteGener) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, accountCredit, playlists, numberOfPlays, null, favoriteGener);
    }

}
