package View;
import Controller.AdminController;
import Model.User;

import java.util.Date;
import java.util.Scanner;
public class AdminView extends User{
    private AdminController view;
    private AdminView(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        super(username,password,fullName,email,phoneNumber,birthDate);
    }

    public void showLoginMenu(){
        System.out.println("1) log in");
        Scanner sin = new Scanner(System.in);
        sin.nextLine();
        System.out.println("Enter Your username & password:");
        String username = sin.next() ;
        String password = sin.next();
        if(view.enterUserPanel(username,password)) {
            System.out.println("Correct!");

        }
        else{
            System.out.println("username or password is wrong!");
            System.out.println("Try Again!");
            showLoginMenu();
        }
    }
    private void showSecondMenu(){
        System.out.println("1) Statistics: Most Poplar Songs\n2) Audios: Audios Information\n3) Artists: Artists Information\n4) Reports\n5) UserInfo\n6) log out");
        Scanner sin = new Scanner(System.in);
        String command = sin.nextLine();
        while ((!command.equals("log out"))) {
            switch (command) {
                case "Statistics":
                    System.out.println(view.showPopularSongs());
                    break;
                case "Audios":
                    System.out.println(view.showAudioInfo());
                    break;
                case "Artists":
                    System.out.println(view.showArtistInfo());
                    break;
                case "Reports":
                    System.out.println(view.showReports());
                    break;
                case "UserInfo":
                    System.out.println(view.showUserInfo());
                    break;
                default:
                    System.out.println("Enter Valid command!");
            }
            showSecondMenu();
            command = sin.nextLine();
        }
        showLoginMenu();
    }
}
