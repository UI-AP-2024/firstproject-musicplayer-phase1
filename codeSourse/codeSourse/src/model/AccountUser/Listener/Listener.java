package model.AccountUser.Listener;

import model.AccountUser.AccountUser;
import model.AccountUser.Listener.TypeOfListener.SubscriptionType;
import model.Audio.Audio;
import model.Genre;
import model.Playlist;

import java.util.*;


public class Listener extends AccountUser {
    private double accountBalance;
    private static List<Playlist> playlists;
    private static Map<Audio, Integer> playCount;
    private Date subEndDate;
    private List<Genre> favoriteGenres;
     private SubscriptionType subscriptionType;


    //*********************************************
    public Listener(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double accountBalance,Date subEndDate) {
        super(userName, password, fullName, email, phoneNumber, birthDate);
        this.accountBalance = 0.0;
        this.playlists = new ArrayList<>();
        this.playCount = new HashMap<>();
        this.favoriteGenres = new ArrayList<>();
        this.subEndDate = null;
        this.subscriptionType = SubscriptionType.FREE;

    }

    //*********************************************
    public double getAccountBalance() {
        return accountBalance;
    }

    public static List<Playlist> getPlaylists() {
        return playlists;
    }

    public static Map<Audio, Integer> getPlayCountByAudio() {
        return playCount;
    }

    public Date getSubscriptionEndDate() {
        return subEndDate;
    }

    public List<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }
    public void upgradeSubscription() {
        this.subscriptionType = SubscriptionType.PREMIUM;
    }

    //*********************************************

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }
    public void setPlaylists(List<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setPlayCountByAudio(Map<Audio, Integer> playCount) {
        this.playCount = playCount;
    }

    public void setSubscriptionEndDate(Date subscriptionEndDate) {
        this.subEndDate = subscriptionEndDate;
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
                subEndDate +
                ", favoriteGenres=" + favoriteGenres +
                '}';
    }
}

