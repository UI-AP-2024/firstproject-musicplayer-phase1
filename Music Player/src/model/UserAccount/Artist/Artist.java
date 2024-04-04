package model.UserAccount.Artist;


import model.Album;
import model.UserAccount.UserAccount;

import java.time.LocalDate;
import java.util.ArrayList;

public class Artist extends UserAccount
{
    double income;
    String biography;
    ArrayList<UserAccount> followers = new ArrayList<UserAccount>();
    public Artist(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth,String biography)
    {
        super(userName,passWord,name,email,phoneNumber,dateOfBirth);
        this.biography = biography;
    }
    Artist artist;
    public double getIncome() {
        return income;
    }
    public void setIncome(double income) {
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
    @Override
    public String toString()
    {
        return "UserName: " + getUserName()+"\t"+"PassWord: "+getPassWord()+"\t"+"Name: "+getName()+"\t"+"Email: "+getEmail()+"\t"+"PhoneNumber: "+getPhoneNumber()+"\t"+"Birth Date: "+getDateOfBirth()+"\t"+"Biography: "+getBiography();
    }
}
