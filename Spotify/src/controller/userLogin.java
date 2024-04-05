package controller;

import controller.Admin.adminController;
import controller.Artist.artistController;
import controller.Listener.listenerController;
import model.Database.Database;
import model.UserAccounts.Admin.Admin;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Listener.Listener;
import model.UserAccounts.userAccount;

public class userLogin {
    private static userLogin user;
    private userLogin(){
    }

    public static userLogin getUser() {
        if (user==null){
            user = new userLogin();
        }
        return user;
    }
    public String checkType(String userId,String password){
        for (userAccount useracc :Database.getDatabase().getAllUsersList()){
            if (useracc.getUserId().equals(userId)&&useracc.getPassword().equals(password)){
                if (useracc instanceof Listener){
                    listenerController.getListenerC().loginListener(useracc);
                    return "Listener";
                }
                else if (useracc instanceof Artist){
                    artistController.getArtistC().loginArtist(useracc);
                    return "Artist";
                }
                else if (useracc instanceof Admin){
                    adminController.getAdminC().loginAdmin(useracc);
                    return "Admin";
                }
            }
        }
        return "0";
    }
}
