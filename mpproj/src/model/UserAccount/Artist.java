package model.UserAccount;

import model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends User{
    private double income;
    private ArrayList<User> followers;
    private String biography;

    public Artist(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, double income, ArrayList<User> followers, String biography) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        this.income = income;
        this.followers = followers;
        this.biography = biography;
    }

    public double getIncome() {
        return income;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public String getBiography() {
        return biography;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setFollowers(ArrayList<User> followers) {
        this.followers = followers;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
}
