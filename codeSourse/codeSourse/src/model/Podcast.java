package model;

import java.util.Date;

public class Podcast extends Audio{
    private String caption;

    //*********************************************

    public Podcast(int uniqeId, String title, String artist, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover,String caption) {
        super(uniqeId, title, artist, playCount, likes, releaseDate, genre, audioLink, cover);
        this.caption = caption;
    }
    //*********************************************

    public String getCaption(){
        return caption;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }


}
