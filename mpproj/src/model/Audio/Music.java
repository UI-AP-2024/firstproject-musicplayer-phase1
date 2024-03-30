package model.Audio;

import model.Genre;

import java.util.Date;

public class Music extends Audio{
    private final String lyrics;

    public Music(int id, String audioTitle, String artistName, Date releaseDate, Genre genre, String audioLink, String cover, String lyrics) {
        super(id, audioTitle, artistName, releaseDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }
}
