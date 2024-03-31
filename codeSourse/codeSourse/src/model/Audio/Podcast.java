package model.Audio;

import model.Audio.Audio;
import model.Genre;

import java.util.Date;

public class Podcast extends Audio {
    private String caption;

    //*********************************************

    public Podcast(int uniqeId, String title, String artist, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover, String caption) {
        super(uniqeId, title, artist, playCount, likes, releaseDate, genre, audioLink, cover);
        this.caption = caption;
    }
    //*********************************************

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    //*********************************************
    @Override
    public String toString() {
        return "Podcast{" +
                "uniqueId=" + getUniqeId() +
                ", title='" + getTitle() + '\'' +
                ", artist='" + getArtist() + '\'' +
                ", playCount=" + getPlayCount() +
                ", likes=" + getLikes() +
                ", releaseDate=" + getReleaseDate() +
                ", genre=" + getGenre() +
                ", audioLink='" + getAudioLink() + '\'' +
                ", cover='" + getCover() + '\'' +
                ", caption='" + caption + '\'' +
                '}';
    }

}
