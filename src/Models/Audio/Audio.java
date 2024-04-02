package Models.Audio;
import Models.Genre;

import java.time.LocalDate;

public abstract class Audio {
    private int id;
    private static int idCounter = 1;
    private String fileName;
    private String artistName;
    private int playCount;
    private int likesCount;
    private LocalDate publishDate;
    private Genre genre;
    private String audioLink;
    private String cover;

    public Audio(String fileName, String artistName, int playCount, int likesCount, LocalDate publishDate,
                 Genre genre, String audioLink, String cover) {
        this.id = idCounter++;
        this.fileName = fileName;
        this.artistName = artistName;
        this.playCount = playCount;
        this.likesCount = likesCount;
        this.publishDate = publishDate;
        this.genre = genre;
        this.audioLink = audioLink;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getPlayCount() {
        return playCount;
    }

    public void setPlayCount(int playCount) {
        this.playCount = playCount;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public LocalDate getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDate publishDate) {
        this.publishDate = publishDate;
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
