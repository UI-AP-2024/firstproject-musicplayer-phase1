package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Premium extends Listener{
    private int daysOfSubs;
    private int countNumberPlaylistP;

    @Override
    public String toString(){
        String result = "";
        result += "Subscription ends in "+getDaysOfSubs()+" days\n"+super.toString();
        return result;
    }

    public Premium(String userName, String password, String name, String email, String phoneNumber, Date dateOfBirth, int accountCredit, ArrayList<Playlist> playlists, Map<Audio, Integer> numberPlays, Date expirationDate, ArrayList<Gener> favoriteGener, int daysOfSubs) {
        super(userName, password, name, email, phoneNumber, dateOfBirth, accountCredit, playlists, numberPlays, expirationDate, favoriteGener);
        this.daysOfSubs = daysOfSubs;
        countNumberPlaylistP=0;
    }

    public int getDaysOfSubs() {
        return daysOfSubs;
    }

    public void setDaysOfSubs(int daysOfSubs) {
        this.daysOfSubs = daysOfSubs;
    }

    public int getCountNumberPlaylistP() {
        return countNumberPlaylistP;
    }

    public void setCountNumberPlaylistP(int countNumberPlaylistP) {
        this.countNumberPlaylistP = countNumberPlaylistP;
    }
}
