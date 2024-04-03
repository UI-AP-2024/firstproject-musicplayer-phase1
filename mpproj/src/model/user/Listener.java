package model.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import model.audio.Genre;
import model.audio.PlayList;

public class Listener extends User{
    private double accountCredit;
    private ArrayList<PlayList> listOfPlayLists;
    private Map<Long,Long> audioPlays;
    private ArrayList<Long> likedAudios;
    private Date premiumExpirationDate;
    private ArrayList<Genre> favoriteGenres;
    private long numberOfFollowing;
    public Listener(String password,String username, String name,String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit) {
        super(password,username, name, emailAddress, phoneNumber, birthDate);
        this.accountCredit = accountCredit;//not sure where to initilaze it
        listOfPlayLists = new ArrayList<>();
        audioPlays = new HashMap<>();
        favoriteGenres = new ArrayList<>();
        likedAudios = new ArrayList<>();
        this.numberOfFollowing=0;
    }
    public double getAccountCredit() {
        return accountCredit;
    }
    public ArrayList<PlayList> getListOfPlayLists() {
        return listOfPlayLists;
    }
    public Map<Long, Long> getAudioPlays() {
        return audioPlays;
    }
    public Date getPremiumExpirationDate() {
        return premiumExpirationDate;
    }
    public ArrayList<Genre> getFavoriteGenres() {
        return favoriteGenres;
    }
    public void setAccountCredit(double accountCredit) {
        this.accountCredit = accountCredit;
    }
    public void setPremiumExpirationDate(Date premiumExpirationDate) {
        this.premiumExpirationDate = premiumExpirationDate;
    }
    public void setListOfPlayLists(ArrayList<PlayList> listOfPlayLists) {//better not to use
        this.listOfPlayLists = listOfPlayLists;
    }
    public void setAudioPlays(Map<Long, Long> audioPlays) {//better not to use
        this.audioPlays = audioPlays;
    }
    public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {//better not to use
        this.favoriteGenres = favoriteGenres;
    }
    public void addToListOfPlayLists(PlayList PlayList) {
        this.listOfPlayLists.add(PlayList);
    }
    public void addToLikedAudios(Long id) {
        this.likedAudios.add(id);
    }
    public void addToAudioPlays(long audioId,long audioPlays) {
        this.audioPlays.put(audioId, audioPlays);
    }
    public void addToFavoriteGenres(Genre favoriteGenre) {
        this.favoriteGenres.add(favoriteGenre);
    }
    public void setNumberOfFollowing(long numberOfFollowing) {
        this.numberOfFollowing = numberOfFollowing;
    }
    public long getNumberOfFollowing() {
        return numberOfFollowing;
    }
    public ArrayList<Long> getLikedAudios() {
        return likedAudios;
    }
    public void setLikedAudios(ArrayList<Long> likedAudios) {
        this.likedAudios = likedAudios;
    }
    
    
    
    
}
