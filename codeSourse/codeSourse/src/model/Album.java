package model;

import java.util.ArrayList;
import java.util.List;

public class Album {
    private int uniqeId;
    private String name;
    private String artistName;
    private List<String> songs;
    //*********************************************
    public Album(int uniqeId, String name, String artistName) {
        this.uniqeId = uniqeId;
        this.name = name;
        this.artistName = artistName;
        this.songs = new ArrayList<>();
    }
    //*********************************************
    public int getUniqeId() {
        return uniqeId;
    }

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public List<String> getSongs() {
        return songs;
    }
    //*********************************************
    public void setUniqeId(int uniqeId) {
        this.uniqeId = uniqeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void addSong(String songName) {
        songs.add(songName);
    }
    //*********************************************
    @Override
    public String toString() {
        return "Album{" +
                "uniqeId=" + uniqeId +
                ", name='" + name + '\'' +
                ", artistName='" + artistName + '\'' +
                ", songs=" + songs +
                '}';
    }
}