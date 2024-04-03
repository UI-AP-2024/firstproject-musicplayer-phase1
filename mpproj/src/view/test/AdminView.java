package view.test;

import java.text.ParseException;
import java.util.Scanner;

import controller.AdminController;
import controller.ArtistController;
import controller.AudioController;
import controller.ListenerController;
import model.user.PremuimAcc;

public class AdminView {
    private static AdminView adminView;
    private AdminView(){

    }
    public static AdminView getAdminView(){
        if(adminView==null){
            adminView = new AdminView();
        }
        return adminView;
    }

    public void AdminView(String [] spltCmd) throws ParseException{
        String txt;
        switch (spltCmd[0]) {
            case "AccountInfo":
            txt = AdminController.getAdminController().ShowAccountInfo();
            System.out.println(txt);   
                break;
            case "Reports":
            txt = AdminController.getAdminController().reports();
            System.out.println(txt);                    
                break;
            case "Statistics":
            txt =AdminController.getAdminController().popularAudios();
            System.out.println(txt);
                
                break;
            case "Audios":
            ArtistController.getArtistController().calculateIncome();
                
                break;
            case "Audio":
            txt = ArtistController.getArtistController().createNewAlbum(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Artists":
            txt = ArtistController.getArtistController().createNewAlbum(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Artist":
            txt = ArtistController.getArtistController().createNewAlbum(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Logout":
            MainView.getMainView().mainView();
                
                break;
        

            default:
                break;
        }
    }
}