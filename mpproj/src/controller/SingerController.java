package controller;

import model.Album;
import model.Premium;
import model.Singer;
import model.UserAccount;

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















}
