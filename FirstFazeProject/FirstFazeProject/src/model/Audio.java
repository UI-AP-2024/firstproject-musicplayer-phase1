package model;

import java.util.Date;

abstract public class Audio {
    private int uniqueId;
    private String audioName;
    private String artistName;
    private int timesPlayed;
    private int likes;
    private Date releaseTime;
    private Genre genre;
    private String musicLink;
    private String cover;

    public Audio(int uniqueId, String audioName, String artistName, Genre genre, String musicLink, String cover) {
        this.uniqueId = uniqueId;
        this.audioName = audioName;
        this.artistName = artistName;
        this.genre = genre;
        this.musicLink = musicLink;
        this.cover = cover;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getAudioName() {
        return audioName;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getTimesPlayed() {
        return timesPlayed;
    }

    public void setTimesPlayed(int timesPlayed) {
        this.timesPlayed = timesPlayed;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public Date getReleaseTime() {
        return releaseTime;
    }

    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getMusicLink() {
        return musicLink;
    }

    public void setMusicLink(String musicLink) {
        this.musicLink = musicLink;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    @Override
    public String toString(){
        String result = null;
        result += "Audio's ID : "+this.uniqueId+"\r\n"+"Audio's name : "+this.audioName+"\r\n"
                +"Audio artist's name : "+this.artistName+"Audio's genre : " +this.genre
                +"Audio's musicLink : " +this.musicLink+"Audio's cover : " +this.cover;
        return result;
    }
}
