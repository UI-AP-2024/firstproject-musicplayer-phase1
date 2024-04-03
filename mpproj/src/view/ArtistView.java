package view;

import java.text.ParseException;
import java.util.Scanner;
import controller.ArtistController;
import controller.AudioController;
import controller.ListenerController;
import model.user.PremuimAcc;

public class ArtistView {
    private static ArtistView artistView;
    private ArtistView(){

    }
    public static ArtistView getArtistView(){
        if(artistView==null){
            artistView = new ArtistView();
        }
        return artistView;
    }

    public void ArtistView() throws ParseException{
        Scanner sc = new Scanner(System.in);
        String command;
        String txt;
        int a =10;
        // String type=null;
        while (a!=0) {
            command = sc.nextLine();
            String [] spltCmd = command.split("-");
            switch (spltCmd[0]) {
                case "AccountInfo":
                MainView.getMainView().mainView();
                    
                    break;
                case "Followers":
                txt = ArtistController.getArtistController().showFollowers();
                System.out.println(txt);                    
                    break;
                case "ViewsStatistics":
                txt = ArtistController.getArtistController().showViewsStatics();
                System.out.println(txt);
                    
                    break;
                case "CalculateEarnings":
                ArtistController.getArtistController().calculateIncome();
                    
                    break;
                case "NewAlbum":
                txt = ArtistController.getArtistController().createNewAlbum(spltCmd[1]);
                System.out.println(txt);
                    
                    break;
                case "Publish":
                txt = ArtistController.getArtistController().publishAudio(spltCmd[1],spltCmd[2],spltCmd[3],spltCmd[4],spltCmd[5],spltCmd[6],Long.parseLong(spltCmd[1]));
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
