package model.UserAccount.Artist;

import model.Audio.AudioModel;
import model.UserAccount.UserAccountModel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public abstract class ArtistModel extends UserAccountModel {
    private double income;
    private ArrayList<UserAccountModel> followers;
    private String biography;
    private Map<AudioModel, Integer> audiosPlayCount;
    private int playsCount;
    public ArtistModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday, String biography) {
        super(username, password, name, email, phoneNumber, birthday);
        this.followers = new ArrayList<UserAccountModel>();
        this.income = 0;
        this.biography = biography;
        this.audiosPlayCount = new HashMap<AudioModel, Integer>();
        this.playsCount = 0;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Biography = " + biography;
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

    public Map<AudioModel, Integer> getAudiosPlayCount() {
        return audiosPlayCount;
    }

    public int getPlaysCount() {
        return playsCount;
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

    public void setAudiosPlayCount(Map<AudioModel, Integer> audiosPlayCount) {
        this.audiosPlayCount = audiosPlayCount;
    }
    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }
}
