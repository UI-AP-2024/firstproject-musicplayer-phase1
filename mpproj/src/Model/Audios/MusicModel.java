package Model.Audios;

import java.util.Date;

public class MusicModel extends AudioModel {
    private String lyrics;

    public MusicModel(String audioName , String artistName , Date releaseTime , String genre , String audioLink , String cover , String lyrics){
        super(audioName , artistName , releaseTime , genre , audioLink , cover);
        this.lyrics = lyrics;
    }

    public String getLyrics(){
        return this.lyrics;
    }

    public void setLyrics(String lyrics){
        this.lyrics = lyrics;
    }
}
