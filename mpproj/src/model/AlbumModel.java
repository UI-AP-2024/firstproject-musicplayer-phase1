package model;

import model.Audio.MusicModel;

import java.util.ArrayList;

public class AlbumModel {
    private static int tempID = 0;
    private final int id;
    private String albumName;
    private String artistName;
    private ArrayList<MusicModel> musics;

    public AlbumModel(int id, String albumName, String artistName) {
        this.id = ++tempID;
        this.albumName = albumName;
        this.artistName = artistName;
        this.musics = new ArrayList<MusicModel>();
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

    public ArrayList<MusicModel> getMusics() {
        return musics;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setMusics(ArrayList<MusicModel> musics) {
        this.musics = musics;
    }
}
