package model.AcountUser.Artist;
import model.AcountUser.AccountUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Podcaster extends Artist {
    private List<String> podcasts;

    //*********************************************
    public Podcaster(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate, double income, String biography, List<AccountUser> followers,List<String> podcasts) {
        super(userName, password, fullName, email, phoneNumber,birthDate,income,biography,followers);
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