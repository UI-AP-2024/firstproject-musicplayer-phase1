package model.model.audio;

import model.enums.Genre;
import model.model.user.Artist;

import java.time.LocalDate;

public abstract class Audio {
    protected int id;
    protected String title;

    protected Artist artist;
    protected int numberOfPlay;
    protected int numberOfLikes;
    protected LocalDate dateOfRelease;
    protected Genre genre;
    protected String audioFileLink;
    protected String cover;

    protected Audio() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public int getNumberOfPlay() {
        return numberOfPlay;
    }

    public void setNumberOfPlay(int numberOfPlay) {
        this.numberOfPlay = numberOfPlay;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public LocalDate getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(LocalDate dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getAudioFileLink() {
        return audioFileLink;
    }

    public void setAudioFileLink(String audioFileLink) {
        this.audioFileLink = audioFileLink;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString() {
        return "Audio{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", artistName='" + artist + '\'' +
                ", numberOfPlay=" + numberOfPlay +
                ", numberOfLikes=" + numberOfLikes +
                ", dateOfRelease=" + dateOfRelease +
                ", genre=" + genre +
                ", audioFileLink='" + audioFileLink + '\'' +
                ", cover='" + cover + '\'' +
                '}';
    }
}
