package model;

import java.util.ArrayList;
import java.util.Date;

public class Podcaster extends Artist{
    private ArrayList<Podcast> podcasts = new ArrayList<>();

    public Podcaster(String uniqueUserName, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography) {
        super(uniqueUserName, password, fullName, email, phoneNumber, birthDate, biography);
    }
}
