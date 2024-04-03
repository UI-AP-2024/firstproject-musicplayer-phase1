package Model;

import java.util.ArrayList;

public abstract class Artist extends UserAccount {
    private double salary;
    private ArrayList<UserAccount> followersList = new ArrayList<>();
    private String biography;

    Artist(String userName, String password, String firstAndLastName, String email, String phoneNumber, int year, int month, int day
            ,String biography) {
        super(userName, password, firstAndLastName, email, phoneNumber, year, month, day);
        this.biography = biography;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public ArrayList<UserAccount> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<UserAccount> followersList) {
        this.followersList = followersList;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }
    @Override
    public String toString(){
        return super.toString()+"\nBio: "+getBiography()+"\n";
    }
}
