package Model;

import java.util.ArrayList;
import java.util.Date;

public class SingerModel extends ArtistModel{
    private ArrayList<Album> albums;
    public SingerModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate,  String biography){
        super(username,password,fullName,email,phoneNumber,birthDate,biography);
        albums = new ArrayList<>();
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
