package model.UserAccount.Artist;

import model.Audio.PodcastModel;

import java.util.ArrayList;
import java.util.Date;
    

public class PodcasterModel extends ArtistModel{
    private ArrayList<PodcastModel> podcasts;
    public PodcasterModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.podcasts = new ArrayList<>();
    }

    public ArrayList<PodcastModel> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<PodcastModel> podcasts) {
        this.podcasts = podcasts;
    }
}
