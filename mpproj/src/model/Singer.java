package model;

import java.util.ArrayList;
import java.util.Date;

public class Singer extends Artist{
    private ArrayList<Album> albumList;

    public Singer(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, double income, ArrayList<UserAccount> listFollowers, String bio, ArrayList<Album> albumList) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, income, listFollowers, bio);
        this.albumList = albumList;
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }
}
