package Views;

import Controllers.AccountController;
import Models.Genre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class AccountView {
    private AccountController accountController;
    private static AccountView accountView;
    public static AccountView getUserView()
    {
        if(accountView == null) accountView = new AccountView();
        return accountView;
    }
    private AccountView()
    {
        accountController = AccountController.getUserController();
    }
    public void getInput()
    {
        Scanner jin = new Scanner(System.in);
        String input = jin.nextLine();
        String[] inputArray = input.split(" -");
        switch (inputArray[0])
        {
            case "Signup":
                if(inputArray[1].equals("L"))
                {
                    System.out.println(accountController.signUp(inputArray[1],
                            inputArray[2], inputArray[3], inputArray[4], inputArray[5],
                            inputArray[6], LocalDate.parse(inputArray[7]), null));
                }
                else if(inputArray[1].equals("S") || inputArray[1].equals("P"))
                {
                    System.out.println(accountController.signUp(inputArray[1],
                            inputArray[2], inputArray[3], inputArray[4], inputArray[5],
                            inputArray[6], LocalDate.parse(inputArray[7]), inputArray[8]));
                }
                break;
            case "Login":
                System.out.println(accountController.login(inputArray[1], inputArray[2]));
                break;
            default:
                System.out.println("Invalid command");
        }
        getInput();
    }

    public ArrayList<Genre> getGenres()
    {
        System.out.println("Enter your favourite genres");
        for(Genre genre : Genre.values())
        {
            System.out.println(genre.getAsString());
        }
        Scanner jin = new Scanner(System.in);
        String input = jin.nextLine();
        String[] inputArray = input.split(" -"); // spliting command name and genres
        String[] genreArray = inputArray[1].split(" ,");
        ArrayList<Genre> result = new ArrayList<>();
        for(String genreName : genreArray)
        {
            result.add(Genre.fromString(genreName));
        }
        return result;
    }
}
