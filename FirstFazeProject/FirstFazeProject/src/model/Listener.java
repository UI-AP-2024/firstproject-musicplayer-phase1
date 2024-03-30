package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends UserAccount{
    Double accountCredit;
    ArrayList<Playlist> playlists = new ArrayList<>();
    Map<Audio,Integer> audioTimesPlayed = new HashMap<>();
    Date shareExpireTime;
    ArrayList<Genre> favoriteGenres = new ArrayList<>();
}
