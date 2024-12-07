package Model.Accounts.Listeners;

import Model.Accounts.AccountsModel;
import Model.Accounts.AccountsModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Audios.AudioModel;
import Model.Audios.Genre;
import Model.Audios.PlaylistModel;

import java.beans.beancontext.BeanContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ListenerModel extends AccountsModel {
    private double credit;
    private ArrayList<PlaylistModel> playlists;
    private Map<AudioModel, Integer> audioPlayCount;
    private Date subscriptionExpiration;
    private ArrayList<Genre> favoriteGenres;
    private ArrayList<AudioModel> likedAudios;
    private ArrayList<ArtistModel> followingList;

    public ListenerModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate){
        super(username , password , fullName , email , phoneNumber , birthDate);

        this.credit = 0;
        this.playlists = new ArrayList<PlaylistModel>();
        this.audioPlayCount = new HashMap<AudioModel , Integer>();
        this.favoriteGenres = new ArrayList<Genre>();
        this.likedAudios = new ArrayList<AudioModel>();
        this.followingList = new ArrayList<ArtistModel>();
    }

    public double getCredit(){
        return this.credit;
    }

    public void setCredit(double credit){
        this.credit = credit;
    }

    public ArrayList<PlaylistModel> getPlaylists(){
        return this.playlists;
    }

    public void addPlaylist(PlaylistModel playlist){
        this.playlists.add(playlist);
    }

    public Integer getAudioPlayCount(AudioModel audio){
        return this.audioPlayCount.get(audio);
    }

    public Map<AudioModel , Integer> getAllAudiosPlayCount(){
        return this.audioPlayCount;
    }

    public void addAudioPlayCount(AudioModel audio){
        this.audioPlayCount.merge(audio, 1, Integer::sum);
    }
    public Date getSubscriptionExpiration(){
        return this.subscriptionExpiration;
    }

    public void setSubscriptionExpiration(Date subscriptionExpiration){
        this.subscriptionExpiration = subscriptionExpiration;
    }

    public ArrayList<Genre> getFavoriteGenres(){
        return this.favoriteGenres;
    }

    public void setFavoriteGenres(ArrayList<Genre> genres){
        this.favoriteGenres.addAll(genres);
    }
    public void addFavoriteGenre(Genre genre){
        this.favoriteGenres.add(genre);
    }

    public ArrayList<AudioModel> getLikedAudios(){
        return this.likedAudios;
    }

    public void addLikedAudio(AudioModel audio){
        this.likedAudios.add(audio);
    }
    public ArrayList<ArtistModel> getFollowingList(){
        return  this.followingList;
    }

    public void addToFollowingList(ArtistModel artist){
        this.followingList.add(artist);
    }
}
