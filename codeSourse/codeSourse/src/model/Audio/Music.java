package model.Audio;

import model.Audio.Audio;
import model.Genre;

import java.util.Date;

public class Music extends Audio {
    private String lyrics;

    //*********************************************
    public Music(int uniqeId, String title, String artist, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover, String lyrics) {
        super(uniqeId, title, artist, playCount, likes, releaseDate, genre, audioLink, cover);
        this.lyrics = lyrics;
    }
    //*********************************************
    public String getLyrics() {
        return lyrics;
    }
    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
    //*********************************************
    @Override
    public String toString() {
        return "Music{" +
                "uniqueId=" + getUniqeId() +
                ", title='" + getTitle() + '\'' +
                ", artist='" + getArtist() + '\'' +
                ", playCount=" + getPlayCount() +
                ", likes=" + getLikes() +
                ", releaseDate=" + getReleaseDate() +
                ", genre=" + getGenre() +
                ", audioLink='" + getAudioLink() + '\'' +
                ", cover='" + getCover() + '\'' +
                ", lyrics='" + lyrics + '\'' +
                '}';
    }
}
