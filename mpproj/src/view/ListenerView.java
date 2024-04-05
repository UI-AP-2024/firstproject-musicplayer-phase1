package view;

import controller.ListenerController;
import model.UserAccount.Listener.ListenerModel;

import java.util.Scanner;

public class ListenerView {
    private ListenerModel listener;

    public ListenerModel getListener() {
        return listener;
    }

    public void setListener(ListenerModel listener) {
        this.listener = listener;
    }
    private static ListenerView listenerView;

    public static ListenerView getListenerView() {
        if (listenerView == null)
            listenerView = new ListenerView();
        return listenerView;
    }

    Scanner scanner = new Scanner(System.in);
    public void methods() {
        String string = scanner.nextLine();
        String[] strings = string.split(" -");
        switch (strings[0]) {
            case "Artists":
                System.out.println(ListenerController.getListenerController().showArtists());
                methods();
            case "Artist" :
                System.out.println(ListenerController.getListenerController().showArtist(strings[1]));
                methods();
            case "Follow" :
                System.out.println(ListenerController.getListenerController().followArtist(strings[1]));
                methods();
            case "Search" :
                System.out.println(ListenerController.getListenerController().searchAudioOrArtist(strings[1]));
                methods();
            case "Sort" :
                System.out.println(ListenerController.getListenerController().sortAudios(strings[1]));
                methods();
            case "Filter" :
                System.out.println(ListenerController.getListenerController().filterAudios(strings[1], strings[2]));
                methods();
            case "Add" :
                System.out.println(ListenerController.getListenerController().addAudioToPlaylist(strings[1], Integer.parseInt(strings[2])));
                methods();
            case "ShowPlaylists" :
                System.out.println(ListenerController.getListenerController().showPlaylists());
                methods();
            case "SelectPlaylist" :
                System.out.println(ListenerController.getListenerController().showPlaylist(strings[1]));
                methods();
            case "Play" :
                System.out.println(ListenerController.getListenerController().playAudio(Integer.parseInt(strings[1])));
                methods();
            case "Like" :
                System.out.println(ListenerController.getListenerController().likeAudio(Integer.parseInt(strings[1])));
                methods();
            case "AccountInfo" :
                System.out.println(ListenerController.getListenerController().showAccountInfo());
                methods();
            case "Lyric" :
                System.out.println(ListenerController.getListenerController().showLyrics(Integer.parseInt(strings[1])));
                methods();
            case "NewPlaylist" :
                System.out.println(ListenerController.getListenerController().newPlaylist(strings[1]));
                methods();
            case "Followings" :
                System.out.println(ListenerController.getListenerController().showFollowings());
                methods();
            case "Report" :
                System.out.println(ListenerController.getListenerController().report(strings[1], strings[2]));
                methods();
            case "GetPremium" :
                System.out.println(ListenerController.getListenerController().getPremiumPackage(Integer.parseInt(strings[1])));
                methods();
            case "IncreaseCredit" :
                System.out.println(ListenerController.getListenerController().increaseCredit(Double.parseDouble(strings[1])));
                methods();
            case "Logout" :
                System.out.println("Logged out");
                SelectUserView.getSelectUserView().signUpLogInOut();
            default :
                System.out.println("This method is not available for you!!!");
                methods();
        }
    }
}
