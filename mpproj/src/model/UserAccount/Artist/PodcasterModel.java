package model.UserAccount.Artist;

import model.Audio.PodcastModel;

import javax.sql.PooledConnection;
import java.util.ArrayList;
import java.util.Date;
    

public class PodcasterModel extends ArtistModel{
    private ArrayList<PodcastModel> podcasts;
    public PodcasterModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.podcasts = new ArrayList<PodcastModel>();
    }

    @Override
    public String toString() {
        return super.toString();
    }

    public ArrayList<PodcastModel> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<PodcastModel> podcasts) {
        this.podcasts = podcasts;
    }
}
