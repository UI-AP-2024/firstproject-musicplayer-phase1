package view;

import controller.AdminController;
import controller.ArtistController;
import controller.PodcasterController;
import controller.SingerController;

import java.util.Scanner;

public class ArtistView {
    private static ArtistView artistView;

    private ArtistView() {
    }

    public static ArtistView getAdminView() {
        if (artistView == null)
            artistView = new ArtistView();
        return artistView;
    }

    Scanner sc = new Scanner(System.in);

    public void showMenu() {
        String txt = sc.nextLine();
        String[] answers = txt.split(" -");
        switch (answers[0]) {
            case "Followers" -> {
                System.out.println(ArtistController.getArtistController().viewFollowers());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "CalculateEarnings" -> {
                System.out.println(ArtistController.getArtistController().calculateEarnings());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "NewAlbum" -> {
                System.out.println(SingerController.getSingerController().newAlbum(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Publish" -> {
                if ( answers[1].equals("M")){
                    System.out.println(SingerController.getSingerController().publishMusic(answers[2],answers[3],answers[4],answers[5],answers[6],Integer.valueOf(answers[7])));
                    System.out.println("END-------------------------------------------------");
                    showMenu();
                }
                else if ( answers[1].equals("P")){
                    System.out.println(PodcasterController.getPodcasterController().publishPodcast(answers[2],answers[3],answers[4],answers[5],answers[6]));
                    System.out.println("END-------------------------------------------------");
                    showMenu();
                }
                else{
                    System.out.println("The command entered is not valid");
                    System.out.println("END-------------------------------------------------");
                    showMenu();
                }
            }
            case "ViewsStatistics" -> {
                System.out.println(ArtistController.getArtistController().viewNumberPlay());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "AccountInfo" -> {
                System.out.println(ArtistController.getArtistController().viewInfoacc());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Logout" -> {
                System.out.println("You are logged out of your account");
                System.out.println("END-------------------------------------------------");
                ArtistController.getArtistController().logoutArtist();
                AccountView.getAccountView().showFirstMenu();
            }
            default -> {
                System.out.println("The command entered is not valid");
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
        }
    }
}
