package model.UserAccount.Artist;

import model.Album;

import java.util.ArrayList;
import java.util.Date;

public class Singer extends Artist{
    private final ArrayList<Album> albums;
    public Singer(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.albums = new ArrayList<>();
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }
}
