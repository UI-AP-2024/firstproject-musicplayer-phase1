package Model;

import java.util.ArrayList;
import java.util.Date;

public class ArtistModel extends User{
    private double salary;
    private ArrayList<User> followers;
    private String biography;
    private int playCount=0;

    public ArtistModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography){
        super(username,password,fullName,email,phoneNumber,birthDate);
        this.biography = biography;
        followers = new ArrayList<>();
        Database.getDatabase().getUsers().add(this);
    }

    public double getSalary() {
        return salary;
    }

    public ArrayList<User> getFollowers() {
        return followers;
    }

    public String getBiography() {
        return biography;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }
}
