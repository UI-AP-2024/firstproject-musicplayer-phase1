package model;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends UserAccount{
    private Double income;
    private ArrayList<UserAccount> followers = new ArrayList<>();
    private String biography;

    public Artist(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate);
        this.biography = biography;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public ArrayList<UserAccount> getFollowers() {
        return followers;
    }

    public void setFollowers(ArrayList<UserAccount> followers) {
        this.followers = followers;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
