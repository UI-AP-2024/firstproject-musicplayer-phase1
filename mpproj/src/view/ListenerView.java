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
                ListenerController.getListenerController().showArtists();
                methods();
            case "Artist" :
                ListenerController.getListenerController().showArtist(strings[1]);
                methods();
            case "Follow" :
                ListenerController.getListenerController().followArtist(strings[1]);
                methods();
            case "Search" :
                ListenerController.getListenerController().searchAudioOrArtist(strings[1]);
                methods();
            case "Sort" :
                ListenerController.getListenerController().sortAudios(strings[1]);
                methods();
            case "Filter" :
                ListenerController.getListenerController().filterAudios(strings[1], strings[2]);
                methods();
            case "Add" :
                ListenerController.getListenerController().addAudioToPlaylist(strings[1], Integer.parseInt(strings[2]));
                methods();
            case "ShowPlaylists" :
                ListenerController.getListenerController().showPlaylists();
                methods();
            case "SelectPlaylist" :
                ListenerController.getListenerController().showPlaylist(strings[1]);
                methods();
            case "Play" :
                ListenerController.getListenerController().playAudio(Integer.parseInt(strings[1]));
                methods();
            case "Like" :
                ListenerController.getListenerController().likeAudio(Integer.parseInt(strings[1]));
                methods();
            case "Lyric" :
                ListenerController.getListenerController().showLyrics(Integer.parseInt(strings[1]));
                methods();
            case "NewPlaylist" :
                ListenerController.getListenerController().newPlaylist(strings[1]);
                methods();
            case "Followings" :
                ListenerController.getListenerController().showFollowings();
                methods();
            case "Report" :
                ListenerController.getListenerController().report(strings[1], strings[2]);
                methods();
            case "IncreaseCredit" :
                ListenerController.getListenerController().increaseCredit(Double.parseDouble(strings[1]));
                methods();
            case "Logout" :
                SelectUserView.getSelectUserView().signUpLogInOut();
            default :
                System.out.println("This method is not available for you!!!");
                methods();
        }
    }
}
