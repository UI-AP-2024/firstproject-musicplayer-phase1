package Model.Accounts.Listeners;

import Model.Audios.Genre;
import Model.Audios.PlaylistModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class FreeListenerModel extends ListenerModel {
    private final int maxAddToPlaylistLimit;
    private Map<PlaylistModel , Integer> playlistAudioCount;
    private final int playlistCountLimit;
    private int playlistCount;

    public FreeListenerModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        super(username , password , fullName , email , phoneNumber , birthDate);

        super.setCredit(50);
        this.maxAddToPlaylistLimit = 10;
        this.playlistAudioCount = new HashMap<PlaylistModel , Integer>();
        this.playlistCountLimit = 3;
        this.playlistCount = 0;
    }

    public int getMaxAddToPlaylistLimit(){
        return this.maxAddToPlaylistLimit;
    }

    public Map<PlaylistModel , Integer> getPlaylistAudioCount(){
        return this.playlistAudioCount;
    }

    public void setPlaylistAudioCount(PlaylistModel playlist){
        this.playlistAudioCount.put(playlist , 0);
    }
    public void AddToPlaylistAudioCount(PlaylistModel playlist){
        this.playlistAudioCount.merge(playlist , 1 , Integer::sum);
    }

    public int getPlaylistCountLimit(){
        return this.playlistCountLimit;
    }

    public int getPlaylistCount(){
        return this.playlistCount;
    }

    public void setPlaylistCount(int count){
        this.playlistCount = count;
    }

    public void setPlaylistCount(){
        this.playlistCount++;
    }
}
