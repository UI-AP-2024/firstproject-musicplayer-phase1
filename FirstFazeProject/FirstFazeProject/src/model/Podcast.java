package model;

public class Podcast extends Audio{
    private String caption;

    public Podcast(String audioName, String artistName, Genre genre, String musicLink, String cover ,String caption) {
        super( audioName, artistName, genre, musicLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
