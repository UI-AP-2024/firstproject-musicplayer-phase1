package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

public class Singer extends Artist{
    private ArrayList<Album> albums = new ArrayList<>();

    public Singer(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate, biography);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
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
        return ((double)counter*(0.4));
    }
}