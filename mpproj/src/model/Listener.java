package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Listener extends UserAccount{
    private int accountCredit;
    private ArrayList<Playlist> playlists;
    private Map<Audio,Integer> numberOfPlays;
    private Date expirationDate;
    private ArrayList<Gener> favoriteGener;

    public Listener(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberOfPlays, Date expirationDate, ArrayList<Gener> favoriteGener) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
        this.accountCredit = accountCredit;
        this.playlists = playlists;
        this.numberOfPlays = numberOfPlays;
        this.expirationDate = expirationDate;
        this.favoriteGener = favoriteGener;
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<Audio, Integer> getNumberOfPlays() {
        return numberOfPlays;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public ArrayList<Gener> getFavoriteGener() {
        return favoriteGener;
    }

    public void setAccountCredit(int accountCredit) {
        this.accountCredit = accountCredit;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setNumberOfPlays(Map<Audio, Integer> numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setFavoriteGener(ArrayList<Gener> favoriteGener) {
        this.favoriteGener = favoriteGener;
    }
}
