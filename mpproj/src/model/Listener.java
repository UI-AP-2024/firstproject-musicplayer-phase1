package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Listener extends UserAccount{
    private int accountCredit;
    private ArrayList<Playlist> playlists;
    private Map<Audio,Integer> numberPlays;
    private Date expirationDate;
    private ArrayList<Gener> favoriteGener;
    private ArrayList<UserAccount> followings;

    @Override
    public String toString(){
        String result = "";
        result += super.toString()+"\tAccount credit: "+getAccountCredit();
        String play = "";
        for (Playlist playlist : getPlaylists()){
            play += "Playlist Id: "+playlist.getId()+"\tPlaylist Name: "+playlist.getNameOfPlaylist()+"\n";
        }
        if ( play.equals(""))
            result += "\nPlaylists: empty";
        else
            result += "\nPlaylists:\n"+play;
        String ge = "";
        for ( Gener gener : getFavoriteGener()){
            ge += String.valueOf(gener)+"\t";
        }
        result += "\nFavorite Gener:\n"+ge;
        String foll = "";
        for ( UserAccount userAccount1 : getFollowings()){
            foll += "Username: "+userAccount1.getUserName()+"\tName: "+userAccount1.getName()+"\n";
        }
        if ( foll.equals(""))
            result += "\nFollowings: empty";
        else
            result += "\nFollowings:\n"+foll;
        return result;
    }


    public Listener(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberPlays, Date expirationDate, ArrayList<Gener> favoriteGener) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
        this.accountCredit = accountCredit;
        this.playlists = playlists;
        this.numberPlays = numberPlays;
        this.expirationDate = expirationDate;
        this.favoriteGener = favoriteGener;
    }

    public int getAccountCredit() {
        return accountCredit;
    }

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Map<Audio, Integer> getNumberPlays() {
        return numberPlays;
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

    public void setNumberPlays(Map<Audio, Integer> numberPlays) {
        this.numberPlays = numberPlays;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setFavoriteGener(ArrayList<Gener> favoriteGener) {
        this.favoriteGener = favoriteGener;
    }

    public void setFollowings(ArrayList<UserAccount> followings) {
        this.followings = followings;
    }

    public ArrayList<UserAccount> getFollowings() {
        return followings;
    }
}
