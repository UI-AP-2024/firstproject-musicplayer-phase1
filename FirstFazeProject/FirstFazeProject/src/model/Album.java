package model;

import java.util.ArrayList;

public class Album {
    private int uniqueId;
    private String albumName;
    private String singerName;
    private ArrayList<Audio> audioList = new ArrayList<>();

    public Album(String albumName, String singerName) {
        this.albumName = albumName;
        this.singerName = singerName;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
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

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }

    @Override
    public String toString(){
        String result = null;
        result += "Album's ID : "+this.uniqueId+"\r\n"+"Album's name Artist : "+this.albumName+"\r\n"
                +"Singer's name : " +"\r\n"+this.singerName+"Album's audios : " +this.audioList.size();
        return result;
    }
}
