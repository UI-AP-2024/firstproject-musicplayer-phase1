package model.Audio;
import java.util.Date;

import model.Database.Database;
import model.Genre.Genre;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.userAccount;

public abstract class Audio {
    private int audioId;
    private static int audioIdCounter=0;
    private String fileName;
    private String artistName;
    private Artist artist;
    private int numberOfPlays;
    private int numberOfLikes;
    private Date dateOfRelease;
    private Genre genre;
    private String fileLink;
    private String cover;

    public Audio(String fileName, String artistName, Date dateOfRelease, Genre genre, String fileLink, String cover) {
        this.audioId=++audioIdCounter;
        this.fileName = fileName;
        this.artistName = artistName;
        this.numberOfPlays = 0;
        this.numberOfLikes=0;
        this.dateOfRelease = dateOfRelease;
        this.genre = genre;
        this.fileLink = fileLink;
        this.cover = cover;
        for (userAccount user: Database.getDatabase().getAllUsersList()){
            if (user.getFullName().equals(artistName)){
                this.artist = (Artist) user;
            }
        }
    }
    @Override
    public String toString(){
        StringBuilder context=new StringBuilder();
        context.append("audio id : ");
        context.append(audioId);
        context.append("\naudio name : ");
        context.append(fileName);
        context.append("\nartist name : ");
        context.append(artistName);
        context.append("\nnumber of plays : ");
        context.append(numberOfPlays);
        context.append("\nnumber of likes : ");
        context.append(numberOfLikes);
        context.append("\ndate of release : ");
        context.append(dateOfRelease.getYear()+1900);
        context.append("/");
        context.append(dateOfRelease.getMonth()+1);
        context.append("/");
        context.append(dateOfRelease.getDate());
        return context.toString();
    }

    public int getAudioId() {
        return audioId;
    }

    public int getAudioIdCounter(){
        return audioIdCounter;
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

    public int getNumberOfPlays() {
        return numberOfPlays;
    }

    public void setNumberOfPlays(int numberOfPlays) {
        this.numberOfPlays = numberOfPlays;
    }

    public int getNumberOfLikes() {
        return numberOfLikes;
    }

    public void setNumberOfLikes(int numberOfLikes) {
        this.numberOfLikes = numberOfLikes;
    }

    public Date getDateOfRelease() {
        return dateOfRelease;
    }

    public void setDateOfRelease(Date dateOfRelease) {
        this.dateOfRelease = dateOfRelease;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }
}
