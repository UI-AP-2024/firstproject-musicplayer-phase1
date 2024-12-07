package Model.Accounts.Artists;

import Model.Accounts.AccountsModel;
import Model.Audios.AudioModel;

import java.util.ArrayList;
import java.util.Date;

public class ArtistModel extends AccountsModel {
    private double income;
    private ArrayList<AccountsModel> followers;
    private int followersCount;
    private String bio;

    public ArtistModel(String username , String password , String fullName , String email , String phoneNumber , int[] birthDate , String bio){
        super(username , password , fullName , email , phoneNumber , birthDate);

        this.followers = new ArrayList<AccountsModel>();
        this.followersCount = 0;
        this.bio = bio;
    }

    public double getIncome(){
        return this.income;
    }

    public void setIncome(double income){
        this.income = income;
    }

    public ArrayList<AccountsModel> getFollowers(){
        return this.followers;
    }

    public void addFollower(AccountsModel follower){
        this.followers.add(follower);
    }

    public int getFollowersCount(){
        return this.followersCount;
    }

    public void setFollowersCount(int followersCount){
        this.followersCount = followersCount;
    }
    public String getBio(){
        return this.bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

}
