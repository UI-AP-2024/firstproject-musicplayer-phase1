package model.Audio;

import model.Genre.Genre;

import java.util.Date;

public class Podcast extends Audio{
    private String caption;

    public Podcast(String fileName, String artistName, Date dateOfRelease, Genre genre, String fileLink, String cover,String caption) {
        super(fileName, artistName, dateOfRelease, genre, fileLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\npodcast caption : ");
        context.append(caption);
        return context.toString();
    }
}
