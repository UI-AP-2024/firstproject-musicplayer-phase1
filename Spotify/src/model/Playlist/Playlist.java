package model.Playlist;
import model.Audio.Audio;
import model.Audio.Music;

import java.util.ArrayList;

public class Playlist {
    private int playListId;
    private static int listIdCounter=0;
    private String playListName;
    private String creatingUserName;
    private ArrayList<Audio> audioList;

    public Playlist(String playListName, String creatingUserName, ArrayList<Audio> audioList) {
        this.playListId = ++listIdCounter;
        this.playListName = playListName;
        this.creatingUserName = creatingUserName;
        this.audioList = audioList;
    }

    @Override
    public String toString(){
        StringBuilder context=new StringBuilder();
        context.append("playlist id : ");
        context.append(playListId);
        context.append("\nplaylist name : ");
        context.append(playListName);
        context.append("\ncreating user name : ");
        context.append(creatingUserName);
        for(Audio audio:audioList){
            context.append(audio.toString());
        }
        return context.toString();
    }
    public int getPlayListId() {
        return playListId;
    }

    public static int getListIdCounter() {
        return listIdCounter;
    }

    public String getPlayListName() {
        return playListName;
    }

    public void setPlayListName(String playListName) {
        this.playListName = playListName;
    }

    public String getCreatingUserName() {
        return creatingUserName;
    }

    public void setCreatingUserName(String creatingUserName) {
        this.creatingUserName = creatingUserName;
    }

    public ArrayList<Audio> getAudioList() {
        return audioList;
    }

    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }
}
