package controller;

import model.Album;
import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre;
import model.UserAccount.Podcaster;
import model.UserAccount.RegularListener;
import model.UserAccount.Singer;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class SingerControler extends ArtistControler {
    public  String publishPodcast(String title,String genre,String caption,String link,String cover,int albumId){
        if (artist instanceof Singer){
            Music music0=new Music(Audio.getIdcounter(),title,artist.getFullName(),0,0,new Date(), Genre.valueOf(genre),link,cover,caption);
            int tmpindex=0;
            Audio.setIdcounter(Audio.getIdcounter()+1);
            for(Album album: ((Singer) artist).getAlbums()){
                if(albumId==album.getId()){
                    ((Singer) artist).getAlbums().get(tmpindex).getSongs().add(music0);
                }else
                    tmpindex++;
            }
            Database.getDatabase().getAudios().add(music0);
        }
        return "music added";
    }
    public String newAlbum(String name){
        Album album0=new Album(Album.getAlbumCounter(),name,artist.getUsername());
        Album.setAlbumCounter(Album.getAlbumCounter()+1);
        ((Singer)artist).getAlbums().add(album0);
        return "album created";
    }
}
