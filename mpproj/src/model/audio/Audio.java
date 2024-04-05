package model.audio;

import java.util.Date;

public abstract class Audio {
    private final long id;
    private String audioName;
    private String artistName;
    private String artistUsername;
    private long numberOfPlays;
    private long numberOfLikes;
    private final Date releaseDate;
    private final Genre genre;
    private String link;
    private String cover;
    private static long audioCounter=0;
    public Audio(String audioName, String artistName, String artistUsername,Date releaseDate, Genre genre,String link, String cover) {
        audioCounter++;
        this.audioName = audioName;
        this.artistName = artistName;
        this.artistUsername = artistUsername;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.cover = cover;
        this.numberOfLikes=0;
        this.numberOfPlays=0;
        this.id=audioCounter;
        this.link = link;
    }
    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
    public void setNumberOfPlays(long numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }
    public void setNumberOfLikes(long numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setCover(String cover) {
        this.cover = cover;
    }
    public static void setAudioCounter(long audioCounter) {
        Audio.audioCounter = audioCounter;
    }
    public long getNumberOfLikes() {
        return numberOfLikes;
    }
    public Date getReleaseDate() {
        return releaseDate;
    }
    public Genre getGenre() {
        return genre;
    }
    public String getLink() {
        return link;
    }
    public String getCover() {
        return cover;
    }
    public static long getAudioCounter() {
        return audioCounter;
    }
    public long getId() {
        return id;
    }
    public String getAudioName() {
        return audioName;
    }
    public String getArtistName() {
        return artistName;
    }
    public long getNumberOfPlays() {
        return numberOfPlays;
    }
    public String getArtistUsername() {
        return artistUsername;
    }
    public void setArtistUsername(String artistUsername) {
        this.artistUsername = artistUsername;
    }
    @Override
    public String toString(){
        return audioName +" By "+artistUsername;
    }
    

    

}
