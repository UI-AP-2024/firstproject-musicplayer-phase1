package view;

import controller.AdminController;
import controller.ListenerController;

import java.util.Scanner;

public class AdminView {
    private static AdminView adminView;

    private AdminView() {
    }

    public static AdminView getAdminView() {
        if (adminView == null)
            adminView = new AdminView();
        return adminView;
    }

    Scanner sc = new Scanner(System.in);

    public void showMenu() {
        String txt = sc.nextLine();
        String[] answers = txt.split(" -");
        switch (answers[0]) {
            case "Statistics" -> {
                System.out.println(AdminController.getAdminController().favoriteAudio());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Audios" -> {
                System.out.println(AdminController.getAdminController().viewAudios());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Artists" -> {
                System.out.println(AdminController.getAdminController().viewArtists());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Reports" -> {
                System.out.println(AdminController.getAdminController().viewReports());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Artist" -> {
                System.out.println(AdminController.getAdminController().viewInfoArtist(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Audio" -> {
                System.out.println(AdminController.getAdminController().viewAudio(answers[1]));
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "AccountInfo" -> {
                System.out.println(AdminController.getAdminController().viewAdminInfo());
                System.out.println("END-------------------------------------------------");
                showMenu();
            }
            case "Logout" -> {
                System.out.println("You are logged out of your account");
                System.out.println("END-------------------------------------------------");
                AdminController.getAdminController().setUserAccount(null);
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
