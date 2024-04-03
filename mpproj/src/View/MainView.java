package View;

import Controller.Accounts.AccountController;
import Controller.Accounts.ListenerController;
import Model.Accounts.AdminModel;
import View.Accounts.AdminView;
import View.Accounts.ArtistView;
import View.Accounts.ListenerView;

import java.util.Scanner;


public class MainView {

    private static MainView mainView;

    private MainView(){}

    public static MainView getMainView(){
        if (mainView == null){
            mainView = new MainView();
        }
        return mainView;
    }


    Scanner scan = new Scanner(System.in);


    public String mainMenu(String message){
        System.out.println();
        System.out.println(message);
        String userInput = scan.nextLine();
        String[] command = userInput.split(" -");

        switch (command[0]){
            case "Signup" -> {
                        String[] temp =  command[7].split("-");
                        int[] birthDate = new int[3];
                        for (int i = 0 ; i < temp.length ; i++){
                            birthDate[i] = Integer.parseInt(temp[i]);
                        }
                switch (command[1]){
                    case "L" -> {
                        System.out.println(AccountController.getMainController().signup(command[1] ,command[2] , command[3] , command[4] , command[5] , command[6] , birthDate));
                        mainMenu("");
                    }
                    case "S", "P" -> {
                        System.out.println(AccountController.getMainController().signup(command[1] ,command[2] , command[3] , command[4] , command[5] , command[6] , birthDate , command[8]));
                        mainMenu("");
                    }
                    default -> {
                        System.out.println("\nEnter the right command \nEnter \"Help\" for help");
                        mainMenu("");
                    }
                }
            }


            case "Login" -> {

                String loginMessage = AccountController.getMainController().login(command[1] , command[2]);

                if(loginMessage.equals("Username does not exist")){
                    mainMenu(loginMessage);
                }

                if(loginMessage.equals("Password is not correct")){
                    mainMenu(loginMessage);
                }

                if (loginMessage.equals("Listener")){
                    ListenerView.getListenerView().login();
                }

                if (loginMessage.equals("Admin")){
                    AdminView.getAdmin().loginPage();

                }

                if (loginMessage.equals("Singer") || loginMessage.equals("Podcaster")){
                    ArtistView.getArtistView().login(loginMessage);
                }
            }
            case "FavoriteGenres" -> {
                    System.out.println(ListenerController.getListenerController().addFavoriteGenres(command[1]));
                    mainMenu("");
            }
            case "Logout" -> {}
            case "Help" -> {
                System.out.println("\nSignup -S|P -[username] -[password] -[name] -[email] -[phone number]" +
                        "-[birth date] -[bio] (new user: Singer or Podcaster)");
                System.out.println("\nSignup -L -[username] -[password] -[name] -[email] -[phone number]" +
                        "-[birth date] new user: Listener");
                System.out.println("\nFavoriteGenres");
                System.out.println("\nLogin -[username] -[password]");
                System.out.println("\nLogout");
                mainMenu("");
            }
            default -> {
                System.out.println("\nEnter the right command \nEnter \"Help\" for help");
                mainMenu("");
            }
        }
        return "\nSomething went wrong";
    }
}
