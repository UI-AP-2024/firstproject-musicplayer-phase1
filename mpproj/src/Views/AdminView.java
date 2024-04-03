package view;

import Controller.AdminController;
import Controller.ListenerController;
import Model.Admin;
import com.sun.security.jgss.GSSUtil;

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

    public void adminPanel(){
        String[] inputs;
        do {
        System.out.println("Admin Panel");
        String input= sc.nextLine();
        inputs=input.split("-");
        switch (inputs[0].trim()) {
                case "Statistics":
                    ListenerController.getListenerController().sortAudios("L");
                    System.out.println(AdminController.getAdminController().showStatistics());
                    break;
                case "Audios":
                    System.out.println(AdminController.getAdminController().showAudiosList());
                    break;
                case "Audio":
                    System.out.println(AdminController.getAdminController().showAudio(inputs[1].trim()));
                    break;
                case "Artists":
                    System.out.println(ListenerController.getListenerController().seeArtists());
                    break;
                case "Artist":
                    System.out.println(ListenerController.getListenerController().seeArtistsAudios(inputs[1].trim()));
                    break;
                case "Reports":
                    System.out.println(AdminController.getAdminController().showReports());
                    break;
                case "Help":
                    System.out.println("Commands:\nStatistics\nAudios\nAudio\nArtists\nArtist\nReports\nLogout");
                    break;
                case "AccountInfo":
                    System.out.println(Admin.getAdmin());
                    break;
                default:
                    if (!(inputs[0].trim().equals("Logout"))) {
                        System.out.println("Incorrect command!(Use <Help> for more Info)");
                    }
            }
        }while (!(inputs[0].trim().equals("Logout")));

    }
}
