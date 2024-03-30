package model;

import model.Audio.Music;

import java.util.ArrayList;

public class Album {
    private static int tempID = 0;
    private final int id;
    private final String albumName;
    private final String artistName;
    private final ArrayList<Music> musics;

    public Album(int id, String albumName, String artistName) {
        this.id = ++tempID;
        this.albumName = albumName;
        this.artistName = artistName;
        this.musics = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getArtistName() {
        return artistName;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }
}
