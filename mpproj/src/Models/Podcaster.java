package Model;

import java.util.ArrayList;

public class Podcaster extends Artist {
    private ArrayList<Podcast> podcastsList = new ArrayList<>();

    public Podcaster(String userName, String password, String firstAndLastName, String email, String phoneNumber, int year, int month, int day, String biography) {
        super(userName, password, firstAndLastName, email, phoneNumber, year, month, day, biography);
    }

    public ArrayList<Podcast> getPodcastsList() {
        return podcastsList;
    }


    public void setPodcastsList(ArrayList<Podcast> podcastsList) {
        this.podcastsList = podcastsList;
    }
    @Override
    public String toString(){
        return super.toString();
    }
}

