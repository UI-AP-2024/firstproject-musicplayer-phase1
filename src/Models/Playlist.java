package Models;


import Models.Audio.Audio;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String playlistName;
    private String ownerUserName;
    private ArrayList<Audio> audioFiles;

    public Playlist(int id, String playlistName, String ownerUserName, ArrayList<Audio> audioFiles) {
        this.id = id;
        this.playlistName = playlistName;
        this.ownerUserName = ownerUserName;
        this.audioFiles = audioFiles;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public void setPlaylistName(String playlistName) {
        this.playlistName = playlistName;
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }

    public ArrayList<Audio> getAudioFiles() {
        return audioFiles;
    }

    public void setAudioFiles(ArrayList<Audio> audioFiles) {
        this.audioFiles = audioFiles;
    }
}
