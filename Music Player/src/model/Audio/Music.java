package model.Audio;

import model.Audio.Audio;
import model.Genre;

import java.time.LocalDate;

public abstract class Music extends Audio
{
    private String lyrics;
    public Music(String name,Genre genre,String artistName, int year, int month, int day, LocalDate dateOfRelease, String lyrics,String audioLink,String cover) {
        super(name,genre,artistName,year,month,day,dateOfRelease,audioLink,cover);
        this.lyrics = lyrics;
    }
    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
