package model;

import java.util.Date;

abstract public class Audio {
    private int uniqueId;
    private String audioName;
    private String artistName;
    private int timesPlayed;
    private int likes;
    private Date releaseTime;
    private Genre genre;
    private String musicLink;
    private String cover;
}
