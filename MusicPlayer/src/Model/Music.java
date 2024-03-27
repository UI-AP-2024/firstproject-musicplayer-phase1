package Model;

public class Music extends AudioModel{
    private String lyrics;
    public Music(String audioName, String artistName, Genre genre, String audioLink, String cover, String lyrics) {
        super(audioName, artistName, genre, audioLink, cover);
        this.lyrics = lyrics;
    }

    public String getLyrics() {
        return lyrics;
    }

    public void setLyrics(String lyrics) {
        this.lyrics = lyrics;
    }
}
