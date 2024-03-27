package model;

import model.Audio.Audio;

import java.util.ArrayList;

public class Album {
    private int id;
    private String name;
    private String singerName;
    private ArrayList<Audio> songs;

    //cons???
    public Album(int id, String name, String singerName, ArrayList<Audio> songs) {
        this.id = id;
        this.name = name;
        this.singerName = singerName;
        this.songs = songs;
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
