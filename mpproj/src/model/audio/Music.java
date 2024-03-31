package model.audio;

import java.util.Date;

public class Music extends Audio{
    private String lyrics;

    public Music(String audioName, String artistName, Date releaseDate, Genre genre, String cover, String lyrics) {
        super(audioName, artistName, releaseDate, genre, cover);
        this.lyrics = lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }
    public String toString(){
        return "Music/"+super.getArtistName()+"/"+super.getAudioName();
    }
}
