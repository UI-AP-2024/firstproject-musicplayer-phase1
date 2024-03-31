package model.AcountUser.Listener;

import model.AcountUser.AccountUser;
import model.Genre;
import model.Playlist;

import java.util.*;


public class Listener extends AccountUser {
    private double accountBalance;
    private List<Playlist> playlists;
    private Map<String, Integer> playCount;
    private Date subscriptionEndDate;

    private List<Genre> favoriteGenres;

    public Listener(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(userName, password, fullName, email, phoneNumber, birthDate);
        this.accountBalance = 0.0;
        this.playlists = new ArrayList<>();
        this.playCount = new HashMap<>();
        this.favoriteGenres = new ArrayList<>();
        this.subscriptionEndDate = null;
    }

    //*********************************************
    public double getAccountBalance() {
        return accountBalance;
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<String, Integer> getPlayCountByAudio() {
        return playCount;
    }

    public Date getSubscriptionEndDate() {
        return subscriptionEndDate;
    }

    public List<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    //*********************************************

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setPlayCountByAudio(Map<String, Integer> playCount) {
        this.playCount = playCount;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subscriptionEndDate = subscriptionEndDate;
    }

    public void setFavoriteGenres(List<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    //*********************************************
    @Override
    public String toString() {
        return "Listener{" +
                "username='" +
                getUserName() + '\'' +
                ", password='" +
                getPassword() + '\'' +
                ", fullName='" +
                getFullName() + '\'' +
                ", email='" +
                getEmail() + '\'' +
                ", phoneNumber='" +
                getPhoneNumber() + '\'' +
                ", birthDate=" +
                getBirthDate() + ", accountBalance=" +
                accountBalance +
                ", playlists=" + playlists +
                ", playCount=" +
                playCount + ", subscriptionEndDate=" +
                subscriptionEndDate +
                ", favoriteGenres=" + favoriteGenres +
                '}';
    }
}

