package Controller;

import Model.*;

import java.util.Date;

public class PodcasterController extends ArtistController {
    private PodcasterModel model;
    public PodcasterController(String username, String password, String fullName, String email, String phoneNumber, Date birthDate, String biography) {
        super(username, password, fullName, email, phoneNumber, birthDate, biography);
    }
    public void publishPodcast(String audioName, Genre genre, String caption, String link, String cover){
        Podcast podcast = new Podcast(audioName,model.getFullName(),genre,link,cover,caption);
       model.getPodcasts().add(podcast);
    }
}
