package model.Audio;

import model.Audio.Audio;
import model.Genre;

import java.time.LocalDate;

public abstract class Podcast extends Audio
{
    private String caption;
    public Podcast(String name,Genre genre,String artistName, int year, int month, int day, LocalDate dateOfRelease, String caption,String audioLink,String cover) {
        super(name,genre,artistName,year,month,day,dateOfRelease,audioLink,cover);
        this.caption = caption;
    }
    public String getCaption() {
        return caption;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
}
