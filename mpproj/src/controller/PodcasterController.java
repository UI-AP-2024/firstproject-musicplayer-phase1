package controller;

import model.*;

import java.security.PublicKey;
import java.util.Date;

public class PodcasterController {
    private static PodcasterController podcasterController;

    private PodcasterController(){
    }

    public static PodcasterController getPodcasterController(){
        if (podcasterController == null)
            podcasterController = new PodcasterController();
        return podcasterController;
    }

    private Podcaster userAccount;

    public Podcaster getUserAccount(){
        return userAccount;
    }

    public void setUserAccount(Podcaster userAccount){
        this.userAccount = userAccount;
    }

    public String publishPodcast(String name,String gener,String caption,String link,String cover){
        Audio.setCountId(Audio.getCountId()+1);
        Date date = new Date();
        Gener gener1 = Gener.valueOf(gener);
        Podcast podcast = new Podcast(Audio.getCountId(),name,getUserAccount().getName(),date,gener1,link,cover,caption);
        Database.getDataBase().getAudio().add(podcast);
        ((Podcaster) getUserAccount()).getPodcastList().add(podcast);
        return "Podcast successfully added";
    }




















}
