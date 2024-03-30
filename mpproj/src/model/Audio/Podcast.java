package model.Audio;

import model.Genre;

import java.util.Date;

public class Podcast extends Audio{
    private final String caption;
    public Podcast(int id, String audioTitle, String artistName, Date releaseDate, Genre genre, String audioLink, String cover, String caption) {
        super(id, audioTitle, artistName, releaseDate, genre, audioLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
}
