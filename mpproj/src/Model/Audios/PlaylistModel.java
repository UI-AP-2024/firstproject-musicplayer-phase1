package Model.Audios;

import java.util.ArrayList;

public class PlaylistModel {
    private final int ID;
    private String playListName;
    private String ownerUserName;
    private ArrayList<AudioModel> audioList;

    private static int playlistCount = 1;

    public PlaylistModel(String playListName , String ownerUserName){
        this.ID = playlistCount++;
        this.playListName = playListName;
        this.ownerUserName = ownerUserName;
        this.audioList = new ArrayList<AudioModel>();
    }

    public int getID(){
        return this.ID;
    }

    public String getPlayListName(){
        return this.playListName;
    }

    public void setPlayListName(String playListName){
        this.playListName = playListName;
    }
    public String getOwnerUserName(){
        return this.ownerUserName;
    }

    public void setOwnerUserName(String newUsername){
        this.ownerUserName = newUsername;
    }
    public ArrayList<AudioModel> getAudioList(){
        return  this.audioList;
    }

    public void addAudio(AudioModel audio){
        this.audioList.add(audio);
    }

    @Override
    public String toString(){
        return "Playlist Name : "+ this.playListName + "\nCreated by : "+ this.ownerUserName;
    }
}
