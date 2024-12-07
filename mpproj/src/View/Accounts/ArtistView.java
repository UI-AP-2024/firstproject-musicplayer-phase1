package View.Accounts;

import Controller.Accounts.AccountController;
import Controller.Accounts.ArtistsController;
import View.MainView;

import java.util.Scanner;

public class ArtistView {

    private static ArtistView artistView;

    private ArtistsController artistsController = ArtistsController.getArtistsController();

    private ArtistView(){}

    Scanner scan = new Scanner(System.in);

    private String artistType;
    public static ArtistView getArtistView(){
        if (artistView == null){
            artistView = new ArtistView();
        }
        return artistView;
    }


    public void login(String artistType){
        this.artistType = artistType;
        System.out.println("\nLogin successfully");
        firstPage();
    }


    public void firstPage(){
        String userInput = scan.nextLine();
        String[] command = userInput.split(" -");

        switch (command[0]){
            case "Followers" -> {
                System.out.println(artistsController.showFollower());
                firstPage();
            }
            case "ViewStatistics" -> {
                System.out.println(artistsController.showAudiosPlayCount());
                firstPage();
            }
            case "CalculateEarnings" -> {
                System.out.println(artistsController.calculateIncome());
                firstPage();
            }
            case "AccountInfo" -> {
                System.out.println(artistsController.showAccountInfo());
                firstPage();
            }
            case "NewAlbum" -> {
                System.out.println(artistsController.createAlbum(command[1]));
                firstPage();
            }
            case "Publish" -> {
                switch (command[1]){
                    case "M" -> {
                        System.out.println(artistsController.releaseAudio(command[2] , command[3] , command[4] , command[5] , command[6] , Integer.parseInt(command[7])));
                        firstPage();
                    }
                    case "P" ->{
                        System.out.println(artistsController.releaseAudio(command[2] , command[3] , command[4] , command[5] , command[6]));
                        firstPage();
                    }
                }
            }
            case "Logout" -> MainView.getMainView().mainMenu("");
            case "Help" -> {
                System.out.println("\nFollowers");
                System.out.println("ViewsStatistics");
                System.out.println("CalculateEarnings");
                if (this.artistType.equals("Singer")){
                    System.out.println("NewAlbum -[name]");
                }
                System.out.println(".Publish -M|P -[title] -[genre] -[lyric|caption] -[link] -[cover] -[album ID ] (Publish Music or Podcast)");
                System.out.println("Logout");
                firstPage();
            }
            default -> {
                System.out.println("\nEnter the right command \nEnter \"Help\" for help");
                firstPage();
            }

        }
    }



}
