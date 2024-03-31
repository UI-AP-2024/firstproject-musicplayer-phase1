package model.audio;

import java.util.Date;

public class Podcast extends Audio{
    private String caption;

    public Podcast(String audioName, String artistName, Date releaseDate, Genre genre, String cover, String caption) {
        super(audioName, artistName, releaseDate, genre, cover);
        this.caption = caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }
    @Override
    public String toString(){
        return "podcast/"+super.getArtistName()+"/"+super.getAudioName();
    }
    
}
