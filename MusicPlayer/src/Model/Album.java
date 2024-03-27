package Model;

import java.util.ArrayList;

public class Album {
    private static int ID=0;
    private int IDCount;
    private String albumName;
    private String singerName;
    private ArrayList<Music> musics;
    public Album(String albumName,String singerName){
        IDCount=++ID;
        this.albumName = albumName;
        this.singerName = singerName;
        this.musics = new ArrayList<>();
    }

    public static int getID() {
        return ID;
    }

    public int getIDCount() {
        return IDCount;
    }

    public String getAlbumName() {
        return albumName;
    }

    public String getSingerName() {
        return singerName;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public static void setID(int ID) {
        Album.ID = ID;
    }

    public void setIDCount(int IDCount) {
        this.IDCount = IDCount;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setMusics(ArrayList<Music> musics) {
        this.musics = musics;
    }
}
