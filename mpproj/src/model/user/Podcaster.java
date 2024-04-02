package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.audio.Album;
import model.audio.Music;
import model.audio.Podcast;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcastList;

    

    public Podcaster(String password,String username, String name, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password,username, name, emailAddress, phoneNumber, birthDate, biographi);
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
