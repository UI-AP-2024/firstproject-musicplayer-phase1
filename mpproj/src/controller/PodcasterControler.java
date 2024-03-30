package controller;

import model.Database.Database;
import model.UserAccount.Podcaster;
import model.UserAccount.RegularListener;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class PodcasterControler {
    public String signUpPodcaster(String username, String pasword, String name, String email, String phoneNum, Date birthDate){
        ArrayList<User> users= Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername()==username){
                return "error : this user name already exist .";
            }
        }
        Podcaster podcaster=new Podcaster(username,pasword,name,email,phoneNum,birthDate);
        return "Podcasteer accaount successfully created .";
    }
}
