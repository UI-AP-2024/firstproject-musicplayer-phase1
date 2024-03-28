package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Podcaster extends Artist{

    private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
    public Podcaster(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, String biography) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, biography);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
