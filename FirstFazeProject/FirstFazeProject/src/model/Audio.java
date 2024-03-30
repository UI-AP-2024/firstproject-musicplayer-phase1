package model;

import java.util.Date;

abstract public class Audio {
    int uniqueId;
    String audioName;
    String artistName;
    int timesPlayed;
    int likes;
    Date releaseTime;
    Genre genre;
    String musicLink;
    String cover;
}
