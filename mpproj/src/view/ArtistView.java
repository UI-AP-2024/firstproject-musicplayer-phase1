package view;

import controller.ArtistController;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;

import java.util.Scanner;

public class ArtistView {
    private ArtistModel artist;

    public ArtistModel getArtist() {
        return artist;
    }

    public void setArtist(ArtistModel artist) {
        this.artist = artist;
    }
    private static ArtistView artistView;

    public static ArtistView getArtistView() {
        if (artistView == null)
            artistView = new ArtistView();
        return artistView;
    }
    Scanner scanner = new Scanner(System.in);
    public void methods() {
        String string = scanner.nextLine();
        String[] strings = string.split(" -");
        switch (strings[0]) {
            case "Followers" :
                System.out.println(ArtistController.getArtistController().showFollowers());
                methods();
            case "ViewStatistics" :
                System.out.println(ArtistController.getArtistController().viewsStatistics());
                methods();
            case "CalculateEarnings" :
                System.out.println(ArtistController.getArtistController().calculateEarnings());
                methods();
            case "NewAlbum" :
                System.out.println(ArtistController.getArtistController().newAlbum(strings[1]));
                methods();
            case "AccountInfo" :
                System.out.println(ArtistController.getArtistController().showAccountInfo());
                methods();
            case "Publish" :
                switch (strings[1]) {
                    case "M" :
                        System.out.println(ArtistController.getArtistController().publishMusic(strings[2], strings[3], strings[4], strings[5], strings[6], Integer.parseInt(strings[7])));
                        methods();
                    case "P" :
                        System.out.println(ArtistController.getArtistController().publishPodcast(strings[2], strings[3], strings[4], strings[5], strings[6]));
                        methods();
                }
            case "Logout" :
                System.out.println("Logged out");
                SelectUserView.getSelectUserView().signUpLogInOut();
            default :
                System.out.println("This method is not available for you!!!");
                methods();
        }
    }
}
