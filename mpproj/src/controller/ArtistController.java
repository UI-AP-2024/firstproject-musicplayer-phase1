package controller;

import model.Artist;
import model.UserAccount;

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
}
