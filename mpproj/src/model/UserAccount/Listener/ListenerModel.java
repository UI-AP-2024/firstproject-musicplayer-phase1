package model.UserAccount.Listener;

import model.Audio.AudioModel;

import model.GenreModel;
import model.PlaylistModel;
import model.UserAccount.UserAccountModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class ListenerModel extends UserAccountModel {
    private int accountCredit;
    private ArrayList<PlaylistModel> playlists;
    private Map<AudioModel, Integer> playsCount;
    private final Date subscriptionExpirationDate;
    private ArrayList<GenreModel> favoriteGenres;
    public ListenerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        super(username, password, name, email, phoneNumber, birthday);
        this.accountCredit = 50;
        this.playlists = new ArrayList<PlaylistModel>();
        this.playsCount = new HashMap<AudioModel, Integer>();
        this.subscriptionExpirationDate = new Date();
        this.favoriteGenres = new ArrayList<GenreModel>();
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public ArrayList<PlaylistModel> getPlaylists() {
        return playlists;
    }

    public Map<AudioModel, Integer> getPlaysCount() {
        return playsCount;
    }

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public ArrayList<GenreModel> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setAccountCredit(int accountCredit) {
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
}
