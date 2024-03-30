package model.Audio;

import model.Genre;

import java.util.Date;

public abstract class Audio {
    private static int tempID = 0;
    private final int id;
    private final String audioTitle;
    private final String artistName;
    private int playsCount;
    private int likesCount;
    private final Date releaseDate;
    private final Genre genre;
    private final String audioLink;
    private final String cover;

    public Audio(int id, String audioTitle, String artistName, Date releaseDate, Genre genre, String audioLink, String cover) {
        this.id = ++tempID;
        this.audioTitle = audioTitle;
        this.artistName = artistName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.audioLink = audioLink;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public String getAudioTitle() {
        return audioTitle;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getPlaysCount() {
        return playsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public Genre getGenre() {
        return genre;
    }

    public String getAudioLink() {
        return audioLink;
    }

    public String getCover() {
        return cover;
    }

    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }
}
