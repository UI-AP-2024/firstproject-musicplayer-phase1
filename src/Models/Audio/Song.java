package Models.Audio;

import Models.Genre;

import java.time.LocalDate;

public class Song extends Audio {
    private String lyrics;

    public Song(String fileName, String artistName, int playCount, int likesCount, LocalDate publishDate,
                Genre genre, String audioLink, String cover, String lyrics) {
        super(fileName, artistName, playCount, likesCount, publishDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
