package model.UserAccount.Artist;

import model.UserAccount.UserAccount;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends UserAccount {
    private double income;
    private final ArrayList<UserAccount> followers;
    private String biography;
    public Artist(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday);
        this.followers = new ArrayList<>();
        this.income = income;
        this.biography = biography;
    }

    public double getIncome() {
        return income;
    }

    public ArrayList<UserAccount> getFollowers() {
        return followers;
    }

    public String getBiography() {
        return biography;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
