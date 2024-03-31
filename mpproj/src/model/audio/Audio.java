package model.audio;

import java.util.Date;

public abstract class Audio {
    private final long id;
    private String audioName;
    private String artistName;
    private long numberOfPlays;
    private long numberOfLikes;
    private final Date releaseDate;
    private final Genre genre;
    private String link;
    private String cover;
    private static long audioCounter=0;
    public Audio(String audioName, String artistName, Date releaseDate, Genre genre, String cover) {
        audioCounter++;
        this.audioName = audioName;
        this.artistName = artistName;
        this.releaseDate = releaseDate;
        this.genre = genre;
        this.cover = cover;
        this.numberOfLikes=0;
        this.numberOfPlays=0;
        this.id=audioCounter;
        this.link = "htpps://fakepotify.com/Audio/"+String.valueOf(id)+"/"+audioName+String.valueOf(id);
    }
    

    

}
