package controller;

import model.Database;
import model.UserAccount;

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































}