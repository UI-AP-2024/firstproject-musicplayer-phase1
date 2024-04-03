package model;

public class Music extends Audio{
    private String musicLyrics;

    public Music(String audioName, String artistName, Genre genre, String musicLink, String cover, String musicLyrics) {
        super(0, audioName, artistName, genre, musicLink, cover);
        this.musicLyrics = musicLyrics;
    }

    public String getMusicLyrics() {
        return musicLyrics;
    }

    public void setMusicLyrics(String musicLyrics) {
        this.musicLyrics = musicLyrics;
    }
}
