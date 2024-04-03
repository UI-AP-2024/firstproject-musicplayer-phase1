package model.UserAccount.Artist;

import model.Audio.PodcastModel;

import javax.sql.PooledConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
    

public class PodcasterModel extends ArtistModel{
    private ArrayList<PodcastModel> podcasts;
    public PodcasterModel(String username, String password, String name, String email, String phoneNumber, LocalDate birthday, String biography) {
        super(username, password, name, email, phoneNumber, birthday, biography);
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
