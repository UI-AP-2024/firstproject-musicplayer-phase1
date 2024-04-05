package controller;

import model.*;
import view.AccountView;

import java.util.Date;

public class LoginController {
    private static LoginController loginController;

    private LoginController() {
    }

    public static LoginController getLoginController() {
        if (loginController == null)
            loginController = new LoginController();
        return loginController;
    }

    public String typeLog(String username){
        for (UserAccount userAccount : Database.getDataBase().getUserAccounts()){
            if ( userAccount.getUserName().equals(username)){
                if ( userAccount instanceof Admin)
                    return "Admin";
                else if ( userAccount instanceof Artist)
                    return "Artist";
                else if ( userAccount instanceof Listener){
                    return "Listener";
                }
            }
        }
        return "Admin";
    }
}
