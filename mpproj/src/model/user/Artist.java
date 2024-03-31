package model.user;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends User {
    private double income;
    private ArrayList<User> followers;
    private String biographi;
    public Artist(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password, firstName, lastName, emailAddress, phoneNumber, birthDate);
        this.biographi = biographi;
        followers = new ArrayList<>();
        this.income = 0;
    }
    public void setIncome(double income) {
        this.income = income;
    }
    // public void setFollowers(ArrayList<User> followers) {//change to apend
    //     this.followers = followers;
    // }
    public void setFollowers(User follower) {
        this.followers.add(follower);
    }
    public void setBiographi(String biographi) {
        this.biographi = biographi;
    }
    public double getIncome() {
        return income;
    }
    public ArrayList<User> getFollowers() {
        return followers;
    }
    public String getBiographi() {
        return biographi;
    }

    
    
}
