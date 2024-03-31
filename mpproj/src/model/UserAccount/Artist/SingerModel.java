package model.UserAccount.Artist;

import model.AlbumModel;

import java.util.ArrayList;
import java.util.Date;

public class SingerModel extends ArtistModel{
    private ArrayList<AlbumModel> albums;
    public SingerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double income, String biography) {
        super(username, password, name, email, phoneNumber, birthday, income, biography);
        this.albums = new ArrayList<AlbumModel>();
    }

    public ArrayList<AlbumModel> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<AlbumModel> albums) {
        this.albums = albums;
    }
}
