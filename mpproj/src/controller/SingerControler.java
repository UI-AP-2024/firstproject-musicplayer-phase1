package controller;

import model.Database.Database;
import model.UserAccount.RegularListener;
import model.UserAccount.Singer;
import model.UserAccount.User;

import java.util.ArrayList;
import java.util.Date;

public class SingerControler {
    public String signUpSinger(String username, String pasword, String name, String email, String phoneNum, Date birthDate,String bio){
        ArrayList<User> users= Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername()==username){
                return "error : this user name already exist .";
            }
        }
        Singer singer=new Singer(username,pasword,name,email,phoneNum,birthDate,bio);
        return "singer accaount successfully created .";
    }
}
