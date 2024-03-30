package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Listener extends UserAccount{
    private Double accountCredit;
    private ArrayList<Playlist> playlists = new ArrayList<>();
    private Map<Audio,Integer> audioTimesPlayed = new HashMap<>();
    private Date shareExpireTime;
    private ArrayList<Genre> favoriteGenres = new ArrayList<>();
}
