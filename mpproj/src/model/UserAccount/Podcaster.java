package model.UserAccount;

import model.Audio.Podcast;
import model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Date;

public class Podcaster extends User{
    ArrayList<Podcast> podcasts;

    public Podcaster(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, SubscriptionPlan subscription, ArrayList<Podcast> podcasts) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth, subscription);
        this.podcasts = podcasts;
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
