package model.Audio;

import model.GenreModel;

import java.time.LocalDate;

public class MusicModel extends AudioModel{
    private String lyrics;

    public MusicModel(String audioTitle, String artistName, LocalDate releaseDate, GenreModel genre, String audioLink, String cover, String lyrics) {
        super(audioTitle, artistName, releaseDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }

    @Override
    public String toString() {
        return super.toString() + "\nLyrics :\n" + this.getLyrics();
    }



    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
