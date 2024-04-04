package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcasts = new ArrayList<>();

    public Podcaster(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate, biography);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public double audiosTimesPlayed() {
        int counter = 0;
        for (Audio audio : Database.getData().getAllAudios()){
            if (Objects.equals(audio.getArtistName(), this.getFullName())){
                counter += audio.getTimesPlayed();
            }
        }
        this.setIncome(this.getIncome()+(double)counter*(0.5));
        return ((double)counter*(0.5));
    }
}
