package model.AccountUser.Artist.TypeOfArtist;
import model.AccountUser.Artist.Artist;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Podcaster extends Artist {
    private List<String> podcasts;

    //*********************************************
    public Podcaster(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double income,String biography) {
        super(userName, password, fullName, email, phoneNumber,birthDate,income,biography);
        this.podcasts = new ArrayList<>();
    }
    //*********************************************
    public List<String> getPodcasts() {
        return podcasts;
    }
    public void setPodcasts(List<String> podcasts) {
        this.podcasts = podcasts;
    }
    //*********************************************
    @Override
    public String toString() {
        return "Podcaster{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", income=" + getIncome() +
                ", followers=" + getFollowers() +
                ", biography='" + getBiography() + '\'' +
                ", podcasts=" + podcasts +
                '}';
    }
}