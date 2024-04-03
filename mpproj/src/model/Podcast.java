package model;

import java.util.Date;

public class Podcast extends Audio {
    private String caption;

    @Override
    public String toString(){
        String result = "";
        result += super.toString();
        result += "\nCaption: "+getCaption();
        return result;
    }

    public Podcast(long id, String name, String nameArtist, Date dateOfRelease, Gener gener, String audioLink, String cover, String caption) {
        super(id, name, nameArtist, dateOfRelease, gener, audioLink, cover);
        this.caption = caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
