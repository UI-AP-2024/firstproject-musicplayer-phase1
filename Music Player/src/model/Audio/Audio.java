package model.Audio;

import model.Genre;

import java.time.LocalDate;

public abstract class Audio
{
    private int i=0;
    private String name;
    private String artistName;
    private int playsCount;
    private int likesCount;
    private int year,month,day;
    private LocalDate dateOfRelease;
    private Genre genre;
    private String audioLink;
    private String cover;
    public Audio(String name,Genre genre,String artistName, int year, int month, int day, LocalDate dateOfRelease, String audioLink, String cover) {
        this.artistName = artistName;
        this.year = year;
        this.month = month;
        this.day = day;
        this.dateOfRelease = dateOfRelease;
        this.genre = genre;
        this.name = name;
        this.audioLink = audioLink;
        this.cover = cover;
        i = (++i);
    }
    public int getI() {
        return i;
    }
    public void setI(int i) {
        this.i = i;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getArtistName() {
        return artistName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getPlaysCount() {
        return playsCount;
    }

    public void setPlaysCount(int playsCount) {
        this.playsCount = playsCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getYear() {
        return year;
    }
    public void setYear(int year) {
        this.year = year;
    }
    public int getMonth() {
        return month;
    }
    public void setMonth(int month) {
        this.month = month;
    }
    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
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
    public String getAudioLink() {
        return audioLink;
    }
    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }
    public String getCover() {
        return cover;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
}
