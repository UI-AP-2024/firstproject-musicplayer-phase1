package model.UserAccounts.Artist;

import model.Album.Album;
import model.Audio.Podcast;
import model.UserAccounts.userAccount;

import java.util.ArrayList;
import java.util.Date;

public class Singer extends Artist{
    private ArrayList<Album> albumsList;

    public Singer(String userId,String password, String fullName, String email, String phoneNumber, Date birthday, double income, ArrayList<userAccount> followersList, String biograghy,ArrayList<Album> albumsList) {
        super(userId,password, fullName, email, phoneNumber, birthday, income, followersList, biograghy);
        this.albumsList = albumsList;
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\n");
        for(Album album:albumsList){
            context.append(album.toString());
        }
        return context.toString();
    }
    public ArrayList<Album> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }
}
