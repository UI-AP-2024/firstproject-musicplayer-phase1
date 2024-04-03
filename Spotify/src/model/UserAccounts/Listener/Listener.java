package model.UserAccounts.Listener;
import java.util.Calendar;

import model.Audio.Audio;
import model.Genre.Genre;
import model.Playlist.Playlist;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.userAccount;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends userAccount {
    private double credit;
    private ArrayList<Playlist> playlists;
    private Map<Audio,Integer> playFiles;
    private ArrayList<Artist> followings;
    private Date dateOfEndSubscription=null;
    private ArrayList<Genre> favouriteGenres;



    public Listener(String userId, String password, String fullName, String email, String phoneNumber, Date birthday,double credit,ArrayList<Playlist> playlists,ArrayList<Genre> favouriteGenres) {
        super(userId, password, fullName, email, phoneNumber, birthday);
        this.credit = credit;
        this.playlists = playlists;
        this.playFiles = new HashMap<>();
        this.favouriteGenres=favouriteGenres;
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\nlistener credit : ");
        context.append(credit);
        context.append("\n");
        for(Playlist playlist:playlists){
            context.append(playlist.toString());
        }
        context.append("\n");
        for(Map.Entry<Audio,Integer> playFile:playFiles.entrySet()){
            context.append(playFile.getKey());
            context.append(" : ");
            context.append(playFile.getValue());
        }
        context.append("\n");
        return context.toString();
    }

    public ArrayList<Artist> getFollowings() {
        return followings;
    }

    public void setFollowings(ArrayList<Artist> followings) {
        this.followings = followings;
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

    public Map<Audio, Integer> getPlayFiles() {
        return playFiles;
    }

    public void setPlayFiles(Map<Audio, Integer> playFiles) {
        this.playFiles = playFiles;
    }

    public Date getDateOfEndSubscription() {
        return dateOfEndSubscription;
    }

    public void setDateOfEndSubscription(Date dateOfEndSubscription) {
        this.dateOfEndSubscription = dateOfEndSubscription;
    }

    public ArrayList<Genre> getFavouriteGenres() {
        return favouriteGenres;
    }

    public void setFavouriteGenres(ArrayList<Genre> favouriteGenres) {
        this.favouriteGenres = favouriteGenres;
    }
}
