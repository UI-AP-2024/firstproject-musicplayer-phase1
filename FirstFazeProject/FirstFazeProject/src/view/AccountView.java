package view;

import controller.AccountController;
import model.*;

import java.util.Date;
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
                showFirstMenu();
            case -1:
                System.out.println("Your password is too weak please choose another password 🙏");
                showFirstMenu();
            case -2:
                System.out.println("Your e-mail's format in not correct please recheck 🙏");
                showFirstMenu();
            case -3:
                System.out.println("Your phone number's format in not correct please reassure 🙏");
                showFirstMenu();
            case 1:
                System.out.println("The listener account was successfully added 😎");
                showGenresMenu();
            case 2:
                System.out.println("The singer account was successfully added 😎");
                showFirstMenu();
            case 3:
                System.out.println("The podcast account was successfully added 😎");
                showFirstMenu();
        }
    }
    public void showGenresMenu(){
        System.out.println("Please choose 4 of the genres below 🎼");
        System.out.println(AccountController.getAccountController().showGenres());
        AccountController.getAccountController().addFavoriteGenres(inp.nextLine());
        System.out.println("Favorite genres were added successfully");
        showMainMenu();
    }
}
