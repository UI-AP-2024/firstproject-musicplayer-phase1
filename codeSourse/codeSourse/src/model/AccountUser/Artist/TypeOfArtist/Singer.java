package model.AccountUser.Artist.TypeOfArtist;
import model.AccountUser.Artist.Artist;
import model.Album;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Singer extends Artist {
    private static List<Album> albums;

    //*********************************************
    public Singer(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double income,String biography) {
        super(userName, password, fullName, email, phoneNumber,birthDate,income,biography);
        this.albums = new ArrayList<>();
    }
    //*********************************************

    public static List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
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