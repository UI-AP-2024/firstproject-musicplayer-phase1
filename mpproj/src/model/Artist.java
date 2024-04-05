package model;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends UserAccount{
    private double income;
    private ArrayList<UserAccount> listFollowers;
    private String bio;

    @Override
    public String toString(){
        String result = "";
        result += super.toString()+"\tBio: "+getBio()+"\tIncome: "+getIncome();
        String foll = "";
        for ( UserAccount userAccount1 : getListFollowers()){
            foll += "Username: "+getUserName()+"\tname: "+getName()+"\n";
        }
        if (foll.equals(""))
            result += "\nFollowers: empty";
        else
            result += "\nFollowers:\n"+foll;
        return result;
    }

    public Artist(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth/*, double income, ArrayList<UserAccount> listFollowers*/, String bio) {
        super(userName, password, name, email, phoneNumber, dateOfBirth);
        this.income = 0;
//        this.listFollowers = listFollowers;
        this.listFollowers = new ArrayList<>();
        this.bio = bio;
    }

    public double getIncome() {
        return income;
    }

    public ArrayList<UserAccount> getListFollowers() {
        return listFollowers;
    }

    public String getBio() {
        return bio;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public void setListFollowers(ArrayList<UserAccount> listFollowers) {
        this.listFollowers = listFollowers;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }
}
