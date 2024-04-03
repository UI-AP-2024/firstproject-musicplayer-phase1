package controller;

import model.*;

import javax.xml.crypto.Data;
import java.util.Date;

public class SingerController {

    private static SingerController singerController;

    private SingerController(){
    }

    public static SingerController getSingerController(){
        if(singerController == null)
            singerController = new SingerController();
        return singerController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount(){
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount){
        this.userAccount = userAccount;
    }

    public String newAlbum(String name){
        Album.setCountId( Album.getCountId()+1 );
        Album album = new Album(Album.getCountId(),name,getUserAccount().getName());
        ((Singer) getUserAccount()).getAlbumList().add(album);
        return "Album successfully added";
    }
    public String publishMusic(String name, String gener,String musicText,String link,String cover,long albumId){
        Audio.setCountId(Audio.getCountId()+1);
        Date date = new Date();
        Gener gener1 = Gener.valueOf(gener);
        Music music = new Music(Audio.getCountId(),name,getUserAccount().getName(),date,gener1,link,cover,musicText);
        Database.getDataBase().getAudio().add(music);
        for ( Album album : ((Singer) getUserAccount()).getAlbumList() ){
            if ( album.getId() == albumId ){
                album.getMusicList().add(music);
                break;
            }
        }
        return "Podcast successfully added";
    }
}