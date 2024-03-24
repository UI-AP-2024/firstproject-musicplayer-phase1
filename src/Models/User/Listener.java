package Models.User;

import Models.Audio.Audio;
import Models.Genre;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Listener extends User {
    private double credit;
    private List<String> playlists;
    private Map<Audio, Integer> audiosPlayed;
    private Date expirementDate;
    private List<Genre> favoriteGenres;

    public Listener(String username, String password, String name, String email, String phoneNumber, Date dateOfBirth,
                    double credit, Date expirementDate) {
        super(username, password, name, email, phoneNumber, dateOfBirth);
        this.credit = credit;
        if(this instanceof NormalListener) this.expirementDate = null;
        else this.expirementDate = expirementDate;
        this.playlists = new ArrayList<>();
        this.audiosPlayed = new HashMap<>();
        this.favoriteGenres = new ArrayList<>();
    }
    public double getCredit() {
        return credit;
    }

    public void setCredit(double credit) {
        this.credit = credit;
    }

    public List<String> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(List<String> playlists) {
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

    public List<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(List<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }
}
