package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.audio.Podcast;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcastList;

    

    public Podcaster(String password,String username, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, biographi);
        podcastList = new ArrayList<>();
    }

    public ArrayList<Podcast> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(ArrayList<Podcast> podcastList) {
        this.podcastList = podcastList;
    }
    public void addToPodcastList(Podcast podcast) {
        this.podcastList.add(podcast);
    }
    
    
}
