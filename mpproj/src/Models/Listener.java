package Model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public  class Listener extends UserAccount {
    private long accountCredit;
    private ArrayList <Playlist> playlists ;
    private Map<Audio,Integer> playMap;
    private LocalDate finishTime;
    private    ArrayList<Genre> favoriteGenres ;
    private    ArrayList<Artist> followingsArtist ;

    private    ArrayList<Audio> likedAudios;
    Listener(String userName, String password, String firstAndLastName,
             String email, String phoneNumber, int year, int month, int day,
             long accountCredit, ArrayList<Genre> favoriteGenres, LocalDate finishTime) {
        super(userName, password, firstAndLastName, email, phoneNumber, year, month, day);
        this.accountCredit = accountCredit;
        this.finishTime = finishTime;
        this.playlists = new ArrayList<>();
        this.favoriteGenres = new ArrayList<>();
        this.followingsArtist=new ArrayList<>();
        this.playMap=new HashMap<>();
        likedAudios=new ArrayList<>();
    }

    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public long getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(long accountCredit) {
        this.accountCredit = accountCredit;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<Audio,Integer> getPlayConter() {
        return playMap;
    }

    public void setPlayConter(Map<Audio,Integer> playConter) {
        this.playMap = playConter;
    }
    @Override
    public String toString(){
        return super.toString()+"\nAcccout Credit:"+getAccountCredit();
    }

    public LocalDate getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDate finishTime) {
        this.finishTime = finishTime;
    }

    public ArrayList<Artist> getFollowingsArtist() {
        return followingsArtist;
    }

    public void setFollowingsArtist(ArrayList<Artist> followingsArtist) {
        this.followingsArtist = followingsArtist;
    }

    public void setPlayMap(Map<Audio, Integer> playMap) {
        this.playMap = playMap;
    }

    public Map<Audio, Integer> getPlayMap() {
        return playMap;
    }

    public ArrayList<Audio> getLikedAudios() {
        return likedAudios;
    }

    public void setLikedAudios(ArrayList<Audio> likedAudios) {
        this.likedAudios = likedAudios;
    }
}

