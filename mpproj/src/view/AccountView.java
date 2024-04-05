package view;

import controller.AdminController;
import controller.ArtistController;
import controller.ListenerController;
import controller.LoginController;

import java.util.Scanner;

public class AccountView {
    private static AccountView accountView;

    private AccountView() {
    }

    public static AccountView getAccountView() {
        if (accountView == null)
            accountView = new AccountView();
        return accountView;
    }

    Scanner sc = new Scanner(System.in);
    public void showFirstMenu() {
        String txt = sc.nextLine();
        String[] answers = txt.split(" -");

        if ( answers[0].equals("Signup")){
            String[] date = answers[7].split("-");
            String result = "";
            if ( answers[1].equals("L")){
                result=ListenerController.getListenerController().registration(answers[2],answers[3],answers[4],answers[5],answers[6],date[0],date[1],date[2]);
                if ( result.equals("Account created successfully")){
                    System.out.println(result);
                    favoriteGener();
                }
                else {
                    System.out.println(result);
                    showFirstMenu();
                }
            }
            else if ( answers[1].equals("S") || answers[1].equals("P")){
                result=ArtistController.getArtistController().registration(answers[1],answers[2],answers[3],answers[4],answers[5],answers[6],date[0],date[1],date[2],answers[8]);
                if ( result.equals("Account created successfully")){
                    System.out.println(result);
                    //-------------------------
                    ArtistView.getAdminView().showMenu();
                }
                else {
                    System.out.println(result);
                    showFirstMenu();
                }
            }
            else {
                System.out.println("The command entered is not valid");
                showFirstMenu();
            }
        }

        else if ( answers[0].equals("Login")){
            String type =LoginController.getLoginController().typeLog(answers[1]);
            String result = "";
            if ( type.equals("Admin")){
                result=AdminController.getAdminController().login(answers[1],answers[2]);
                if ( result.equals("The username does not exist or the password is incorrect")) {
                    System.out.println(result);
                    showFirstMenu();
                }
                else{
                    System.out.println(result);
                    //----------------------------
                    AdminView.getAdminView().showMenu();
                }
            }
            else if ( type.equals("Artist")){
                result=ArtistController.getArtistController().login(answers[1],answers[2]);
                if ( result.equals("The username does not exist or the password is incorrect")) {
                    System.out.println(result);
                    showFirstMenu();
                }
                else{
                    System.out.println(result);
                    //----------------------------
                    ArtistView.getAdminView().showMenu();
                }
            }
            else if ( type.equals("Listener")){
               result =ListenerController.getListenerController().login(answers[1],answers[2]);
                if ( result.equals("The username does not exist or the password is incorrect")) {
                    System.out.println(result);
                    showFirstMenu();
                }
                else{
                    System.out.println(result);
                    //----------------------------
                    ListenerView.getListenerView().showMenu();
                }
            }
        }
        else {
            System.out.println("The command entered is not valid");
            showFirstMenu();
        }
    }

    public void favoriteGener(){
        System.out.println(ListenerController.getListenerController().showGener());
        String gener = sc.nextLine();
        String[] generArr = gener.split(" -");
        String result = "";
        if ( generArr[0].equals("FavouriteGenres")){
            result = ListenerController.getListenerController().getGener(generArr[1]);
            if ( result.equals("done successfully")){
                System.out.println(result);
                //---------------------------------
                ListenerView.getListenerView().showMenu();
            }
            else {
                System.out.println(result);
                favoriteGener();
            }
        }
        else {
            System.out.println("The command entered is not valid");
            favoriteGener();
        }
    }


}
