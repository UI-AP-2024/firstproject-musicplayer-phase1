package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Free extends Listener{
    final private int limitAddSong;
    final private int limitNumberPlaylist;

    public Free(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberOfPlays, ArrayList<Gener> favoriteGener) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, accountCredit, playlists, numberOfPlays, null, favoriteGener);
        limitAddSong=10;
        limitNumberPlaylist=3;
    }

    public int getLimitAddSong() {
        return limitAddSong;
    }

    public int getLimitNumberPlaylist() {
        return limitNumberPlaylist;
    }


}
