package Models.User;

import Models.Audio.Audio;
import Models.Genre;
import Models.Playlist;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends User {
    private double credit;
    private ArrayList<Playlist> playlists;
    private Map<Audio, Integer> audiosPlayed;
    private Date expirementDate;
    private ArrayList<Genre> favoriteGenres;

    public Listener(String username, String password, String name, String email, String phoneNumber, Date dateOfBirth,
                    double credit, Date expirementDate, ArrayList<Genre> favoriteGenres) {
        super(username, password, name, email, phoneNumber, dateOfBirth);
        this.credit = credit;
        if(this instanceof NormalListener) this.expirementDate = null;
        else this.expirementDate = expirementDate;
        this.playlists = new ArrayList<>();
        this.audiosPlayed = new HashMap<>();
        this.favoriteGenres = favoriteGenres;
    }
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Map<Audio, Integer> getAudiosPlayed() {
        return audiosPlayed;
    }

    public void setAudiosPlayed(Map<Audio, Integer> audiosPlayed) {
        this.audiosPlayed = audiosPlayed;
    }

    public Date getExpirementDate() {
        return expirementDate;
    }

    public void setExpirementDate(Date expirementDate) {
        this.expirementDate = expirementDate;
    }

    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

}
