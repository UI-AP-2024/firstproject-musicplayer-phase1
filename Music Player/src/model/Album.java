package model;

import java.util.ArrayList;

public class Album
{
    private int j = 0;
    private String name;
    private String artistName;
    ArrayList<Music> album = new ArrayList<Music>();

    public Album(String name, String artistName) {
        this.name = name;
        this.artistName = artistName;
        j = ++j;
    }

    public int getJ() {
        return j;
    }

    public void setJ(int j) {
        this.j = j;
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

    public ArrayList<Music> getAlbum() {
        return album;
    }

    public void setAlbum(ArrayList<Music> album) {
        this.album = album;
    }
}
