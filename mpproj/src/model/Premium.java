package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Premium extends Listener{
    private int daysOfSubs;

    public Premium(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberOfPlays, Date expirationDate, ArrayList<Gener> favoriteGener, int daysOfSubs) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, accountCredit, playlists, numberOfPlays, expirationDate, favoriteGener);
        this.daysOfSubs = daysOfSubs;
    }

    public int getDaysOfSubs() {
        return daysOfSubs;
    }

    public void setDaysOfSubs(int daysOfSubs) {
        this.daysOfSubs = daysOfSubs;
    }
}
