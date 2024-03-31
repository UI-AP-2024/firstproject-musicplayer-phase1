package model.UserAccount.Artist;

import model.Album;

import java.util.ArrayList;
import java.util.Date;

public class SingerModel extends ArtistModel{
    private ArrayList<Album> albums;
    public SingerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.albums = new ArrayList<>();
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
