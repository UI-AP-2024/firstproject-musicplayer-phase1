package Controller;

import Model.*;

import java.util.Date;

public class SingerController extends ArtistController {
    private SingerModel model;

    public void newAlbum(String albumName){
        model.getAlbums().add(new Album(albumName, model.getFullName()));
    }
    public void publishMusic(String audioName, Genre genre,String lyric,String link,String cover,int albumID){
        for (int i = 0; i < model.getAlbums().size(); i++) {
            if(model.getAlbums().get(i).getIDCount()==albumID){
                Music music = new Music(audioName, model.getFullName(),genre,link,cover,lyric);
                model.getAlbums().get(i).getMusics().add(music);
            }
        }
    }
}
