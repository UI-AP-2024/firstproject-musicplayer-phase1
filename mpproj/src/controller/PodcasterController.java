package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.audio.Album;
import model.audio.Music;
import model.audio.Podcast;
import model.database.Database;
import model.user.FreeListener;
import model.user.Podcaster;
import model.user.User;

public class PodcasterController {

    private static PodcasterController podcasterController;
    private PodcasterController(){

    }
    public static PodcasterController getPodcasterController(){
        if(podcasterController==null){
            podcasterController = new PodcasterController();
        }
        return podcasterController;
    }

    private static Podcaster podcaster;
    public static Podcaster getPodcaster() {
        return podcaster;
    }
    public static void setPodcaster(Podcaster podcaster) {
        PodcasterController.podcaster = podcaster;
    }

    public void loginPodcaster(Podcaster podcaster){
        setPodcaster(podcaster);
    }

    public String ShowPodcasterInfo(){
        String txt="Podcaster info:"+
        "\nuser name : "+getPodcaster().getUsername()+
        "\nFirst name : "+getPodcaster().getName()+
        "\nFollowers : "+String.valueOf(getPodcaster().getFollowers().size())+
        "\nBiographi : "+getPodcaster().getBiographi()+"\n";
        if(getPodcaster().getPodcastList().size()==0){
            txt+="No Podcast found!!";
            return txt;
        }
        for(Podcast podcast : getPodcaster().getPodcastList()){
            txt+=podcast.getAudioName()+"\n";
            
        }
        
        return txt;
    }

    public String showViewsStatics(){
        String txt="Podcasts\n";
        if(getPodcaster().getPodcastList().size()==0){
            txt+="No Podcast found!!";
            return txt;
        }
        for(Podcast podcast : getPodcaster().getPodcastList()){
            txt+="-"+podcast.getAudioName()+"("+String.valueOf(podcast.getNumberOfPlays())+")\n";
        }

        return txt;
    }


    
}
