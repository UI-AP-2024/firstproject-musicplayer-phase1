package controller;

import model.database.Database;
import model.user.User;

public class UserController {
    private static UserController userController;
    private UserController(){

    }
    public static UserController getUserController(){
        if(userController==null){
            userController = new UserController();
        }
        return userController;
    }
    public User findUser(String username,String password){
        for(User user : Database.getDatabase().getAllUsers()){
            if(user.getUsername().equals(username)){
                if(user.getPassword().equals(password)){
                    return user;
                }
            }
        }
        return null;
    }
}