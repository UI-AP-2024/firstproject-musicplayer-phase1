package Model.Audios;

import java.util.Date;

abstract public class AudioModel {
    private final int ID;
    private String audioName;
    private String artistName;
    private int playCount;
    private int likeCount;
    private final Date releaseTime;
    private final Genre genre;
    private String audioLink;
    private String cover;

    private static int ID_count = 1;

    public AudioModel(String audioName , String artistName , Date releaseTime , String genre , String audioLink , String cover){
        this.ID = ID_count++;
        this.audioName = audioName;
        this.artistName = artistName;
        this.playCount = 0;
        this.likeCount = 0;
        this.releaseTime = releaseTime;
        this.genre = Genre.valueOf(genre);
        this.audioLink = audioLink;
        this.cover = cover;
    }

    public int getID(){
        return this.ID;
    }
    public String getAudioName(){
        return this.audioName;
    }

    public void setAudioName(String audioName){
        this.audioName = audioName;
    }
    public String getArtistName(){
        return this.artistName;
    }

    public void setArtistName(String artistName){
        this.artistName = artistName;
    }
    public int getPlayCount(){
        return this.playCount;
    }

    public void setPlayCount(int playCount){
        this.playCount = playCount;
    }

    public int getLikeCount(){
        return this.likeCount;
    }
    public void setLikeCount(int likeCount){
        this.likeCount = likeCount;
    }

    public Date getReleaseTime(){
        return this.releaseTime;
    }

    public String getGenre(){
        return this.genre.toString();
    }
    public String getAudioLink(){
        return this.audioLink;
    }

    public void setAudioLink(String audioLink){
        this.audioLink = audioLink;
    }

    public String getCover(){
        return this.cover;
    }

    public void setCover(String cover){
        this.cover = cover;
    }

    @Override
    public String toString(){
        return "Audio ID : "+ this.ID +
                "\nAudio Name : " + this.audioName +
                "\nArtist Name : " + this.artistName +
                "\nLike : "+ this.likeCount +
                "\nPlay Coutn : "+ this.playCount +
                "\nAudio link : " + this.audioLink;
    }
}
