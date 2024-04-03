package model.UserAccount.Listener;

import model.Audio.AudioModel;

import model.GenreModel;
import model.PlaylistModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.UserAccountModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class ListenerModel extends UserAccountModel {
    private double accountCredit;
    private ArrayList<PlaylistModel> playlists;
    private Map<AudioModel, Integer> playsCount;
    private LocalDate subscriptionExpirationDate;
    private ArrayList<GenreModel> favoriteGenres;
    private ArrayList<ArtistModel> followings;
    public ListenerModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday) {
        super(username, password, name, email, phoneNumber, birthday);
        this.accountCredit = 50;
        this.playlists = new ArrayList<PlaylistModel>();
        this.playsCount = new HashMap<AudioModel, Integer>();
        this.favoriteGenres = new ArrayList<GenreModel>();
        this.followings = new ArrayList<ArtistModel>();
    }
    @Override
    public String toString() {
        return super.toString() +
                ", accountCredit = " + accountCredit +
                ", subscriptionExpirationDate = " + subscriptionExpirationDate;
    }

    public double getAccountCredit() {
        return accountCredit;
    }

    public ArrayList<PlaylistModel> getPlaylists() {
        return playlists;
    }

    public Map<AudioModel, Integer> getPlaysCount() {
        return playsCount;
    }

    public LocalDate getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public ArrayList<GenreModel> getFavoriteGenres() {
        return favoriteGenres;
    }

    public ArrayList<ArtistModel> getFollowings() {
        return followings;
    }

    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public void setPlaysCount(Map<AudioModel, Integer> playsCount) {
        this.playsCount = playsCount;
    }

    public void setFavoriteGenres(ArrayList<GenreModel> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void setPlaylists(ArrayList<PlaylistModel> playlists) {
        this.playlists = playlists;
    }

    public void setFollowings(ArrayList<ArtistModel> followings) {
        this.followings = followings;
    }

    public void setSubscriptionExpirationDate(LocalDate subscriptionExpirationDate) {
        this.subscriptionExpirationDate = subscriptionExpirationDate;
    }
}
