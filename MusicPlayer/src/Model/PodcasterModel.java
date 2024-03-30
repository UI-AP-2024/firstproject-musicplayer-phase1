package Model;

import java.util.ArrayList;
import java.util.Date;

public class PodcasterModel extends ArtistModel{
    private ArrayList<Podcast> podcasts;
    public PodcasterModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate,double salary,String biography){
        super(username,password,fullName,email,phoneNumber,birthDate,salary,biography);
        podcasts = new ArrayList<>();
        Database.getDatabase().getPodcaster().add(this);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}
