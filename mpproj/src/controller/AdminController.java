package controller;

import model.*;

import java.security.PublicKey;

public class AdminController {
    private static AdminController adminController;

    private AdminController() {
    }

    public static AdminController getAdminController() {
        if (adminController == null)
            adminController = new AdminController();
        return adminController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount(){
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String login(String username, String password){
        for ( UserAccount userAccount1 : Database.getDataBase().getUserAccounts()){
            if ( userAccount1.getUserName().equals(username) && userAccount1.getPassword().equals(password)){
                setUserAccount(userAccount1);
                return "Account created successfully";
            }
        }
        return "The username does not exist or the password is incorrect";
    }

    public String favoriteAudio(){
        String result = "";
        ListenerController.getListenerController().sortAudioFile("L");
        for (Audio audio : Database.getDataBase().getAudio()){
            result += audio+"\n";
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewAudios(){
        String result = "";
        for ( Audio audio : Database.getDataBase().getAudio()){
            result += audio+"\n";
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewArtists(){
        String result = "";
        for ( UserAccount userAccount1 : Database.getDataBase().getUserAccounts()){
            if ( userAccount1 instanceof Artist){
                result += userAccount1+"\n";
            }
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewReports(){
        String result = "";
        for (Report report : Database.getDataBase().getReports()){
            result += report+"\n\n";
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewInfoAccount(String username){
        String result = "";
        for ( UserAccount userAccount1 : Database.getDataBase().getUserAccounts()){
            if ( userAccount1.getUserName().equals(username)){
                result += userAccount1;
                break;
            }
        }
        if ( result.equals(""))
            result += "No account was found with the entered username";
        return result;
    }
}