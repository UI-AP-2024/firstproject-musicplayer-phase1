package View.Accounts;

import java.util.Scanner;

import Controller.Accounts.*;
        import View.MainView;

public class ListenerView {

    private static ListenerView listenerView;

    private String[] command;

    private ListenerView(){}

    public static ListenerView getListenerView(){
        if (listenerView == null){
            listenerView = new ListenerView();
        }
        return listenerView;
    }
    private ListenerController listenerController = ListenerController.getListenerController();
    private AccountController accountController = AccountController.getMainController();
    Scanner scan = new Scanner(System.in);

    private void getCommand(){
        String userInput = scan.nextLine();
        this.command = userInput.split(" -");
    }

    public void login(){
        System.out.println("\nLogin successfully");
        firstPage();
    }

    public void firstPage() {
        getCommand();
        switch (this.command[0]) {

            case "GetSuggestions" -> {
                if (this.command.length == 1){
                    System.out.println(listenerController.suggestAudio());
                }
                else {
                    System.out.println(listenerController.suggestAudio(Integer.parseInt(this.command[1])));
                }
                firstPage();
            }
            case "Artists" -> {
                System.out.println(listenerController.showArtists());
                firstPage();
            }
            case "Artist" -> {
                System.out.println(listenerController.showArtistDetails(command[1]));
                firstPage();
            }
            case "Follow" -> {
                System.out.println(listenerController.followArtist(command[1]));
                firstPage();
            }
            case "Search" -> {
                System.out.println(listenerController.search(this.command[1]));
                firstPage();
            }
            case "Sort" -> {
                switch (this.command[1]) {
                    case "L" -> System.out.println(listenerController.sortByLike());
                    case "P" -> System.out.println(listenerController.sortByPlayCount());
                }
                firstPage();
            }
            case "Filter" -> {
                switch (this.command[1]) {
                    case "A" -> {
                        System.out.println(listenerController.filterByArtist(this.command[2]));
                    }
                    case "G" -> {
                        System.out.println(listenerController.filterByGenre(this.command[2]));
                    }
                    case "D" -> {
                        System.out.println(listenerController.filterByDate(this.command[2], this.command[3]));
                    }
                }
                firstPage();
            }
            case "AccountInfo" -> {
                System.out.println(listenerController.showAccountInformation());
                firstPage();
            }
            case "Add" -> {
                System.out.println(listenerController.addToPlaylist(this.command[1], Integer.parseInt(this.command[2])));
                firstPage();
            }
            case "Play" -> {
                System.out.println(listenerController.playMusic(Integer.parseInt(this.command[1])));
                firstPage();
            }
            case "Like" ->{
                System.out.println(listenerController.likeAudio(Integer.parseInt(this.command[1])));
                firstPage();
            }
            case "Lyric" -> {
                System.out.println(listenerController.showLyric(Integer.parseInt(this.command[1])));
                firstPage();
            }
            case "IncreaseCredit" -> {
                System.out.println(listenerController.increaseCredit(Double.parseDouble(this.command[1])));
                firstPage();
            }
            case "GetPremium" -> {
                System.out.println(listenerController.buyOrRenewalSubscription(this.command[1]));
                firstPage();
            }
            case "ShowPlaylists" -> {
                System.out.println(listenerController.showAllPlaylists());
                firstPage();
            }
            case "NewPlaylist" -> {
                System.out.println(listenerController.createPlaylist(command[1]));
                firstPage();
            }
            case "SelectPlaylist" -> {{
                System.out.println(listenerController.showPlaylistDetail(this.command[1]));
                firstPage();
                }
            }
            case "Followings" -> {
                System.out.println(listenerController.showFollowing());
                firstPage();
            }
            case "Report" -> {
                System.out.println(listenerController.reportArtists(this.command[1], this.command[2]));
                firstPage();
            }
            case "Logout" -> MainView.getMainView().mainMenu("");
            case "Help" -> {
                System.out.println("GetSuggestions");
                System.out.println("GetSuggestions -[audio count]");
                System.out.println("Artists");
                System.out.println("Artist -[username]");
                System.out.println("Follow -[username]");
                System.out.println("Search -[artist name OR audio’s title]");
                System.out.println("Sort -L|P (based on Likes or Plays)");
                System.out.println("Filter -A|G -[filter by] (Filter by Artist, Genre)");
                System.out.println("Filter -D -[start date] -[end date]");
                System.out.println("AccountInfo");
                System.out.println("Add -[playlist’s name] -[audio’s ID]");
                System.out.println("Play -[audio’s ID]");
                System.out.println("Like -[audio’s ID]");
                System.out.println("Lyric -[audio’s ID]");
                System.out.println("IncreaseCredit -[value]");
                System.out.println("GetPremium -[package]");
                System.out.println("ShowPlaylists");
                System.out.println("NewPlaylist -[playlist’s name]");
                System.out.println("SelectPlaylist -[playlist’s name]");
                System.out.println("Followings");
                System.out.println("Report -[artist’s username] -[explanation]");
                System.out.println("Logout");
                firstPage();
            }
            default -> {
                System.out.println("Enter the right command \nEnter \"Help\" for help");
                firstPage();
            }

        }
    }
}

