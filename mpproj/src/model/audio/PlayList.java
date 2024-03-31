package model.audio;
import java.util.ArrayList;

public class PlayList {
    private String id;
    private String playListName;
    private String createrName;
    ArrayList<Audio> audioList;
    private static long playlistCounter=0;
    public PlayList(String playListName, String createrName) {
        playlistCounter++;
        this.playListName = playListName;
        this.createrName = createrName;
        audioList = new ArrayList<>();
        this.id = String.valueOf(playlistCounter)+"/"+playListName;
    }
    public String getId() {
        return id;
    }
    public String getPlayListName() {
        return playListName;
    }
    public String getCreaterName() {
        return createrName;
    }
    public ArrayList<Audio> getAudioList() {
        return audioList;
    }
    public static long getPlaylistCounter() {
        return playlistCounter;
    }
    // public void setId(String id) {
    //     this.id = id;
    // }
    public void setPlayListName(String playListName) {
        this.playListName = playListName;
        setId();
    }
    public void setCreaterName(String createrName) {
        this.createrName = createrName;
    }
    public void setAudioList(ArrayList<Audio> audioList) {
        this.audioList = audioList;
    }
    public static void setPlaylistCounter(long playlistCounter) {
        PlayList.playlistCounter = playlistCounter;
    }
    private void setId(){
        this.id = String.valueOf(playlistCounter)+"/"+playListName;
    }
    
    
}
