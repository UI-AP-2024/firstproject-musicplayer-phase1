package model.UserAccount.Artist;

import model.Album;
import model.UserAccount.Artist.Artist;

import java.time.LocalDate;
import java.util.ArrayList;

public class Singer extends Artist {
    private ArrayList<Album> albums = new ArrayList<Album>();
    public Singer(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth,String biography) {
        super(userName, passWord, name, email, phoneNumber, dateOfBirth, biography);
    }

    public ArrayList<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(ArrayList<Album> albums) {
        this.albums = albums;
    }
    @Override
    public String toString() {
        return "UserName: " + getUserName() + "\t" + "PassWord: " + getPassWord() + "\t" + "Name: " + getName() + "\t" + "Email: " + getEmail() + "\t" + "PhoneNumber: " + getPhoneNumber() + "\t" + "Birth Date: " + getDateOfBirth() + "\t" + "Biography: " + getBiography();
    }
}
