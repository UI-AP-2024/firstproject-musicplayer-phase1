package model;

import java.util.Date;

public abstract class Audio {
    private int uniqeId;
    private String title;
    private String artist;
    private int playCount;
    private int likes;
    private Date releaseDate;
    private Genre genre;
    private String audioLink;
    private String cover;

    //*********************************************

    public Audio(int uniqeId, String title, String artist, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover) {
        this.uniqeId = uniqeId;
        this.title = title;
        this.artist = artist;
        this.playCount = 0;
        this.likes = 0;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.audioLink = audioLink;
        this.cover = cover;
    }

    //*********************************************

    public int getUniqeId() {
        return uniqeId;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public int getPlayCount() {
        return playCount;
    }

    public int getLikes() {
        return likes;
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

    //*********************************************

    public void setUniqeId(int uniqeId) {
        this.uniqeId = uniqeId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }
    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}













