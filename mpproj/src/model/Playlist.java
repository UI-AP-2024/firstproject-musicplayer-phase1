package model;

import model.Audio.Audio;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String name;
    private String creatorUsername;
    private ArrayList<Audio> audios;

    //cons???
    public Playlist(int id, String name, String creatorUsername, ArrayList<Audio> audios) {
        this.id = id;
        this.name = name;
        this.creatorUsername = creatorUsername;
        this.audios = audios;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreatorUsername() {
        return creatorUsername;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreatorUsername(String creatorUsername) {
        this.creatorUsername = creatorUsername;
    }

    public void setAudios(ArrayList<Audio> audios) {
        this.audios = audios;
    }
}
