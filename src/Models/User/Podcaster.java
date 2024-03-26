package Models.User;
import Models.Audio.Podcast;
import java.util.*;

public class Podcaster extends Artist {
    private ArrayList<Podcast> podcasts;

    public Podcaster(String username, String password, String name, String email, String phoneNumber,
                   Date dateOfBirth, double income, String biography) {
        super(username, password, name, email, phoneNumber, dateOfBirth, income, biography);
        this.podcasts = new ArrayList<>();
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }
}