package model;

import java.util.ArrayList;

public class Playlist {
    private int id;
    private String playListName;
    private String userGeneratorName;
    private ArrayList<Audio> audioList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getUserGeneratorName() {
        return userGeneratorName;
    }

    public void setUserGeneratorName(String userGeneratorName) {
        this.userGeneratorName = userGeneratorName;
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }
}
