package model;

import java.time.LocalDate;
import java.util.ArrayList;

public class Podcaster extends Artist{

    ArrayList<Podcast> podcasts = new ArrayList<Podcast>();
    public Podcaster(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, String biography) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, biography);
    }
}
