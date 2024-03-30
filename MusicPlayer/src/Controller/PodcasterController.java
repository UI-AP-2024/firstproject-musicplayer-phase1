package Controller;

import Model.*;

import java.util.Date;

public class PodcasterController extends ArtistModel {
    private PodcasterModel model;
    public PodcasterController(String username, String password, String fullName, String email, String phoneNumber, Date birthDate, double salary, String biography) {
        super(username, password, fullName, email, phoneNumber, birthDate, salary, biography);
    }
    public void publishPodcast(String audioName, Genre genre, String lyric, String link, String cover){
        Podcast podcast = new Podcast(audioName,model.getFullName(),genre,link,cover,lyric);
       model.getPodcasts().add(podcast);
    }
}
