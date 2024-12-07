package Model.Audios;

import java.util.Date;

public class PodcastModel extends AudioModel {
    private String caption;


    public PodcastModel(String audioName , String artistName , Date releaseTime , String genre , String audioLink , String cover , String caption) {
        super(audioName, artistName, releaseTime, genre, audioLink, cover);
        this.caption = caption;
    }

    public String getCaption(){
        return this.caption;
    }

    public void setCaption(String caption){
        this.caption = caption;
    }
}
