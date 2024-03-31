package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.database.Database;
import model.user.FreeListener;
import model.user.Listener;
import model.user.User;

public class ListenerController {
    private static ListenerController listenerController;
    private ListenerController(){

    }
    public static ListenerController getListenerController(){
        if(listenerController==null){
            listenerController = new ListenerController();
        }
        return listenerController;
    }
    private Listener listener;
    public Listener getListener(){
        return listener;
    }
    public void setListener(Listener listener){//in login
        this.listener = listener;
    }

    public boolean emailPassRegex(String email){
        Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public boolean phoneNumPassRegex(String phoneNumber){
        Pattern pattern = Pattern.compile("^09[0-9]{9}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
    public boolean passwordPassRegex(String password){
        Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    public boolean usernameIsUnic(String username){
        for(User user : Database.getDatabase().getAllUsers()){
            if(user.getUsername()==username)
                return false;
        }
        return true;
    }
    public String signupNewListener(String password,String username, String firstName, String lastName, String emailAddress, String phoneNumber,
            Date birthDate){
        if(!(emailPassRegex(emailAddress))){
            return"Please enter a valid email address";
        }
        if(!(phoneNumPassRegex(phoneNumber))){
            return"Please enter a valid phone Number";
        }
        if(!(passwordPassRegex(password))){
            return"Not a strong password!! Your password must be at least 8 characters long, contain at least one number and have a mixture of uppercase and lowercase letters.";
        }
        if(!(usernameIsUnic(username))){
            return"This username is alredy taken ! Please enter another one";
        }
        FreeListener tmp = new FreeListener(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, 50);
        Database.getDatabase().addToAllUsers(tmp);
        return "Thanks for signing up. Your account has been created";
    }
    
}
