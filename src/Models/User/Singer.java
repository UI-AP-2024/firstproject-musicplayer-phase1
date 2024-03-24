
package Models.User;
import Models.Album;

import java.util.ArrayList;
public class Singer extends Artist {
    private ArrayList<Album> albums;

    public Singer(String username, String password, String name, String email, String phoneNumber,
                  java.util.Date dateOfBirth, double income, String biography, ArrayList<Album> albums) {
        super(username, password, name, email, phoneNumber, dateOfBirth, income, biography);
        this.albums = albums;
    }
    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
}