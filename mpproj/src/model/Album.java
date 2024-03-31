package model;

import java.util.ArrayList;

public class Album {
    private long id;
    private String name;
    private String singerName;
    private ArrayList<Music> musicList;

    public Album(long id, String name, String singerName, ArrayList<Music> musicList) {
        this.id = id;
        this.name = name;
        this.singerName = singerName;
        this.musicList = musicList;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSingerName() {
        return singerName;
    }

    public ArrayList<Music> getMusicList() {
        return musicList;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setMusicList(ArrayList<Music> musicList) {
        this.musicList = musicList;
    }
}
