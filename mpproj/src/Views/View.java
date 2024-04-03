package view;

import Controller.Controller;
import Model.DataBase;
import Model.FreeListener;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class View {
    static String[] answers;
    public static void firstPage() {
        System.out.println("Signup-Login");
        Scanner sc = new Scanner(System.in);
        String answer = sc.nextLine();
         answers=answer.split("-");
        if (answers[0].trim().equals("Signup")) {
            if(!(Controller.checkEmail(answers[5].trim()))){
                System.out.println("Invalid Email!");
                firstPage();
            }
            if(!(Controller.checkPhoneNum(answers[6].trim()))){
                System.out.println("Invalid Phone Number!");
                firstPage();
            }
            signUpPage();
        }else if (answers[0].trim().equals("Login")){
            loginPage();
        }else{
            System.out.println("Incorrect Command(Use Login or Signup Command!)");
            firstPage();
        }
    }
    public static void signUpPage(){
        if (Controller.checkUserName(answers[2].trim())) {
            switch (answers[1].trim()) {
                case "L":
                    ListenerView.getlistenerView().SignUp_L();
                    firstPage();
                    break;
                case "S":
                    ArtistView.getArtistView().SignUp_S();
                    firstPage();
                    break;
                case "P":
                    ArtistView.getArtistView().SignUp_P();
                    firstPage();
                    break;
                default:
                    System.out.println("Incorrect Command(Use Help for more Info!");
                    firstPage();
            }
        }else{
            System.out.println("Username is used before!");
            firstPage();
        }
    }
    public static void loginPage(){
        switch (Controller.findUser(answers[1].trim(), answers[2].trim())) {
            case "Listener":
                ListenerView.getlistenerView().listenerPanel();
                firstPage();
                break;
            case "Admin":
                AdminView.getAdminView().adminPanel();
                firstPage();
                break;
            case "Artist":
                ArtistView.getArtistView().artistPanel();
                firstPage();
                break;
            default :
                System.out.println("Username or password is incorrect");
                firstPage();
                break;
        }
    }
}
