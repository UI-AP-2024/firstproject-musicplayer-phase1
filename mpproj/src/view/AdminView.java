package view;

import controller.AdminController;
import model.UserAccount.AdminModel;
import model.UserAccount.Artist.ArtistModel;

import java.util.Scanner;

public class AdminView {
    private AdminModel admin;

    public AdminModel getAdmin() {
        return admin;
    }

    public void setAdmin(AdminModel admin) {
        this.admin = admin;
    }
    private static AdminView adminView;

    public static AdminView getAdminView() {
        if (adminView == null)
            adminView = new AdminView();
        return adminView;
    }
    Scanner scanner = new Scanner(System.in);
    public void methods() {
        String string = scanner.nextLine();
        String[] strings = string.split(" -");
        switch (strings[0]) {
            case "Statistics" :
                AdminController.getAdminController().showPopularAudios();
                methods();
            case "Audios" :
                AdminController.getAdminController().showAudios();
                methods();
            case "Audio" :
                AdminController.getAdminController().showAudioInfo(Integer.parseInt(strings[1]));
                methods();
            case "Artists" :
                AdminController.getAdminController().showArtists();
                methods();
            case "Artist" :
                AdminController.getAdminController().showArtistInfo(strings[1]);
                methods();
            case "Reports" :
                AdminController.getAdminController().showReports();
                methods();
            case "Logout" :
                SelectUserView.getSelectUserView().signUpLogInOut();
            default :
                System.out.println("This method is not available for you!!!");
                methods();
        }
    }
}
