package model.user;

import java.util.ArrayList;
import java.util.Date;

import model.audio.Album;
import model.audio.Music;

public class Singer extends Artist{
    private ArrayList<Album> albumList;
    public Singer(String password,String username, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate, String biographi) {
        super(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, biographi);
        albumList = new ArrayList<>();
    }

    public ArrayList<Album> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Album> albumList) {
        this.albumList = albumList;
    }
    public void addToAlbumList(Album album) {
        this.albumList.add(album);
    }

    public void calculateIncome(){
        long view =0;
        for(Album album :albumList){
            for(Music music : album.getMusicList()){
                view+=music.getNumberOfPlays();
            }
        }
        super.setIncome(view*0.4);
    }
    
    
}
