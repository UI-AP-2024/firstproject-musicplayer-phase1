package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Free extends Listener{
    final private int limitAddSong;
    final private int limitNumberPlaylist;
    private int countAddSong;
    private int countNumberPlaylist;

    @Override
    public String toString(){
        String result = "";
        result += super.toString();
        return result;
    }

    public Free(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberPlays, ArrayList<Gener> favoriteGener) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, accountCredit, playlists, numberPlays, null, favoriteGener);
        limitAddSong=10;
        limitNumberPlaylist=3;
        countAddSong=0;
        countNumberPlaylist=0;
    }

    public int getLimitAddSong() {
        return limitAddSong;
    }

    public int getLimitNumberPlaylist() {
        return limitNumberPlaylist;
    }

    public int getCountAddSong() {
        return countAddSong;
    }

    public int getCountNumberPlaylist() {
        return countNumberPlaylist;
    }

    public void setCountAddSong(int countAddSong) {
        this.countAddSong = countAddSong;
    }

    public void setCountNumberPlaylist(int countNumberPlaylist) {
        this.countNumberPlaylist = countNumberPlaylist;
    }
}
