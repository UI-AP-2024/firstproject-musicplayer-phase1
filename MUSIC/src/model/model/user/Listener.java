package model.model.user;

import model.enums.Genre;
import model.model.audio.Music;
import model.model.audio.PlayList;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

// شنونده
public class Listener extends User {
    protected float accountCredit;
    protected List<PlayList> playLists;
    protected Map<Music, Integer> numberOfPlayMusic;
    protected LocalDate subscriptionExpirationDate;
    protected List<Genre> favoriteGenre;

    protected Listener() {
    }

    public float getAccountCredit() {
        return accountCredit;
    }

    public void setAccountCredit(float accountCredit) {
        this.accountCredit = accountCredit;
    }

    public List<PlayList> getPlayLists() {
        return playLists;
    }

    public void setPlayLists(List<PlayList> playLists) {
        this.playLists = playLists;
    }

    public Map<Music, Integer> getNumberOfPlayMusic() {
        return numberOfPlayMusic;
    }

    public void setNumberOfPlayMusic(Map<Music, Integer> numberOfPlayMusic) {
        this.numberOfPlayMusic = numberOfPlayMusic;
    }

    public LocalDate getSubscriptionExpirationDate() {
        return subscriptionExpirationDate;
    }

    public void setSubscriptionExpirationDate(LocalDate subscriptionExpirationDate) {
        this.subscriptionExpirationDate = subscriptionExpirationDate;
    }

    public List<Genre> getFavoriteGenre() {
        return favoriteGenre;
    }

    public void setFavoriteGenre(List<Genre> favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    @Override
    public String toString() {
        return "Listener{" +
                "accountCredit=" + accountCredit +
                ", playLists=" + playLists +
                ", numberOfPlayMusic=" + numberOfPlayMusic +
                ", subscriptionExpirationDate=" + subscriptionExpirationDate +
                ", favoriteGenre=" + favoriteGenre +
                "} " + super.toString();
    }
}
