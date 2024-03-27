package model;

import java.time.LocalDate;
import java.util.ArrayList;

public abstract class Artist extends UserAccount
{
    double income;
    String biography;
    ArrayList<UserAccount> followes = new ArrayList<UserAccount>();
    public Artist(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord , String biography)
    {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord);
        this.biography = biography;
    }
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    public ArrayList<UserAccount> getFollowes() {
        return followes;
    }
    public void setFollowes(ArrayList<UserAccount> followes) {
        this.followes = followes;
    }
    public String getBiography() {
        return biography;
    }
    public void setBiography(String biography) {
        this.biography = biography;
    }
}
