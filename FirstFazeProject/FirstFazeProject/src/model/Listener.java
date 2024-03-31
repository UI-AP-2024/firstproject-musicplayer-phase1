package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends UserAccount{
    private Double accountCredit;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private Map<Audio,Integer> audioTimesPlayed = new HashMap<>();
    private Date shareExpireTime;
    private ArrayList<Genre> favoriteGenres = new ArrayList<>();

    public Listener(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate);
        this.accountCredit = 50D;
    }

    public Double getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(Double accountCredit) {
        this.accountCredit = accountCredit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public Map<Audio, Integer> getAudioTimesPlayed() {
        return audioTimesPlayed;
    }

    public void setAudioTimesPlayed(Map<Audio, Integer> audioTimesPlayed) {
        this.audioTimesPlayed = audioTimesPlayed;
    }

    public Date getShareExpireTime() {
        return shareExpireTime;
    }

    public void setShareExpireTime(Date shareExpireTime) {
        this.shareExpireTime = shareExpireTime;
    }

    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }

    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }
    public StringBuilder getSuggestions(Listener person){
        int counter = 1;
        StringBuilder result = new StringBuilder("Audios you might like : ");
        for (Audio audio : Database.getData().getAllAudios()){
            for (Genre genre1 : person.getFavoriteGenres()){
                if (genre1 == audio.getGenre()){
                    result.append(counter++).append("_").append(audio.getAudioName()).append("\r\n");
                }
            }
        }
        return result;
    }
}
