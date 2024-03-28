package model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends UserAccount
{
    private int accountCredit = 50;
    private Date expirationDate = null;
    private Genre[] genres = new Genre[4];
    private ArrayList<Playlist> playlists = new ArrayList<Playlist>();
    private Map<String,Integer> map = new HashMap<>();
    public Listener(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord,Genre[] genres) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord);
        this.genres = genres;
    }
    public int getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(int accountCredit) {
        this.accountCredit = accountCredit;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Genre[] getGenres() {
        return genres;
    }

    public void setGenres(Genre[] genres) {
        this.genres = genres;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }
}
