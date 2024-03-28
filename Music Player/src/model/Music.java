package model;

import java.time.LocalDate;

public abstract class Music extends Audio
{
    private String lyrics;
    public Music(String artistName, int year, int month, int day, LocalDate dateOfRelease, Genre genre, String name, String audioLink, String cover) {
        super(artistName, year, month, day, dateOfRelease, genre, name, audioLink, cover);
    }
    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
