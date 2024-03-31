package model.UserAccount.Listener;

import model.Audio.AudioModel;
import model.Genre;
import model.Playlist;
import model.UserAccount.UserAccountModel;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class ListenerModel extends UserAccountModel {
    private int accountCredit;
    private ArrayList<Playlist> playlists;
    private Map<AudioModel, Integer> playsCount;
    private final Date subscriptionExpirationDate;
    private ArrayList<Genre> favoriteGenres;
    public ListenerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, int accountCredit) {
        super(username, password, name, email, phoneNumber, birthday);
        this.accountCredit = accountCredit;
        this.playlists = new ArrayList<>();
        this.playsCount = new HashMap<>();
        this.subscriptionExpirationDate = new Date();
        this.favoriteGenres = new ArrayList<>();
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<AudioModel, Integer> getPlaysCount() {
        return playsCount;
    }

    public Date getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setAccountCredit(int accountCredit) {
        this.accountCredit = accountCredit;
    }

    public void setPlaysCount(Map<AudioModel, Integer> playsCount) {
        this.playsCount = playsCount;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }
}
