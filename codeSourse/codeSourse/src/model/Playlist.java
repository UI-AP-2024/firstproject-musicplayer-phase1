package model;

import java.util.ArrayList;
import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private String userName;
    private List<String> audios;

    //*********************************************
    public Playlist(int id, String name, String userName) {
        this.id = id;
        this.name = name;
        this.userName = userName;
        this.audios = new ArrayList<>();
    }

    //*********************************************
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getAudios() {
        return audios;
    }
    //*********************************************

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    //*********************************************

    public void addAudio(String audio){
        audios.add(audio);
    }
}