package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class Listener extends UserAccount{
    int accountCredit;
    ArrayList<Playlist> playlists;
    Map<Audio,Integer> numberOfPlays;
    Date expirationDate;
    ArrayList<Gener> favoriteGener;
}
