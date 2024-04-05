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

    private Admin userAccount;

    public Admin getUserAccount(){
        return userAccount;
    }

    public void setUserAccount(Admin userAccount) {
        this.userAccount = userAccount;
    }

    public String login(String username, String password){
        for ( UserAccount userAccount1 : Database.getDataBase().getUserAccounts()){
            if ( userAccount1.getUserName().equals(username) && userAccount1.getPassword().equals(password)){
                setUserAccount((Admin) userAccount1);
                return "Login successfully";
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

    public String viewInfoArtist(String username){
        String result = "";
        for ( UserAccount userAccount1 : Database.getDataBase().getUserAccounts()){
            if ( userAccount1.getUserName().equals(username) && userAccount1 instanceof Artist){
                result += (Artist)userAccount1;
                break;
            }
        }
        if ( result.equals(""))
            result += "No Artist was found with the entered username";
        return result;
    }

    public String viewAdminInfo(){
        String result = "";        result += (Admin)getUserAccount();
        return result;
    }

    public String viewAudio(String id){
        String result = "";
        for ( Audio audio : Database.getDataBase().getAudio()){
            if ( audio.getId() == Integer.valueOf(id)){
                if ( audio instanceof Music) {
                    result += (Music) audio;
                    break;
                }
                else if ( audio instanceof Podcast) {
                    result += (Podcast) audio;
                    break;
                }
            }
        }
        if ( result.equals(""))
            return "The entered ID is not valid";
        else
            return result;
    }
}