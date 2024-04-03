package Controller;

import Model.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller {
    public static String findUser(String username, String password) {
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                if (user instanceof Listener) {
                    ListenerController.getListenerController().setListener((Listener) user);
                    return "Listener";
                } else if (user instanceof Admin) {
                    return "Admin";
                } else if (user instanceof Artist) {
                    ArtistController.getArtistController().setArtist((Artist) user);
                    return "Artist";
                }
            }
        }
        return "Username or password is incorrect";
    }

    public static boolean checkUserName(String userName) {
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if (user.getUserName().equals(userName)) {
                return false;
            }
        }
        return true;
    }
    public  static boolean checkEmail(String email){
        //String emailRGX="^\\w{3,20}(@)(y|g|e)(mail)\\.com$";
        String emailRGX="^(.+)@(\\S+)\\.com$";
        Pattern p1 = Pattern.compile(emailRGX);
        Matcher matcher1 = p1.matcher(email);
        return matcher1.matches();
    }
    public  static boolean checkPhoneNum(String phoneNum){
        //String phoneNumRGX="09(1[0-9]|3[1-9]|2[1-9])\\d{3}\\d{4}";
        String phoneNumRGX="09\\d{2}\\d{3}\\d{4}";
        Pattern p2 = Pattern.compile(phoneNumRGX);
        Matcher matcher2 = p2.matcher(phoneNum);
        return matcher2.matches();
    }
}
