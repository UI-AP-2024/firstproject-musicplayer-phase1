package Controller.Accounts;

import Model.Accounts.AccountsModel;
import Model.Accounts.AdminModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Accounts.Artists.PodcasterModel;
import Model.Accounts.Artists.SingerModel;
import Model.Accounts.Listeners.FreeListenerModel;
import Model.Accounts.Listeners.ListenerModel;
import Model.Database.Database;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class AccountController {

    private static AccountController mainController;

    private AccountController(){}

    public static AccountController getMainController(){
        if(mainController == null){
            mainController = new AccountController();
        }
        return mainController;
    }


    public String signup(String userType , String username , String password , String fullName , String email , String phoneNumber , int[] birthDate , String ... bio) {
//        if(!(Pattern.matches("^(?=[a-zA-Z0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z])(?=.*\\d).{8,}$" , password)) || (Pattern.matches(".*123.*" , password) || Pattern.matches(".*321.*" , password))){
        if(!(Pattern.matches("^(?=[a-zA-Z0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[a-zA-Z])(?=.*\\d).{8,}$" , password))){
            return "\nYour Password is weak . Enter a stronger Password";
        }
        if(!(Pattern.matches("^[a-zA-Z0-9.!?#$*+_-]+@[a-z]+(?:\\.[a-z]+)+" , email))){
            return "\nEmail is invalid";
        }
        if(!(Pattern.matches("^09[\\d]{9}" , phoneNumber))){
            return "\nPhone number is invalid";
        }
        Database database = Database.getDatabase();

        for (AccountsModel account : database.getAccounts()){
            if(account.getUsername().equals(username)){
                return "\nUsername already exist";
            }
        }

        switch (userType){
            case "L" -> {
                ListenerModel listener = new FreeListenerModel(username , password , fullName , email , phoneNumber , birthDate);
                database.addAccount(listener);
                ListenerController.getListenerController().loginListener(listener);
            }
            case "S" -> {
                SingerModel singer = new SingerModel(username, password, fullName, email, phoneNumber, birthDate , bio[0]);
                database.addAccount(singer);
                ArtistsController.getArtistsController().loginArtist((singer));
            }
            case "P" -> {
                PodcasterModel podcaster = new PodcasterModel(username, password, fullName, email, phoneNumber, birthDate , bio[0]);
                database.addAccount(podcaster);
                ArtistsController.getArtistsController().loginArtist((podcaster));
            }
        }
        if(Pattern.matches("^(?=[a-zA-Z0-9])(?=.*[A-Z].*[A-Z])(?=.*[@.!#$*])(?=.*[0-9].*[0-9])(?=.*[a-z].*[a-z].*[a-z])(?=.*[a-zA-Z])(?=.*\\d).{8,}$" , password)){
            return "\nPassword strength : Strong\nAccount created successfully";
        }
        return "\nPassword strength : good\nAccount created successfully";
    }
    public String login(String username , String password){
        Database database = Database.getDatabase();
        AccountsModel userAccount = null;
        for (AccountsModel account : database.getAccounts()){
            if(account.getUsername().equals(username)){
                userAccount = account;
                break;
            }
        }

        if(userAccount == null){
            return "\nUsername does not exist";
        }

        if(!(userAccount.getPassword().equals(password))){
            return "\nPassword is not correct";
        }


        if(userAccount instanceof ListenerModel user){
            ListenerController.getListenerController().loginListener(user);
            return "Listener";
        }

        if(userAccount instanceof AdminModel admin){
            AdminController.getAdminController().loginAdmin(admin);
            return "Admin";
        }

        if(userAccount instanceof ArtistModel artist){
            ArtistsController.getArtistsController().loginArtist(artist);
            if (artist instanceof SingerModel singer){
                return "Singer";
            }
            if (artist instanceof PodcasterModel podcaster){
                return "Podcaster";
            }

        }

        return "\nSomething went wrong";
    }
}
