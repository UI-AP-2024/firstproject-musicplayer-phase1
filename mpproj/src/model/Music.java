package model;

import java.util.Date;

public class Music extends Audio {
    private String musicText;

    public Music(long id, String name, String nameArtist, int numberOfPolys, int numberOfLikes, Date dateOfRelease, Gener gener, String audioLink, String cover, String musicText) {
        super(id, name, nameArtist, numberOfPolys, numberOfLikes, dateOfRelease, gener, audioLink, cover);
        this.musicText = musicText;
    }

    public void setMusicText(String musicText) {
        this.musicText = musicText;
    }

    public String getMusicText() {
        return musicText;
    }
}
