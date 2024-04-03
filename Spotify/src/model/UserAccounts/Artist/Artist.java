package model.UserAccounts.Artist;

import model.UserAccounts.userAccount;

import java.util.ArrayList;
import java.util.Date;

public class Artist extends userAccount {

    private double income;
    private ArrayList<userAccount> followersList;
    private String biograghy;
    public Artist(String userId,String password, String fullName, String email, String phoneNumber, Date birthday,String biograghy) {
        super(userId,password, fullName, email, phoneNumber, birthday);
        this.income = 0;
        this.followersList = new ArrayList<userAccount>();
        this.biograghy = biograghy;
    }


    @Override
    public String toString(){
        StringBuilder context=new StringBuilder(super.toString());
        context.append("\nincome artist : ");
        context.append(income);
        context.append("\nartist biography : ");
        context.append(biograghy);
        context.append("\n");
        for(userAccount user:followersList){
            context.append(user.toString());
        }
        return context.toString();
    }
    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public ArrayList<userAccount> getFollowersList() {
        return followersList;
    }

    public void setFollowersList(ArrayList<userAccount> followersList) {
        this.followersList = followersList;
    }

    public String getBiograghy() {
        return biograghy;
    }

    public void setBiograghy(String biograghy) {
        this.biograghy = biograghy;
    }
}
