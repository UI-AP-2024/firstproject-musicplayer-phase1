package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.audio.Genre;
import model.database.Database;
import model.user.Artist;
import model.user.FreeListener;
import model.user.Listener;
import model.user.Podcaster;
import model.user.PremiumListener;
import model.user.Singer;
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
    // private FreeListener freeListener;
    // public FreeListener getFreeListener(){
    //     return freeListener;
    // }
    // public void setFreeListener(FreeListener freeListener){//in login
    //     this.freeListener = freeListener;
    // }
    // private Listener listener;
    // public Listener getListener(){
    //     return listener;
    // }
    // public void setListener(Listener listener){//in login
    //     this.listener = listener;
    // }

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
    // public String signupNewListener(String username,String password, String firstName, String lastName, String emailAddress, String phoneNumber,
    //         int birthyear ,int birthMonth, int birthday){
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
    //     FreeListener tmp = new FreeListener(password,username, firstName, lastName, emailAddress, phoneNumber, birthDate, 50);
    //     Database.getDatabase().addToAllUsers(tmp);
    //     setListener(tmp);
    //     return "Thanks for signing up. Your account has been created";
    // }

    public void getFavoriteGenres(ArrayList<String> genres){
        for (String string : genres) {
            getListener().addToFavoriteGenres(Genre.valueOf(string));
        }  
    }
    public void loginListener(Listener listener){
        setListener(listener);
    }

    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+getListener().getUsername()+
        "\nFirst name : "+getListener().getFirstName()+
        "\nLast name : "+getListener().getLastName()+
        "\nemail address: "+getListener().getEmailAddress()+
        "\npassword : "+getListener().getPassword()+
        "\nbirth date : "+String.valueOf(getListener().getBirthDate())+
        "\nAccount Credit : "+String.valueOf(getListener().getAccountCredit());
        return txt;
    }

    public void getPremium(int days,int payment){
        if(getListener().getAccountCredit()>=payment){
            getListener().setAccountCredit(getListener().getAccountCredit()-payment);
            if(getListener() instanceof FreeListener){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expDate = cal.getTime(); 
                PremiumListener tmp = new PremiumListener(getListener().getPassword(), getListener().getUsername(), getListener().getFirstName(), getListener().getLastName(), getListener().getEmailAddress(), getListener().getPhoneNumber(), getListener().getBirthDate(), getListener().getAccountCredit(),days,expDate);
                setListener(tmp);
                //change the method with type casting?to nut remove user
                //exchanging two listeners in all felds they are in->database,artist,report
            }
            else{
                Calendar cal = Calendar.getInstance();
                Date date = getListener().getPremiumExpirationDate();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expirationDate = cal.getTime(); 
                getListener().setPremiumExpirationDate(expirationDate);
                Listener tmp = getListener();
                if(tmp instanceof PremiumListener){
                    PremiumListener tmpp = (PremiumListener)tmp;
                    tmpp.setRemainingDaysOfPremium(tmpp.getRemainingDaysOfPremium()+days);
                }
            }
        }

    }

    public String showAllArtists(){
        String txt = "All Artists\n";
        for(User user:Database.getDatabase().getAllUsers()){
            if(user instanceof Artist){
                txt+="-"+user.getUsername()+"\n";
            }
        }
        return txt;
    }

    public String showArtistInfo(String username){
        User user =UserController.getUserController().findUser(username);
        
        if(user instanceof Singer){
            SingerController.getSingerController().setSinger((Singer)user);
            String txt = SingerController.getSingerController().ShowSingerInfo();
            return txt;
        }
        if(user instanceof Podcaster){
            PodcasterController.getPodcasterController().setPodcaster((Podcaster)user);
            String txt =PodcasterController.getPodcasterController().ShowPodcasterInfo();
            return txt;
        }
           
        return null;
    }

    public String followArtist(String username){
        User user = UserController.getUserController().findUser(username);
        if(user instanceof Artist){
            ((Artist)user).addFollowers(getListener());
            return"you've followed '"+username+"' succesfullly";
        }
        else if(user == null){
            return"the username is not valid";
        }
        else{
            return "this user is not an Artist! enter an artist username for following";
        }
    }

    // public String showArtistInfo(){
    //     String txt="Artist info:"+
    //     "\nuser name : "+getListener().getUsername()+
    //     "\nFirst name : "+getListener().getFirstName()+
    //     "\nLast name : "+getListener().getLastName()+
    //     "\nFollowers : "+get
        
    //     return txt;
    // }
    
    
}
