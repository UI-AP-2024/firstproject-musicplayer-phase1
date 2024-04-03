package view;

import controller.PodcasterC;
import controller.SingerC;
import controller.UserC;
import controller.UserRegistration;
import model.AccountUser.AccountUser;
import model.AccountUser.Admin;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Database;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Panels {
        public static void showFirstMeneu() {
            System.out.println("Signup\nLogin");
            System.out.println("Please enter your choice:");

            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine();

            handleCommand(choice);
        }
        public static void handleCommand(String command) {
            String[] commands = command.split(" -");
            switch (commands[0]) {
                case "Signup":
                    signUp(commands);
                    break;
                case "Login":
                    login(commands);
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
        public static void signUp(String[] commands) {
            switch (commands[1]) {
                case "L":
                    UserC.signUpL(commands);
                    break;
                case "P":
                    PodcasterC.signUpLP(commands);
                    break;
                case "S":
                    SingerC.signUpS(commands);
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
        public static void login(String[] commands) {
            if (commands.length != 3) {
                System.out.println("Invalid number of arguments for Login command!");
                return;
            }
            String username = commands[1];
            String password = commands[2];

            UserC.loginAll(username,password);
        }

    public static void showUserPanel(Listener user) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] commits = input.split(" -");
        switch (commits[0]){
            case "GetSeggestions":
                System.out.println(UserC.recommendSongs(user, Database.getDatabase().getAudiofiles(), UserC.getLikeAudis()));
                showUserPanel(user);
                break;

            case "Artists":
                AdminV.displayArtists();
                showUserPanel(user);
                break;

            case "Artist":
                String userName = commits[1];
                AdminV.displayArtistInfo(userName);
                showUserPanel(user);
                break;

            case  "Follow":





        }

    }
}