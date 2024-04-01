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
import model.user.Report;
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
    public void loginListener(Listener listener){
        setListener(listener);
    }
 
    public void getFavoriteGenres(ArrayList<String> genres){
        for (String string : genres) {
            getListener().addToFavoriteGenres(Genre.valueOf(string));
        }  
    }

    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+getListener().getUsername()+
        "\nFirst name : "+getListener().getName()+
        "\nemail address: "+getListener().getEmailAddress()+
        "\npassword : "+getListener().getPassword()+
        "\nbirth date : "+String.valueOf(getListener().getBirthDate())+
        "\nAccount Credit : "+String.valueOf(getListener().getAccountCredit())+"\n";
        if(getListener() instanceof PremiumListener){
            txt+="Premium Expiration Date : "+String.valueOf(((PremiumListener)getListener()).getPremiumExpirationDate());
        }
        return txt;
    }

    public void getPremium(int days,int payment){
        if(getListener().getAccountCredit()>=payment){
            getListener().setAccountCredit(getListener().getAccountCredit()-payment);
            if(getListener() instanceof FreeListener){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expDate = cal.getTime(); 
                PremiumListener tmp = new PremiumListener(getListener().getPassword(), getListener().getUsername(), getListener().getName(), getListener().getEmailAddress(), getListener().getPhoneNumber(), getListener().getBirthDate(), getListener().getAccountCredit(),days,expDate);
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
            getListener().setNumberOfFollowing(getListener().getNumberOfFollowing()+1);
            return"you've followed '"+username+"' succesfullly";
        }
        else if(user == null){
            return"the username is not valid";
        }
        else{
            return "this user is not an Artist! enter an artist username for following";
        }
    }

    public String increaseAccountCredit(double amount){
        getListener().setAccountCredit(getListener().getAccountCredit()+amount);
        return "the amount added to your credit";
    }

    public String ShowFollowings(){
        String txt="All Followings\n";
        if(getListener().getNumberOfFollowing()==0){
            txt+="you have no following yet";
            return txt;
        }
        for(User user : Database.getDatabase().getAllUsers()){
            if(user instanceof Artist){
                Artist artist = (Artist)user;
                for(User listenerp : artist.getFollowers()){
                    if(listenerp.getUsername().equals(getListener().getUsername())){
                        txt+="-"+artist.getUsername()+"\n";
                    }

                }
            }
        }
        return txt;
    }

    public String reportArtist(String username , String explanation){
        User user = UserController.getUserController().findUser(username);
        if(user instanceof Artist){
            Report tmp = new Report(getListener(), null, explanation);
            Database.getDatabase().addToAllReports(tmp);
            return "thanks for your feedback , your report has been recieved!";
        }
        else{
            return "invalid username for an Artist ! make sure to enter an Artist's username ";
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
