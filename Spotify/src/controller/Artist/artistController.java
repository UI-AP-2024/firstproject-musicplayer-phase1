package controller.Artist;

import model.Database.Database;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Artist.Podcaster;
import model.UserAccounts.Artist.Singer;
import model.UserAccounts.Listener.Free;
import model.UserAccounts.Listener.Premium;
import model.UserAccounts.userAccount;

import java.util.Date;
import java.util.regex.Pattern;

public class artistController  {
    private static artistController artistC;

    private Artist artistM;
    private artistController(){}

    public static artistController getArtistC() {
        if (artistC==null){
            artistC=new artistController();
        }
        return artistC;
    }

    public Artist getArtistM() {
        return artistM;
    }

    public void setArtistM(Artist artistM) {
        this.artistM = artistM;
    }

    // register artist
    public String registerListener(String userId,String type, String password, String fullName, String email, String phoneNumber, Date birthday,String bio){
        for(model.UserAccounts.userAccount userAccount: Database.getDatabase().getAllUsersList()){
            if (userAccount.getUserId().equals(userId)){
                return "this user name has already exist!";
            }
        }
        Pattern phoneRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneRegex.matcher(phoneNumber).matches()){
            return "phone number is not true!";
        }
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches()){
            return "email is not true";
        }
        if (type.equals("S")){
            artistM = new Singer(userId,password,fullName,email,phoneNumber,birthday,bio);
        }
        else if (type.equals("P")){
            artistM = new Singer(userId,password,fullName,email,phoneNumber,birthday,bio);
        }
        Database.getDatabase().getAllUsersList().add(artistM);
        return "Registering was successful!!!";
    }

    // login artist
    public boolean loginListener(String userId,String password){
        for(userAccount userAccount:Database.getDatabase().getAllUsersList()){
            if (userAccount.getUserId().equals(userId)&&userAccount.getPassword().equals(password)){
                if (userAccount instanceof Singer){
                    artistM = (Singer) userAccount;
                }
                else if (userAccount instanceof Podcaster){
                    artistM = (Podcaster) userAccount;
                }
                return true;
            }
        }
        return false;
    }

    ///

}
