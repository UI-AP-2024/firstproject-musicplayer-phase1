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
    private Date premiumExpirationDate;
    private ArrayList<Genre> favoriteGenres;
    public Listener(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, double accountCredit) {
        super(password, firstName, lastName, emailAddress, phoneNumber, birthDate);
        this.accountCredit = accountCredit;//not sure where to initilaze it
        listOfPlayLists = new ArrayList<>();
        audioPlays = new HashMap<>();
        favoriteGenres = new ArrayList<>();
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
    public void setListOfPlayLists(PlayList PlayList) {
        this.listOfPlayLists.add(PlayList);
    }
    public void setAudioPlays(long audioId,long audioPlays) {
        this.audioPlays.put(audioId, audioPlays);
    }
    public void setFavoriteGenres(Genre favoriteGenre) {
        this.favoriteGenres.add(favoriteGenre);
    }
    // public void setListOfPlayLists(ArrayList<PlayList> listOfPlayLists) {
    //     this.listOfPlayLists = listOfPlayLists;
    // }
    // public void setAudioPlays(Map<Long, Long> audioPlays) {
    //     this.audioPlays = audioPlays;
    // }
    // public void setFavoriteGenres(ArrayList<Genre> favoriteGenres) {
    //     this.favoriteGenres = favoriteGenres;
    // }
    
    
    
}
