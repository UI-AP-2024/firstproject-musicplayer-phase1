package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.database.Database;
import model.user.FreeListener;
import model.user.Podcaster;
import model.user.Singer;
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
    public String signupNewUser(String username,String password, String firstName, String lastName, String emailAddress, String phoneNumber,
            int birthyear ,int birthMonth, int birthday,char type,String bio){
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
        @SuppressWarnings("deprecation")
        Date birthDate = new Date(birthyear,birthMonth,birthday);
        switch (type) {
            case 'L':
            System.out.println("L");
            FreeListener listener = new FreeListener(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, 50);
            Database.getDatabase().addToAllUsers(listener);
                
                break;
        
            case 'S':
            System.out.println("S");
            Singer singer = new Singer(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate,bio);
            Database.getDatabase().addToAllUsers(singer);
                break;
        
            case 'P':
            Podcaster podcaster = new Podcaster(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate,bio);
            Database.getDatabase().addToAllUsers(podcaster);
                break;
        
            default:
                break;
        }
        return "Thanks for signing up. Your account has been created";
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