package Model;

import java.util.*;

public class ListenerModel extends User{

    private double credit;
    private ArrayList<Playlists> playlists;
    private Map<AudioModel, Integer> playCount;
    public Date subscriptionExpirDate;
    private Genre[] favoriteGenres;
    private ArrayList<ArtistModel> fallowingArtist;
    public int playlistCount=0;
    public int musicCount=0;
    public int Count=0;
    public ListenerModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
        Database.getDatabase().getUsers().add(this);
        playCount = new HashMap<>();
        favoriteGenres = new Genre[4];
        fallowingArtist = new ArrayList<>();
        playlists = new ArrayList<>();
    }

    public double getCredit() {
        return credit;
    }

    public ArrayList<Playlists> getPlaylists() {
        return playlists;
    }

    public Map<AudioModel, Integer> getPlayCount() {
        return playCount;
    }

    public Date getSubscriptionExpirDate() {
        return subscriptionExpirDate;
    }

    public Genre[] getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public void setPlaylists(ArrayList<Playlists> playlists) {
        this.playlists = playlists;
    }

    public void setPlayCount(Map<AudioModel, Integer> playCount) {
        this.playCount = playCount;
    }

    public void setSubscriptionExpirDate(Date subscriptionExpirDate) {
        this.subscriptionExpirDate = subscriptionExpirDate;
    }

    public void setFavoriteGenres(Genre[] favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public int getPlaylistCount() {
        return playlistCount;
    }

    public ArrayList<ArtistModel> getFallowingArtist() {
        return fallowingArtist;
    }
}
