package model.AccountUser.Artist;

import model.AccountUser.AccountUser;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Artist extends AccountUser {
    private double income;
    private static List<AccountUser> followers;
    private String biography;
    //*********************************************
    public Artist(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double income,String biography) {
        super(userName, password, fullName, email, phoneNumber,birthDate);
        this.income = income;
        this.biography = biography;
        this.followers = new ArrayList<>();
    }
    //*********************************************
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public static List<AccountUser> getFollowers() {
        return followers;
    }

    public void setFollowers(List<AccountUser> followers) {
        this.followers = followers;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    //*********************************************
    @Override
    public String toString() {
        return "Artist{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", income=" + income +
                ", followers=" + followers +
                ", biography='" + biography + '\'' +
                '}';
    }


}