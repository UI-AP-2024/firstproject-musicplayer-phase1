package model;

import java.util.ArrayList;

public class Singer extends Artist{
    private ArrayList<Album> albums = new ArrayList<>();

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
