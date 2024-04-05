package view;

import controller.ListenerController;
import controller.LoginController;

import java.util.Scanner;

public class ListenerView {
    private static ListenerView listenerView;

    private ListenerView() {
    }

    public static ListenerView getListenerView() {
        if (listenerView == null)
            listenerView = new ListenerView();
        return listenerView;
    }

    Scanner sc = new Scanner(System.in);

    public void showMenu(){
        String txt = sc.nextLine();
        String[] answers = txt.split(" -");
        switch (answers[0]){
            case "GetSuggestions" -> {
                System.out.println(ListenerController.getListenerController().suggestions());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Artists" ->{
                System.out.println(ListenerController.getListenerController().viewListArtist());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Artist" ->{
                System.out.println(ListenerController.getListenerController().viewInfoArtist(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Follow" ->{
                System.out.println(ListenerController.getListenerController().followArtist(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Search" ->{
                System.out.println(ListenerController.getListenerController().searchAudioFile(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Sort" ->{
                System.out.println(ListenerController.getListenerController().sortAudioFile(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Filter" ->{
                System.out.println(ListenerController.getListenerController().filterAudioFile(answers[1],answers[2]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Add" ->{
                System.out.println(ListenerController.getListenerController().addAudioToPlaylist(answers[1],Integer.valueOf(answers[2])));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "ShowPlaylists" ->{
                System.out.println(ListenerController.getListenerController().viewPLaylists());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "SelectPlaylist" ->{
                System.out.println(ListenerController.getListenerController().selectPlaylist(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Play" ->{
                System.out.println(ListenerController.getListenerController().playAudioFile(Integer.valueOf(answers[1])));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Like" ->{
                System.out.println(ListenerController.getListenerController().likeAudioFile(Integer.valueOf(answers[1])));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "NewPlaylist" ->{
                System.out.println(ListenerController.getListenerController().createPlaylist(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Followings" ->{
                System.out.println(ListenerController.getListenerController().viewFollowing());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Report" ->{
                System.out.println(ListenerController.getListenerController().reportArtist(answers[1],answers[2]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "IncreaseCredit" ->{
                System.out.println(ListenerController.getListenerController().increaseCredit(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "GetPremium" ->{
                System.out.println(ListenerController.getListenerController().getPremium(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }

            case "ShowPremium" ->{
                System.out.println(ListenerController.getListenerController().showPremium());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }

            case "AccountInfo" ->{
                System.out.println(ListenerController.getListenerController().accountInfo());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Logout" ->{
                System.out.println("You are logged out of your account");
                System.out.println("END-------------------------------------------------");
                ListenerController.getListenerController().setUserAccount(null);
                AccountView.getAccountView().showFirstMenu();
            }
            case "Lyric" ->{
                System.out.println(ListenerController.getListenerController().lyricAudio(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            default -> {
                System.out.println("The command entered is not valid");
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
        }
    }
}
