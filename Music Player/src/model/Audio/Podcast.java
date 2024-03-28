package model.Audio;

import model.Audio.Audio;
import model.Genre;

import java.time.LocalDate;

public abstract class Podcast extends Audio
{
    private String caption;
    public Podcast(String artistName, int year, int month, int day, LocalDate dateOfRelease, Genre genre, String caption, String name, String audioLink, String cover) {
        super(artistName, year, month, day, dateOfRelease, genre, name, audioLink, cover);
        this.caption = caption;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
