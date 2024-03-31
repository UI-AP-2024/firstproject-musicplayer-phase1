package model;

import java.util.ArrayList;

public class Playlist {
    private long id;
    private String nameOfPlaylist;
    private String nameOfCreator;
    private ArrayList<Audio> listAudio;

    public Playlist(long id, String nameOfPlaylist, String nameOfCreator, ArrayList<Audio> listAudio) {
        this.id = id;
        this.nameOfPlaylist = nameOfPlaylist;
        this.nameOfCreator = nameOfCreator;
        this.listAudio = listAudio;
    }

    public long getId() {
        return id;
    }

    public String getNameOfPlaylist() {
        return nameOfPlaylist;
    }

    public String getNameOfCreator() {
        return nameOfCreator;
    }

    public ArrayList<Audio> getListAudio() {
        return listAudio;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setNameOfPlaylist(String nameOfPlaylist) {
        this.nameOfPlaylist = nameOfPlaylist;
    }

    public void setNameOfCreator(String nameOfCreator) {
        this.nameOfCreator = nameOfCreator;
    }

    public void setListAudio(ArrayList<Audio> listAudio) {
        this.listAudio = listAudio;
    }
}
