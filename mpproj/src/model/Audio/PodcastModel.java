package model.Audio;

import model.GenreModel;

import java.time.LocalDate;

public class PodcastModel extends AudioModel{
    private String caption;
    public PodcastModel(String audioTitle, String artistName, LocalDate releaseDate, GenreModel genre, String audioLink, String cover, String caption) {
        super(audioTitle, artistName, releaseDate, genre, audioLink, cover);
        this.caption = caption;
    }

    @Override
    public String toString() {
        return super.toString() + "\n Caption :\n" + this.getCaption();
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
