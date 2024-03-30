package Model;

import java.util.ArrayList;
import java.util.Date;

public class SingerModel extends ArtistModel{
    private ArrayList<Album> albums;
    public SingerModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate, double salary, String biography){
        super(username,password,fullName,email,phoneNumber,birthDate,salary,biography);
        albums = new ArrayList<>();
        Database.getDatabase().getSinger().add(this);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}
