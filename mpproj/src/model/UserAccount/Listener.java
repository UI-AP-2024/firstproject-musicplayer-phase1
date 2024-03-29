package model.UserAccount;
import model.Audio.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import model.*;

public class Listener extends User{

    private ArrayList<Playlist> playlists;
    private Map<Audio, Integer> listeningHistory;
    private int credit;
    private Genre favoriteGenre;
    private  int playlistcounter;
//ارΟخ اتمام اشتراک )از نوع کلاس  - Dateدر صورتی که حساب شنونده عادی است،
//اΞن فیلد  nullخواهد بود.
    public Listener(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth,int credit) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth);
        this.credit=credit;
    }

    public int getPlaylistcounter() {
        return playlistcounter;
    }

    public void setPlaylistcounter(int playlistcounter,int i) {
        this.playlistcounter = playlistcounter+i;
    }

    public void setPlaylists(ArrayList<Playlist> playlists) {
        this.playlists = playlists;
    }

    public void setListeningHistory(Map<Audio, Integer> listeningHistory) {
        this.listeningHistory = listeningHistory;
    }



    public void setFavoriteGenre(Genre favoriteGenre) {
        this.favoriteGenre = favoriteGenre;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<Audio, Integer> getListeningHistory() {
        return listeningHistory;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Genre getFavoriteGenre() {
        return favoriteGenre;
    }
}
