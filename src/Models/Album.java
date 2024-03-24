package Models;

import Models.Audio.Song;

import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String artistName;
    private ArrayList<Song> songs;

    public Album(int id, String name, String artistName, ArrayList<Song> songs) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.songs = songs;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }
}
