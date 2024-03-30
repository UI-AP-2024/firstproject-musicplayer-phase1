package model;

import model.Audio.Audio;

import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String singerName;
    private ArrayList<Audio> songs;

    //cons???
    public Album(int id, String name, String singerName) {
        this.id = id;
        this.name = name;
        this.singerName = singerName;
        this.songs = new ArrayList<>();
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("album name");
        res.append(name).append("singer name : ").append(singerName).append("\n songs : ");
        for(Audio audio : songs){
            res.append(audio.getTitle());
        }
        return String.valueOf(res);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSingerName() {
        return singerName;
    }

    public ArrayList<Audio> getSongs() {
        return songs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSingerName(String singerName) {
        this.singerName = singerName;
    }

    public void setSongs(ArrayList<Audio> songs) {
        this.songs = songs;
    }
}
