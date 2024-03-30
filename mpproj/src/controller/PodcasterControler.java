package controller;

import model.Audio.Audio;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre;
import model.UserAccount.Podcaster;
import model.UserAccount.RegularListener;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class PodcasterControler extends ArtistControler{
    public  String publishPodcast(String title,String genre,String caption,String link,String cover){
        if (artist instanceof Podcaster){
            Podcast podcast0=new Podcast(Podcast.getIdcounter(),title,artist.getFullName(),0,0,new Date(), Genre.valueOf(genre),link,cover,caption);
            Audio.setIdcounter(Audio.getIdcounter()+1);
            ((Podcaster) artist).getPodcasts().add(podcast0);
            Database.getDatabase().getAudios().add(podcast0);
        }
        return "podcast added";
    }
}
