package model.Album;
import model.Audio.Music;
import java.util.ArrayList;

public class Album {
    private int albumId;
    private static int albumIdCounter=0;
    private String albumName;
    private String singerName;
    private ArrayList<Music> musicList;

    public Album(String albumName, String singerName, ArrayList<Music> musicList) {
        this.albumId = ++albumIdCounter;
        this.albumName = albumName;
        this.singerName = singerName;
        this.musicList = musicList;
    }

    @Override
    public String toString(){
        StringBuilder context=new StringBuilder();
        context.append("album id : ");
        context.append(albumId);
        context.append("\nalbum name : ");
        context.append(albumName);
        context.append("\nsinger name : ");
        context.append(singerName);
        context.append("\n");
        for(Music music:musicList){
            context.append(music.toString());
        }
        return context.toString();
    }
    public int getAlbumId() {
        return albumId;
    }
    public static int getAlbumIdCounter() {
        return albumIdCounter;
    }

    public String getAlbumName() {
        return albumName;
    }

    public void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public String getSingerName() {
        return singerName;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }
}
