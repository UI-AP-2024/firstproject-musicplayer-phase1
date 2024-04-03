package Model;

import java.util.ArrayList;

public class Singer extends Artist {
    private ArrayList<Album> albumsList = new ArrayList<>();

    public Singer(String userName, String password, String firstAndLastName, String email, String phoneNumber, int year, int month, int day, String biography) {
        super(userName, password, firstAndLastName, email, phoneNumber, year, month, day, biography);
    }

    public ArrayList<Album> getAlbumsList() {
        return albumsList;
    }

    public void setAlbumsList(ArrayList<Album> albumsList) {
        this.albumsList = albumsList;
    }
    @Override
    public String toString(){
        return super.toString();
    }
}
