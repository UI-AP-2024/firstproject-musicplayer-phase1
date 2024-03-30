package model.UserAccount.Artist;

import model.Audio.Podcast;

import java.util.ArrayList;
import java.util.Date;
    

public class Podcaster extends Artist{
    private final ArrayList<Podcast> podcasts;
    public Podcaster(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.podcasts = new ArrayList<>();
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }
}
