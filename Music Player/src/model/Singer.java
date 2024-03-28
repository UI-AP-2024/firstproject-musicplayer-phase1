package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Singer extends Artist{
    private ArrayList<Album> albums = new ArrayList<Album>();
    public Singer(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, String biography) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, biography);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
