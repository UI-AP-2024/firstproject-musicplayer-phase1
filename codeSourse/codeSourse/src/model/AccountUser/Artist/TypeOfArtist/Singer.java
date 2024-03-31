package model.AccountUser.Artist.TypeOfArtist;
import model.AccountUser.Artist.Artist;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Singer extends Artist {
    private List<String> albums;

    //*********************************************
    public Singer(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double income,String biography) {
        super(userName, password, fullName, email, phoneNumber,birthDate,income,biography);
        this.albums = new ArrayList<>();
    }
    //*********************************************

    public List<String> getAlbums() {
        return albums;
    }

    public void setAlbums(List<String> albums) {
        this.albums = albums;
    }
    //*********************************************
    @Override
    public String toString() {
        return "Artist{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", albums=" + albums +
                '}';
    }
}