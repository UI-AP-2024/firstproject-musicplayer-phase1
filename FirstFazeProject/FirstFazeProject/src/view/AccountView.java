package view;
import model.Admin;
import controller.AccountController;
import model.*;

import java.util.Objects;
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

    Scanner inp = new Scanner(System.in);

    public void showFirstMenu(){
        Admin.getAdmin();
        System.out.println("Welcome to the music player please signup or login first ❤");
        showMainMenu();
    }
    public void showMainMenu() {
        System.out.println("Please enter you information to signup or login\r\n" +
                "Your password was be made of 10 to 16 characters, including lower upper case characters and numbers as well 📱\r\n" +
                "Your e-mail must have true format and have one of these suffixes --> (gmail-yahoo-hotmail-aol) 📃\r\n" +
                "Your phone number must have a true form and have no space between numbers 📞");
        String answer = inp.nextLine();
        switch (AccountController.getAccountController().signUp(answer)){
            case 0:
                System.out.println("This name has been taken before please try again 🙏");
                showMainMenu();
            case -1:
                System.out.println("Your password is too weak please choose another password 🙏");
                showMainMenu();
            case -2:
                System.out.println("Your e-mail's format in not correct please recheck 🙏");
                showMainMenu();
            case -3:
                System.out.println("Your phone number's format in not correct please reassure 🙏");
                showMainMenu();
            case 1:
                System.out.println("The listener account was successfully added 😎");
                showMainMenu();
            case 2:
                System.out.println("The singer account was successfully added 😎");
                showMainMenu();
            case 3:
                System.out.println("The podcast account was successfully added 😎");
                showMainMenu();
            case 4:
                System.out.println("Login was successfully done 😎");
                AccountController.getAccountController().loginPanel(answer);
                break;
            case 5:
                System.out.println("Your name or password is wrong please try again 🙏");
                showMainMenu();
            case 6:
                System.out.println("You must first signup or login to be able to use the music player 😓");
                showMainMenu();
        }
    }
    public void showGenresMenu(){
        System.out.println("Please choose 4 of the genres below 🎼");
        System.out.println(AccountController.getAccountController().showGenres());
        AccountController.getAccountController().addFavoriteGenres(inp.nextLine());
        System.out.println("Favorite genres were added successfully");
        showMainMenu();
    }
    public void showListenerLoginPanel(UserAccount person){
        System.out.println("Please Enter a command");
        String answer = inp.nextLine();
        AccountController.getAccountController().loginListenerPanelOrders((Listener)person,answer);
    }
    public void showArtistLoginPanel(UserAccount person){
        System.out.println("Please Enter a command");
        String answer = inp.nextLine();
        AccountController.getAccountController().loginArtistPanelOrders((Artist)person,answer);
    }
    public void showAdminLoginPanel(UserAccount person){
        System.out.println("Please Enter a command");
        String answer = inp.nextLine();
        AccountController.getAccountController().loginAdminPanelOrders((Admin)person,answer);
    }
    public void successfullyLogin(UserAccount person , String type){
        System.out.println("You have login to your panel, now you can use the program ✌");
        if (Objects.equals(type, "Listener")) {
            showListenerLoginPanel(person);
        }else if (Objects.equals(type, "Admin")){
            showAdminLoginPanel(person);
        }else if (type == "Artist"){
            showArtistLoginPanel(person);
        }
    }
    public void showResult(StringBuilder result){
        System.out.println(result);
    }
}
