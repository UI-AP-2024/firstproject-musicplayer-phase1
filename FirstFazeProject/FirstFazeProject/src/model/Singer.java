package model;

import java.util.ArrayList;
import java.util.Date;

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
}
