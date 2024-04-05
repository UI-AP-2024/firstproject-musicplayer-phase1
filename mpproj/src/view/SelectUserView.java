package view;

import controller.ListenerController;
import controller.SignInOutController;
import model.UserAccount.AdminModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;


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
        String result;
        switch (strings[0]) {
            case "Signup" :
                switch (strings[1]) {
                    case "L" :
                        result = SignInOutController.getUserAccountController().sinUpListener(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7]);
                        if(Objects.equals(result, "Phone number format is false") || Objects.equals(result, "Email format is false") || Objects.equals(result, "This username or email is already in use. Try again!")) {
                            System.out.println(result);
                            signUpLogInOut();
                        }
                        else {
                            System.out.println(result);
                            String string1 = scanner.nextLine();
                            String[] string2 = string1.split(" -");
                            String[] genres = string2[1].split(",");
                            result = ListenerController.getListenerController().favoriteGenres(genres[0], genres[1], genres[2], genres[3]);
                            System.out.println(result);
                            signUpLogInOut();
                        }
                    case "S" :
                        SignInOutController.getUserAccountController().sinUpSinger(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8]);
                        signUpLogInOut();
                    case "P" :
                        SignInOutController.getUserAccountController().sinUpPodcaster(strings[2], strings[3], strings[4], strings[5], strings[6], strings[7], strings[8]);
                        signUpLogInOut();
                    default:
                        System.out.println("This type of account is invalid");
                        signUpLogInOut();
                }
            case "Login" :
                result = SignInOutController.getUserAccountController().login(strings[1], strings[2]);
                if (Objects.equals(result, "Password is incorrect") || Objects.equals(result, "username not found")) {
                    System.out.println(result);
                    signUpLogInOut();
                }
                else {
                    System.out.println("Logged in");
                    if (SignInOutController.getUserAccountController().getUserAccount() instanceof ListenerModel)
                        ListenerView.getListenerView().methods();
                    else if (SignInOutController.getUserAccountController().getUserAccount() instanceof ArtistModel)
                        ArtistView.getArtistView().methods();
                    else if (SignInOutController.getUserAccountController().getUserAccount() instanceof AdminModel)
                        AdminView.getAdminView().methods();
                }
            case "Logout" :
                System.out.println("Logged out");
                signUpLogInOut();
            default :
                System.out.println("Your are not logged in!");
                signUpLogInOut();
        }
    }
}
