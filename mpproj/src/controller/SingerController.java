package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.database.Database;
import model.user.FreeListener;
import model.user.Listener;
import model.user.Singer;
import model.user.User;

public class SingerController {

    private static SingerController singerController;
    private SingerController(){

    }
    public static SingerController getSingerController(){
        if(singerController==null){
            singerController = new SingerController();
        }
        return singerController;
    }

    private static Singer singer;
    public static Singer getSinger() {
        return singer;
    }
    public static void setSinger(Singer singer) {
        SingerController.singer = singer;
    }
    
    // public boolean emailPassRegex(String email){
    //     Pattern pattern = Pattern.compile("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$");
    //     Matcher matcher = pattern.matcher(email);
    //     return matcher.matches();
    // }
    // public boolean phoneNumPassRegex(String phoneNumber){
    //     Pattern pattern = Pattern.compile("^09[0-9]{9}$");
    //     Matcher matcher = pattern.matcher(phoneNumber);
    //     return matcher.matches();
    // }
    // public boolean passwordPassRegex(String password){
    //     Pattern pattern = Pattern.compile("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$");
    //     Matcher matcher = pattern.matcher(password);
    //     return matcher.matches();
    // }
    // public boolean usernameIsUnic(String username){
    //     for(User user : Database.getDatabase().getAllUsers()){
    //         if(user.getUsername()==username)
    //             return false;
    //     }
    //     return true;
    // }
    // public String signupNewSinger(String username,String password, String firstName, String lastName, String emailAddress, String phoneNumber,
    //         int birthyear ,int birthMonth, int birthday,String biographi){
    //     if(!(emailPassRegex(emailAddress))){
    //         return"Please enter a valid email address";
    //     }
    //     if(!(phoneNumPassRegex(phoneNumber))){
    //         return"Please enter a valid phone Number";
    //     }
    //     if(!(passwordPassRegex(password))){
    //         return"Not a strong password!! Your password must be at least 8 characters long, contain at least one number and have a mixture of uppercase and lowercase letters.";
    //     }
    //     if(!(usernameIsUnic(username))){
    //         return"This username is alredy taken ! Please enter another one";
    //     }
    //     @SuppressWarnings("deprecation")
    //     Date birthDate = new Date(birthyear,birthMonth,birthday);
    //     Singer tmp = new Singer(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate,biographi);
    //     Database.getDatabase().addToAllUsers(tmp);
    //     setSinger(tmp);
    //     return "Thanks for signing up. Your account has been created";
    // }

    public void loginSinger(Singer singer){
        setSinger(singer);
    }

    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+getSinger().getUsername()+
        "\nFirst name : "+getSinger().getFirstName()+
        "\nLast name : "+getSinger().getLastName()+
        "\nemail address: "+getSinger().getEmailAddress()+
        "\npassword : "+getSinger().getPassword()+
        "\nbirth date : "+String.valueOf(getSinger().getBirthDate())+
        "\nIncome : "+String.valueOf(getSinger().getIncome());
        return txt;
    }

    
}
