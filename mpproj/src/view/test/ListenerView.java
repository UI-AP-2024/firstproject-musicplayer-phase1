package view.test;

import java.text.ParseException;
import java.util.Scanner;

import controller.ArtistController;
import controller.AudioController;
import controller.ListenerController;
import model.user.PremuimAcc;

public class ListenerView {
    private static ListenerView listenerView;
    private ListenerView(){

    }
    public static ListenerView getListenerView(){
        if(listenerView==null){
            listenerView = new ListenerView();
        }
        return listenerView;
    }

    public void ListenerView(String[] spltCmd) throws ParseException{
        String txt;
        switch (spltCmd[0]) {
            case "AccountInfo":
            txt = ListenerController.getListenerController().ShowAccountInfo();
            System.out.println(txt);
                
                break;
            case "Artists":
            txt = ListenerController.getListenerController().showAllArtists();
            System.out.println(txt);
                
                break;
            case "Artist":
            txt = ListenerController.getListenerController().showArtistInfo(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Follow":
            txt = ListenerController.getListenerController().followArtist(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Search":
            txt = AudioController.getAudioController().searchAudio(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Sort":
            txt = AudioController.getAudioController().sortAudio(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Filter":
            if(spltCmd.length==3){
                txt = AudioController.getAudioController().filterAudio(spltCmd[1], spltCmd[2]);
                System.out.println(txt);
            }
            if(spltCmd.length==4){
                txt = AudioController.getAudioController().filterByDate(spltCmd[2], spltCmd[3]);
                System.out.println(txt);
            }
                
                break;
            case "Add":
            txt = ListenerController.getListenerController().addAudioToPlaylist(spltCmd[1], Long.parseLong(spltCmd[2]));
            System.out.println(txt);
                
                break;
            case "ShowPlaylists":
            txt = ListenerController.getListenerController().showPlaylists();
            System.out.println(txt);
                
                break;
            case "SelectPlaylist":
            txt = ListenerController.getListenerController().selectPlaylist(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "NewPlaylist":
            txt = ListenerController.getListenerController().createNewPlaylist(spltCmd[1]);
            System.out.println(txt);
                
                break;
            case "Followings":
            txt = ListenerController.getListenerController().ShowFollowings();
            System.out.println(txt);
                
                break;
        
            case "Report":
            txt = ListenerController.getListenerController().reportArtist(spltCmd[1], spltCmd[2]);
            System.out.println(txt);
                
                break;
        
            case "IncreaseCredit":
            txt = ListenerController.getListenerController().increaseAccountCredit(Double.parseDouble(spltCmd[1]));
            System.out.println(txt);
                
                break;
        
            case "GetPremium":
            int packag=Integer.parseInt(spltCmd[1]);

            switch (packag) {
            case 30:
            txt = ListenerController.getListenerController().getPremium(30,PremuimAcc.ONE_MONTH.getMoney());
            System.out.println(txt);
                break;
            case 60:
            txt = ListenerController.getListenerController().getPremium(60,PremuimAcc.TWO_MONTH.getMoney());
            System.out.println(txt);
                break;
            case 180:
            txt = ListenerController.getListenerController().getPremium(180,PremuimAcc.THREE_MONTH.getMoney());
            System.out.println(txt);
                break;
            default:
                break;
            }
                
                break;
        
            case "Play":
            txt= ListenerController.getListenerController().addAudioPlay(Long.parseLong(spltCmd[1]));
            System.out.println(txt);
                
                break;
        
            case "Like":
            txt= ListenerController.getListenerController().likeAudio(Long.parseLong(spltCmd[1]));
            System.out.println(txt);
                
                break;
        
            case "Lyric":
            txt = AudioController.getAudioController().showLyric(Long.parseLong(spltCmd[1]));
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