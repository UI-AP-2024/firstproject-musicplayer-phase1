package controller;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.audio.Album;
import model.audio.Music;
import model.audio.Podcast;
import model.database.Database;
import model.user.FreeListener;
import model.user.Podcaster;
import model.user.User;

public class PodcasterController {

    private static PodcasterController podcasterController;
    private PodcasterController(){

    }
    public static PodcasterController getPodcasterController(){
        if(podcasterController==null){
            podcasterController = new PodcasterController();
        }
        return podcasterController;
    }

    private static Podcaster podcaster;
    public static Podcaster getPodcaster() {
        return podcaster;
    }
    public static void setPodcaster(Podcaster podcaster) {
        PodcasterController.podcaster = podcaster;
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
    // public String signupNewPodcaster(String username,String password, String firstName, String lastName, String emailAddress, String phoneNumber,
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
    //     Podcaster tmp = new Podcaster(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate,biographi);
    //     Database.getDatabase().addToAllUsers(tmp);
    //     setPodcaster(tmp);
    //     return "Thanks for signing up. Your account has been created";
    // }

    public void loginPodcaster(Podcaster podcaster){
        setPodcaster(podcaster);
    }

    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+getPodcaster().getUsername()+
        "\nFirst name : "+getPodcaster().getFirstName()+
        "\nLast name : "+getPodcaster().getLastName()+
        "\nemail address: "+getPodcaster().getEmailAddress()+
        "\npassword : "+getPodcaster().getPassword()+
        "\nbirth date : "+String.valueOf(getPodcaster().getBirthDate())+
        "\nIncome : "+String.valueOf(getPodcaster().getIncome());
        return txt;
    }

    public String ShowPodcasterInfo(){
        String txt="Podcaster info:"+
        "\nuser name : "+getPodcaster().getUsername()+
        "\nFirst name : "+getPodcaster().getFirstName()+
        "\nLast name : "+getPodcaster().getLastName()+
        "\nFollowers : "+String.valueOf(getPodcaster().getFollowers().size())+
        "\nBiographi : "+getPodcaster().getBiographi()+"\n";
        if(getPodcaster().getPodcastList().size()==0){
            txt+="No Podcast found!!";
            return txt;
        }
        for(Podcast podcast : getPodcaster().getPodcastList()){
            txt+=podcast.getAudioName()+"\n";
            
        }
        
        return txt;
    }


    
}
