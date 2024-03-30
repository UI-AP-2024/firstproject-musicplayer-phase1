package controller;

import model.Database.Database;
import model.Report;
import model.UserAccount.Artist;
import model.UserAccount.Listener;
import model.UserAccount.User;

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

    public String signUpListener(String username, String pasword, String name, String email, String phoneNum, Date birthDate){
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


}
