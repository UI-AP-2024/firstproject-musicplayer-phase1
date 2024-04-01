package model;

import java.util.Date;

public class Music extends Audio {
    private String musicText;

    public Music(long id, String name, String nameArtist, Date dateOfRelease, Gener gener, String audioLink, String cover, String musicText) {
        super(id, name, nameArtist, dateOfRelease, gener, audioLink, cover);
        this.musicText = musicText;
    }

    public void setMusicText(String musicText) {
        this.musicText = musicText;
    }

    public String getMusicText() {
        return musicText;
    }
}
