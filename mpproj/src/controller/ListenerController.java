package controller;

import model.Database;
import model.Free;
import model.UserAccount;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController {
    private static ListenerController listenerController;

    private ListenerController() {
    }

    public static ListenerController getListenerController() {
        if (listenerController == null)
            listenerController = new ListenerController();
        return listenerController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    public String registration(String userName, String password, String name, String email, String number, Date birth){
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()){
            if (tmp.getUserName().equals(userName))
                return "Username is duplicated. Please try again";
        }
        String regex = "^([\\w-\\.]+[@]{1}[\\w]+[\\.]{1}[\\w]{2,4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()==false)
            return "The email entered is not valid";
        regex = "^([0]{1}[9]{1}[0-9]{9})$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(number);
        if (matcher.matches()==false)
            return "The phone number entered is not valid";
        Free free = new Free(userName,password,name,email,number,birth,50,null,null,null);
        Database.getDataBase().getUserAccounts().add(free);
        return "Account created successfully";
    }

    public String login(String userName, String password){
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()){
            if (tmp.getUserName().equals(userName) && tmp.getPassword().equals(password)){
                setUserAccount(tmp);
                return "Login successfully";
            }
        }
        return "The username does not exist or the password is incorrect";
    }
}
