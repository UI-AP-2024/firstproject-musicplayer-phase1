package model.Audio;

import model.GenreModel;

import java.util.Date;

public class MusicModel extends AudioModel{
    private String lyrics;

    public MusicModel(int id, String audioTitle, String artistName, Date releaseDate, GenreModel genre, String audioLink, String cover, String lyrics) {
        super(id, audioTitle, artistName, releaseDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
