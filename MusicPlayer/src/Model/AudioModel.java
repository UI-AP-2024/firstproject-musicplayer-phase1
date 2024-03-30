package Model;

import java.util.Date;

abstract public class AudioModel {
    private static int ID=0;
    private int IDCount;
    private String audioName;
    private String artistName;
    private int playedCount;
    private int likeCount;
    private Date releaseDate;
    private Genre genre;
    private String audioLink;
    private String cover;
    public AudioModel(String audioName, String artistName, Genre genre, String audioLink, String cover) {
        IDCount=ID++;
        this.audioName = audioName;
        this.artistName = artistName;
        this.genre = genre;
        this.audioLink = audioLink;
        this.cover = cover;
        this.playedCount = 0;
        this.likeCount = 0;
        this.releaseDate = new Date();
        Database.getDatabase().getAudios().add(this);
    }
    public static int getID() {
        return ID;
    }

    public String getAudioName() {
        return audioName;
    }

    public String getArtistName() {
        return artistName;
    }

    public int getPlayedCount() {
        return playedCount;
    }

    public int getLikeCount() {
        return likeCount;
    }

    public Date getDate() {
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

    public int getIDCount() {
        return IDCount;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public static void setID(int ID) {
        AudioModel.ID = ID;
    }

    public void setAudioName(String audioName) {
        this.audioName = audioName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setPlayedCount(int playedCount) {
        this.playedCount = playedCount;
    }

    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    public void setDate(Date releaseDate) {
        this.releaseDate = AudioModel.this.releaseDate;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public void setAudioLink(String audioLink) {
        this.audioLink = audioLink;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
