package Model;

import java.util.ArrayList;

public class Playlist {
    private String ID;
    private String playlistName;
    private String userName;
    private ArrayList<Audio> audioList = new ArrayList<>();
    static int id=1;

    public Playlist(String playlistName, String userName) {
        String playlist=playlistName+"0";
        String playlistID = String.format("0");
        int Id = Integer.valueOf(playlistID) + id;
        id++;
        this.ID=playlist+Id;
        this.playlistName = playlistName;
        this.userName = userName;

    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID() {
        return ID;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }
    @Override
    public String toString(){
        return "ID: "+getID()+"\nPlayListName: "+getPlaylistName()+"\n";
    }
}
