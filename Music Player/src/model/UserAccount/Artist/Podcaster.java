package model.UserAccount.Artist;

import model.Audio.Podcast;
import model.UserAccount.Artist.Artist;

import java.time.LocalDate;
import java.util.ArrayList;

public class Podcaster extends Artist {

    private ArrayList<Podcast> podcasts = new ArrayList<Podcast>();

    public Podcaster(String userName, String passWord, String name, String email, String phoneNumber, LocalDate dateOfBirth, String biography) {
        super(userName, passWord, name, email, phoneNumber, dateOfBirth, biography);
    }

    public ArrayList<Podcast> getPodcasts() {
        return podcasts;
    }

    public void setPodcasts(ArrayList<Podcast> podcasts) {
        this.podcasts = podcasts;
    }

    @Override
    public String toString() {
        return "UserName: " + getUserName() + "\t" + "PassWord: " + getPassWord() + "\t" + "Name: " + getName() + "\t" + "Email: " + getEmail() + "\t" + "PhoneNumber: " + getPhoneNumber() + "\t" + "Birth Date: " + getDateOfBirth() + "\t" + "Biography: " + getBiography();
    }
}
