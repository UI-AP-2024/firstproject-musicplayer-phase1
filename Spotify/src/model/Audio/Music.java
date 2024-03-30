package model.Audio;

import model.Genre.Genre;

import java.util.Date;

public class Music extends Audio{
    String lyric;
    public Music(String fileName, String artistName, Date dateOfRelease, Genre genre, String fileLink, String cover,String lyric) {
        super(fileName, artistName, dateOfRelease, genre, fileLink, cover);
        this.lyric = lyric;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\nmusic lyric : ");
        context.append(lyric);
        return context.toString();
    }
}
