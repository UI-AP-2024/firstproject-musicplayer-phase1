package Model;

import java.util.ArrayList;

public class Album {
    private String ID;
    private String albumName;
    private String singerName;
    private ArrayList<Music> musicsList = new ArrayList<>();
    static int id=1;

    public Album(String albumName, String singerName) {
        String album=albumName+"0";
        String playlistID = String.format("0");
        int Id = Integer.valueOf(playlistID) + id;
        id++;
        this.ID=album+Id;
        this.albumName = albumName;
        this.singerName = singerName;
    }

    public ArrayList<Music> getMusicsList() {
        return musicsList;
    }

    public void setMusicsList(ArrayList<Music> musicsList) {
        this.musicsList = musicsList;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

}
