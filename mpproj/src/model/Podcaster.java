package model;

import java.util.ArrayList;
import java.util.Date;

public class Podcaster extends Artist {
    private ArrayList<Podcast> podcastList;

    @Override
    public String toString(){
        String result = "";
        result += super.toString();
        String pod = "";
        for (Podcast podcast : getPodcastList()){
            pod += "Podcast Id: "+podcast.getId()+"\tPodcast Name: "+podcast.getName()+"\n";
        }
        if ( pod.equals(""))
            result += "\nPodcasts: empty";
        else
            result += "\nPodcasts:\n"+pod;
        return result;
    }

    public Podcaster(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth/*, double income, ArrayList<UserAccount> listFollowers*/, String bio/*, ArrayList<Podcast> podcastList*/) {
        super(userName, password, name, email, phoneNumber, dateOfBirth/*, income, listFollowers*/, bio);
//        this.podcastList = podcastList;
        this.podcastList = new ArrayList<>();
    }

    public ArrayList<Podcast> getPodcastList() {
        return podcastList;
    }

    public void setPodcastList(ArrayList<Podcast> podcastList) {
        this.podcastList = podcastList;
    }
}
