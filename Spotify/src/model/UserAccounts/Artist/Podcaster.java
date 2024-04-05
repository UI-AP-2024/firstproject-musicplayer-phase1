package model.UserAccounts.Artist;
import model.Audio.Podcast;
import model.UserAccounts.userAccount;

import java.util.ArrayList;
import java.util.Date;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcastsList;
    public Podcaster(String userId,String password, String fullName, String email, String phoneNumber, Date birthday,String biograghy) {
        super(userId,password, fullName, email, phoneNumber, birthday, biograghy);
        this.podcastsList = new ArrayList<Podcast>();
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\n");
        for(Podcast podcast:podcastsList){
            context.append(podcast.toString());
        }
        return context.toString();
    }
    public ArrayList<Podcast> getPodcastsList() {
        return podcastsList;
    }

    public void setPodcastsList(ArrayList<Podcast> podcastsList) {
        this.podcastsList = podcastsList;
    }
}
