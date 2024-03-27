package model.UserAccount;

import model.Audio.Music;
import model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Date;

public class Singer extends User{
    ArrayList<Music> musics;

    public Singer(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, ArrayList<Music> musics) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        this.musics = musics;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }

    public void setMusics(ArrayList<Music> musics) {
        this.musics = musics;
    }
}
