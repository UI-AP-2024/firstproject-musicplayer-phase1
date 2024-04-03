package View.Accounts;

import Controller.Accounts.AccountController;
import Controller.Accounts.AdminController;
import View.MainView;

import java.util.Scanner;

public class AdminView {

    private static AdminView admin;

    private AdminController adminController = AdminController.getAdminController();

    private AdminView(){}

    public static AdminView getAdmin(){
        if(admin == null){
            admin = new AdminView();
        }
        return admin;
    }

    Scanner scan = new Scanner(System.in);

    private String[] command;
    private void getCommand(){
        String userInput = scan.nextLine();
        this.command = userInput.split(" -");
    }
    public void loginPage(){
        System.out.println("\nLogin successfully");
        firstPage();
    }

    public void firstPage(){
        getCommand();

        switch (this.command[0]){
            case "AccountInfo" -> {
                System.out.println(adminController.showAccountInfo());
                firstPage();
            }
            case "Statistics" -> {
                System.out.println(adminController.showMostPopularAudiosInfo());
                firstPage();
            }
            case "Audios" -> {
                System.out.println(adminController.showAudiosInfo());
                firstPage();
            }
            case "Audio" -> {
                System.out.println(adminController.showAudioDetail(Integer.parseInt(this.command[1])));
                firstPage();
            }
            case "Artists" -> {
                System.out.println(adminController.showArtists());
                firstPage();
            }
            case "Artist" -> {
                System.out.println(adminController.showArtistDetail(this.command[1]));
                firstPage();
            }
            case "Reports" -> {
                System.out.println(adminController.showReports());
                firstPage();
            }
            case "Logout" -> {
                MainView.getMainView().mainMenu("");
            }
            case "Help" -> {
                System.out.println("\nStatistics");
                System.out.println("Audios");
                System.out.println("Audio -[audio’s ID]");
                System.out.println("Artists");
                System.out.println("Artist -[username]");
                System.out.println("Reports");
                firstPage();
            }
            default -> {
                System.out.println("\nEnter the right command \nEnter \"Help\" for help");
                firstPage();
            }


        }
    }
}
