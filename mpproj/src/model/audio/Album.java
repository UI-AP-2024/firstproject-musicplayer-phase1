package model.audio;

import java.util.*;

public class Album {
    private final long id;
    private String albumName;
    private String artistName;
    private ArrayList<Music> musicList;
    private static long albumCounter=0;
    public Album(String albumName, String artistName) {
        albumCounter++;
        this.albumName = albumName;
        this.artistName = artistName;
        this.musicList = new ArrayList<>();
        this.id=albumCounter;
    }
    public long getId() {
        return id;
    }
    public String getAlbumName() {
        return albumName;
    }
    public String getArtistName() {
        return artistName;
    }
    public ArrayList<Music> getMusicList() {
        return musicList;
    }
    public static long getAlbumCounter() {
        return albumCounter;
    }
    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public void setMusicList(ArrayList<Music> musicList) {//change to add
        this.musicList = musicList;
    }
    public void addToMusicList(Music music) {//change to add
        this.musicList.add(music);
    }
    public static void setAlbumCounter(long albumCounter) {
        Album.albumCounter = albumCounter;
    }
    
    
}
