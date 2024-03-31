package model.UserAccount.Artist;

import model.UserAccount.UserAccountModel;

import java.util.ArrayList;
import java.util.Date;

public abstract class ArtistModel extends UserAccountModel {
    private double income;
    private ArrayList<UserAccountModel> followers;
    private String biography;
    public ArtistModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday);
        this.followers = new ArrayList<>();
        this.income = income;
        this.biography = biography;
    }

    public ArtistModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday) {
        super(username, password, name, email, phoneNumber, birthday);
    }

    public double getIncome() {
        return income;
    }

    public ArrayList<UserAccountModel> getFollowers() {
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

    public void setFollowers(ArrayList<UserAccountModel> followers) {
        this.followers = followers;
    }
}
