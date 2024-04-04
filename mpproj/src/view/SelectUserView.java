package view;

import controller.ListenerController;
import controller.SignInOutController;


import java.util.Objects;
import java.util.Scanner;

public class SelectUserView {
    private SignInOutController user;
    public SignInOutController getUserAccount() {
        return user;
    }
    public void setUserAccount(SignInOutController userAccount) {
        this.user = userAccount;
    }

    private static SelectUserView selectUserView;

    public static SelectUserView getSelectUserView() {
        if (selectUserView == null)
            selectUserView = new SelectUserView();
        return selectUserView;
    }
    Scanner scanner = new Scanner(System.in);

    public void signUpLogInOut() {
        String command = scanner.nextLine();
        String[] strings = command.split(" -");
        switch (strings[0]) {
            case "Signup" :
                switch (strings[1]) {
                    case "L" :
                        if(Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Phone number format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Email format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "This username or email is already in use. Try again!"))
                            signUpLogInOut();
                        else {
                            String string1 = scanner.nextLine();
                            String[] string2 = string1.split(" -");
                            String[] genres = string2[1].split(",");
                            ListenerController.getListenerController().favoriteGenres(genres[0], genres[1], genres[2], genres[3]);
                            signUpLogInOut();
                        }
                    case "S" :
                        if(Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Phone number format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Email format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "This username or email is already in use. Try again!"))
                            signUpLogInOut();
                        else
                            signUpLogInOut();
                    case "P" :
                        if(Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Phone number format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "Email format is false") || Objects.equals(SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]), "This username or email is already in use. Try again!"))
                            signUpLogInOut();
                        else
                            signUpLogInOut();
                    default:
                        System.out.println("This type of account is invalid");
                        signUpLogInOut();
                }
            case "Login" :
                if (Objects.equals(SignInOutController.getUserAccountController().login(strings[1], strings[2]), "Password is incorrect") || Objects.equals(SignInOutController.getUserAccountController().login(strings[1], strings[2]), "username not found"))
                    signUpLogInOut();
                else {
                    user = SignInOutController.getUserAccountController();
                }

        }

    }
}
