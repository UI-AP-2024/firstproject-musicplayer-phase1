package model.UserAccount;
import model.Audio.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import model.*;

public class Listener extends User{
    private SubscriptionPlan subscriptionPlan;
    private ArrayList<Playlist> playlists;
    private Map<Audio, Integer> listeningHistory;
    private Date finishsubDate;
    private Genre favoriteGenre;
//ارΟخ اتمام اشتراک )از نوع کلاس  - Dateدر صورتی که حساب شنونده عادی است،
//اΞن فیلد  nullخواهد بود.
    public Listener(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, SubscriptionPlan subscriptionPlan) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        this.subscriptionPlan = subscriptionPlan;
    }

    public void setSubscriptionPlan(SubscriptionPlan subscriptionPlan) {
        this.subscriptionPlan = subscriptionPlan;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setListeningHistory(Map<Audio, Integer> listeningHistory) {
        this.listeningHistory = listeningHistory;
    }

    public void setFinishsubDate(Date finishsubDate) {
        this.finishsubDate = finishsubDate;
    }

    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public SubscriptionPlan getSubscriptionPlan() {
        return subscriptionPlan;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<Audio, Integer> getListeningHistory() {
        return listeningHistory;
    }

    public Date getFinishsubDate() {
        return finishsubDate;
    }

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }
}
