package Model;

import java.util.ArrayList;

public class Playlists {
    private static int ID=0;
    private int IDCount;
    private String playlistName;
    private String userName;
    private ArrayList<AudioModel> audio;
    public Playlists(String playlistName,String userName ){
        IDCount = ++ID;
        this.playlistName = playlistName;
        this.userName = userName;
    }

    public static int getID() {
        return ID;
    }

    public int getIDCount() {
        return IDCount;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<AudioModel> getAudio() {
        return audio;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAudio(ArrayList<AudioModel> audio) {
        this.audio = audio;
    }
}
