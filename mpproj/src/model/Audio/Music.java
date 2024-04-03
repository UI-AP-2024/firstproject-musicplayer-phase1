package model.Audio;

import model.Genre;
import model.SubscriptionPlan;
import model.UserAccount.User;

import java.util.Date;

public class Music extends Audio {
    private String caption;

    public Music(int id, String title, String artistName, int playCount, int likes, Date releaseDate, Genre genre, String audioFileLink, String cover, String caption) {
        super(id, title, artistName, playCount, likes, releaseDate, genre, audioFileLink, cover);
        this.caption = caption;
    }
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder(" music caption");
        return super.toString()+"\n"+String.valueOf(res);
    }
    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }
}
