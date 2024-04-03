package controller;

import model.*;

import java.security.PublicKey;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ArtistController {
    private static ArtistController artistController;

    public ArtistController() {
    }

    public static ArtistController getArtistController(){
        if ( artistController == null)
            artistController = new ArtistController();
        return artistController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String viewFollowers(){
        String result = "";
        if ( getUserAccount() instanceof Artist){
            for ( UserAccount userAccount1 : ((Artist) getUserAccount()).getListFollowers()){
                result += userAccount1+"\n";
            }
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewNumberPlay(){
        String result = "";
        if ( getUserAccount() instanceof Podcaster){
            int count=0;
            for ( Podcast podcast : ((Podcaster) getUserAccount()).getPodcastList()){
                result += "Audio Id: "+podcast.getId()+"\tAudio Name: "+podcast.getName()+"\tplay: "+podcast.getNumberOfPlays()+"\n";
                count += podcast.getNumberOfPlays();
            }
            if ( result.equals(""))
                result += "empty";
            else
                result += "Total number of plays: "+String.valueOf(count);
            return result;
        }
        else if ( getUserAccount() instanceof Singer){
            int count=0;
            for ( Album album : ((Singer) getUserAccount()).getAlbumList() ){
                for ( Music music : album.getMusicList()) {
                    result += "Audio Id: " + music.getId() + "\tAudio Name: " + music.getName() + "\tplay: " + music.getNumberOfPlays() + "\n";
                    count += music.getNumberOfPlays();
                }
            }
            if ( result.equals(""))
                result += "empty";
            else
                result += "Total number of plays: "+String.valueOf(count);
            return result;
        }
        return "ERORR viewNumberPlay";
    }

    public String registration(String type,String userName, String password, String name, String email, String number/*, Date birth*/,String year,String month,String day,String bio) {
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()) {
            if (tmp.getUserName().equals(userName))
                return "Username is duplicated. Please try again";
        }
        String regex = "^([\\w-\\.]+[@]{1}[\\w]+[\\.]{1}[\\w]{2,4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == false)
            return "The email entered is not valid";
        regex = "^([0]{1}[9]{1}[0-9]{9})$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(number);
        if (matcher.matches() == false)
            return "The phone number entered is not valid";

        Date birth = new Date(Integer.valueOf(year)-1900,Integer.valueOf(month)-1,Integer.valueOf(day));

        if ( type.equals("S")){
            Singer singer = new Singer(userName,password,name,email,number,birth,bio);
            Database.getDataBase().getUserAccounts().add(singer);
        }
        else{
            Podcaster podcaster = new Podcaster(userName,password,name,email,number,birth,bio);
            Database.getDataBase().getUserAccounts().add(podcaster);
        }
        return "Account created successfully";
    }

    public String login(String userName, String password) {
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()) {
            if (tmp.getUserName().equals(userName) && tmp.getPassword().equals(password)) {
                setUserAccount(tmp);
                return "Login successfully";
            }
        }
        return "The username does not exist or the password is incorrect";
    }

    public String viewInfoacc(){
        calculateEarnings();
        String result = "";
        if ( getUserAccount() instanceof Singer)
            result += (Singer)getUserAccount();
        else
            result += (Podcaster)getUserAccount();
        return result;
    }

    public String calculateEarnings(){
        double count=0;
        if ( getUserAccount() instanceof Singer){
            for ( Album album : ((Singer) getUserAccount()).getAlbumList()){
                for ( Music music : album.getMusicList()){
                    count+=music.getNumberOfPlays();
                }
            }
            count = count*0.4;
            ((Singer) getUserAccount()).setIncome( ((Singer) getUserAccount()).getIncome()+count );
        }
        else{
            for ( Podcast podcast : ((Podcaster)getUserAccount()).getPodcastList()){
                count += podcast.getNumberOfPlays();
            }
        }
        count = count * 0.5;
        ((Podcaster) getUserAccount()).setIncome( ((Podcaster) getUserAccount()).getIncome()+count );
        return "Your income is $"+String.valueOf(count);
    }
}
