package controller;

import model.Album;
import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Report;
import model.UserAccount.*;

import java.util.ArrayList;
import java.util.Date;

public class ArtistControler {
    Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public String signUpArtist(String username, String pasword, String name, String email, String phoneNum, Date birthDate){
        ArrayList<User> users= Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username)){
                return "error : this user name already exist .";
            }
        }
        Artist artist=new Artist(username,pasword,name,email,phoneNum,birthDate);
        setArtist(artist);
        //add to database
        artist.setIsLogin(true);
        return  "artist accaount successfully created .";
    }
    public String login(String username,String password){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(artist.getIsLogin()==true)
                    return "you are in already in your profile .";
                setArtist((Artist) user);
                artist.setIsLogin(true);
                return "login successful .";
            }
        }
        return "error : user name or password is wrong";
    }
    public String logout(){
        ArrayList<User>users=Database.getDatabase().getUsers();
        artist.setIsLogin(false);
        return "logout successfull";
    }
    public  String artistInfo(){
        return artist.toString();
    }
    public String showFollowers(){
        StringBuilder result=new StringBuilder(" followers : ");
        for(User user: artist.getFollowers()){
            result.append(user.getUsername()).append(" ");
        }
        return artist.getFollowers().size()+String.valueOf(result);
    }
    public String viewStatic(){
        StringBuilder str=new StringBuilder(" view static: \n");
        int totalplayCount=0;
        if(artist instanceof Singer){
            for(Album album:((Singer) artist).getAlbums()){
                for(Music music:album.getSongs()) {
                    str.append(" music name :").append(music.getTitle()).append(" view : ").append(music.getPlayCount());
                    totalplayCount += music.getPlayCount();
                }
            }
        }
        if(artist instanceof Podcaster){
            for(Podcast podcast:((Podcaster) artist).getPodcasts()){
                str.append(" podcast name :").append(podcast.getTitle()).append(" view : ").append(podcast.getPlayCount());
                totalplayCount+= podcast.getPlayCount();
            }
        }
        return "total view : "+ totalplayCount+String.valueOf(str);
    }
    public String calculateEarning(){
        double total=0;
        if(artist instanceof Singer){
            for(Album album:((Singer) artist).getAlbums()){
                for(Music music:album.getSongs()) {
                total+= music.getPlayCount()*0.4;
                }
            }
        }
        if(artist instanceof Podcaster){
            for(Podcast podcast:((Podcaster) artist).getPodcasts()){
                total+= podcast.getPlayCount()*0.5;
            }
        }
        return String.valueOf(total);
    }

}
