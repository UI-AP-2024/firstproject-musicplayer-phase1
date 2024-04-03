package view.trash;

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

    public void AdminView() throws ParseException{
        Scanner sc = new Scanner(System.in);
        String command;
        String txt;
        int a =10;
        // String type=null;
        while (true) {
            command = sc.nextLine();
            String [] spltCmd = command.split("-");
            switch (spltCmd[0]) {
                case "AccountInfo":
                AdminController.getAdminController().ShowAccountInfo();
                    
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
}
