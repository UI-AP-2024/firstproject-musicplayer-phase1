package model;

import java.util.Date;

public class Music extends Audio {
    private String lyrics;

    //*********************************************
    public Music(int uniqeId, String title, String artist, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover, String lyrics) {
        super(uniqeId, title, artist, playCount, likes, releaseDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }
    //*********************************************
    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
