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

        UserC.loginAll(username, password);
    }

    public static void showUserPanel(Listener user) {
        System.out.println("welcome to listener panel");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commits = input.split(" -");
            switch (commits[0]) {
                case "GetSeggestions":
                    System.out.println(UserC.recommendSongs(user, Database.getDatabase().getAudiofiles(), UserC.getLikeAudis()));
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

                case "Follow":
                    String artistUsername = commits[1];
                    UserC.followArtistByUsername(artistUsername);
                    showUserPanel(user);

                    break;

                case "Search":
                    String keyword = commits[1];
                    System.out.println(UserC.searchByKeyword(Database.getDatabase().getAudiofiles(), keyword));
                    showUserPanel(user);


                    break;

                case "Filter":
                    String filterType = commits[1];

                    switch (filterType) {
                        case "A":
                            String input2 = commits[2];
                            System.out.println(UserC.filterByArtist(Database.getDatabase().getAudiofiles(), input2));
                            showUserPanel(user);
                            break;

                        case "G":
                            String input3 = commits[2];
                            System.out.println(UserC.filterByGenre(Database.getDatabase().getAudiofiles(), input3));
                            showUserPanel(user);
                            break;

                        case "D":
                            String input4 = commits[2];
                            String input5 = commits[3];
                            System.out.println(UserC.filterByReleaseDate(Database.getDatabase().getAudiofiles(), input4, input5));

                            showUserPanel(user);

                            break;
                    }
                    break;
                case "Sort":
                    String sortType = commits[1];

                    switch (sortType) {
                        case "L":
                            System.out.println(UserC.sortByPopularity(Database.getDatabase().getAudiofiles()));
                            showUserPanel(user);
                            break;

                        case "P":
                            System.out.println(UserC.sortByPlayCount(Database.getDatabase().getAudiofiles()));
                            showUserPanel(user);
                            break;
                    }
                    break;

                case "Add":
                    String playlistName = commits[1];
                    String AudioId = commits[2];
                    UserC.addMusicToPlaylist(playlistName, Integer.parseInt(AudioId));
                    showUserPanel(user);
                    break;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    public static void showUserPanelLibrary(Listener user) {
        System.out.println("welcome to Library panel");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commits = input.split(" -");
            switch (commits[0]) {
                case "ShowPlaylists":
                    UserV.viewPlaylists(Listener.getPlaylists());
                    showUserPanelLibrary(user);
                    break;
                case "SelectPlaylists":
                    String playlistName = commits[1];
                    UserV.viewPlaylistContents(playlistName);
                    showUserPanelLibrary(user);



}}}}