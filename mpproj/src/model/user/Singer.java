package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.audio.Album;

public class Singer extends Artist{
    private ArrayList<Album> albumList;
    public Singer(String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password, firstName, lastName, emailAddress, phoneNumber, birthDate, biographi);
        albumList = new ArrayList<>();
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    // public void setPodcastList(ArrayList<Podcast> podcastList) {
    //     this.podcastList = podcastList;
    // }
    public void setAlbumList(Album album) {
        this.albumList.add(album);
    }
    
    
}
