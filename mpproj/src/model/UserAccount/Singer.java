package model.UserAccount;

import model.Audio.Music;
import model.SubscriptionPlan;

import java.util.ArrayList;
import java.util.Date;

public class Singer extends Artist{
    private ArrayList<Music> musics;
    private String bio;


    public Singer(String username, String password, String fullName, String email, String phoneNumber, Date dateOfBirth, String bio ) {
        super(username, password, fullName, email, phoneNumber, dateOfBirth);
        this.musics = new ArrayList<>();
        this.bio=bio;
    }
    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public ArrayList<Music> getMusics() {
        return musics;
    }
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder("biography : ");
        res.append(bio).append(" musics : ");
        for(Music music:musics){
            res.append(music.getTitle());
        }
        return super.toString()+"\n"+String.valueOf(res);
    }
    public void setMusics(ArrayList<Music> musics) {
        this.musics = musics;
    }
}
