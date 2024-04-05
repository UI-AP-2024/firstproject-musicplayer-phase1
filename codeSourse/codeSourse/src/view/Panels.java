package view;

import controller.ArtistC;
import controller.PodcasterC;
import controller.SingerC;
import controller.UserC;
import model.AccountUser.Admin;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Audio.Music;
import model.Database;
import model.Genre;

import java.util.List;
import java.util.Scanner;

public class Panels {
    public static void showFirstMeneu() {
        System.out.println("Signup\nLogin");
        System.out.println("Please enter your choice:\n\n");

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
        System.out.println("GetSeggestions\nArtists\nArtist\nFollow\nSearch\nFilter\nSort\nShowPlaylists\nSelectPlaylists\nPlay\nLike\nLyric\nNewPlaylist\nAdd\nFollowing\nReport\nIncreasrCredit\nAudioId\nGetPremium\nAccountInfo\nLogout\n\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commits = input.split(" -");
            switch (commits[0]) {
                case "GetSeggestions":
                    System.out.println(UserC.recommendSongs(user, Database.getInstance().getAudiofiles(), UserC.getLikeAudis()));
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
                    UserC.followArtistByUsername(artistUsername, user);
                    showUserPanel(user);

                    break;

                case "Search":
                    String keyword = commits[1];
                    System.out.println(UserC.searchByKeyword(Database.getInstance().getAudiofiles(), keyword));
                    showUserPanel(user);
                    break;

                case "Filter":
                    String filterType = commits[1];

                    switch (filterType) {
                        case "A":
                            String input2 = commits[2];
                            List<Audio> filteredByArtist = UserC.filterByArtist(Database.getInstance().getAudiofiles(), input2);
                            if (!filteredByArtist.isEmpty()) {
                                System.out.println("Filtered audios by artist:");
                                for (Audio audio : filteredByArtist) {
                                    System.out.println(audio.getTitle() + " by " + audio.getArtist());
                                }
                            } else {
                                System.out.println("No audio found for the specified artist.");
                            }
                            showUserPanel(user);
                            break;

                        case "G":
                            String input3 = commits[2];
                            List<Audio> filteredByGenre = UserC.filterByGenre(Database.getInstance().getAudiofiles(), input3);
                            if (!filteredByGenre.isEmpty()) {
                                System.out.println("Filtered audios by genre:");
                                for (Audio audio : filteredByGenre) {
                                    System.out.println(audio.getTitle() + " (" + audio.getGenre() + ")");
                                }
                            } else {
                                System.out.println("No audio found for the specified genre.");
                            }
                            showUserPanel(user);
                            break;

                        case "D":
                            String input4 = commits[2];
                            String input5 = commits[3];
                            List<Audio> filteredByReleaseDate = UserC.filterByReleaseDate(Database.getInstance().getAudiofiles(), input4, input5);
                            if (!filteredByReleaseDate.isEmpty()) {
                                System.out.println("Filtered audios by release date:");
                                for (Audio audio : filteredByReleaseDate) {
                                    System.out.println(audio.getTitle() + " (Release Date: " + audio.getReleaseDate() + ")");
                                }
                            } else {
                                System.out.println("No audio found for the specified release date range.");
                            }
                            showUserPanel(user);
                            break;
                    }
                    break;
                case "Sort":
                    String sortType = commits[1];

                    switch (sortType) {
                        case "L":
                            System.out.println(UserC.sortByPopularity(Database.getInstance().getAudiofiles()));
                            showUserPanel(user);
                            break;

                        case "P":
                            System.out.println(UserC.sortByPlayCount(Database.getInstance().getAudiofiles()));
                            showUserPanel(user);
                            break;
                    }
                    break;

                case "ShowPlaylists":
                    UserV.viewPlaylists(Listener.getPlaylists());
                    showUserPanel(user);
                    break;

                case "SelectPlaylists":
                    String playName = commits[1];
                    UserV.viewPlaylistContents(playName);
                    showUserPanel(user);
                    break;

                case "Play":
                    String playId = commits[1];
                    UserV.playAudio(Integer.parseInt(playId));
                    showUserPanel(user);
                    break;

                case "Like":
                    String LikeId = commits[1];
                    UserC.likeAudio(Integer.parseInt(LikeId));
                    showUserPanel(user);
                    break;

                case "Lyric":
                    String lyrcId = commits[1];
                    System.out.println(Music.getLyrics());
                    showUserPanel(user);
                    break;


                case "NewPlaylist":
                    String playlistId = commits[1];
                    UserC.createPlaylist(playlistId, user);
                    showUserPanel(user);
                    break;

                case "Add":
                    String playlistname = commits[1];
                    String inp = commits[2];
                    UserC.addMusicToPlaylist(playlistname, Integer.parseInt(inp));
                    showUserPanel(user);
                    break;

                case "Following":
                    UserV.displayFollowedArtists();
                    showUserPanel(user);
                    break;

                case "Report":
                    String userArtist = commits[1];
                    String description = commits[2];
                    UserC.reportUser(user, userArtist, description);
                    showUserPanel(user);
                    break;

                case "IncreasrCredit":
                    String value = commits[1];
                    UserC.increaseAccountBalance(user, Double.parseDouble(value));
                    showUserPanel(user);
                    break;

                case "GetPremium":
                    String pakage = commits[1];
                    UserC.purchasePremiumSubscription(Integer.parseInt(pakage), user);
                    showUserPanel(user);
                    break;

                case "AccountInfo":
                    UserV.printAccountInfo(user);
                    showUserPanel(user);
                    break;

                case "AudioId":
                    SingerV.displayAudioIdsAndTitles();
                    showUserPanel(user);
                    break;

                case "Logout":
                    showFirstMeneu();
                    break;

                default:
                    System.out.println("Invalid command!");


            }
        }
    }

    public static void showAdminPanel(Admin admin) {
        System.out.println("welcome to Admin panel");
        System.out.println("Statistics\nAudios\nAudio\nArtists\nArtist\nReports\nAccountInfo\nLogout\n\n");


        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commits = input.split(" -");
            switch (commits[0]) {

                case "Statistics":
                    AdminV.displayPopularAudios(UserC.getLikeAudis(), Listener.getPlayCountByAudio());
                    showAdminPanel(admin);
                    break;

                case "Audios":
                    AdminV.displayAudios();
                    showAdminPanel(admin);
                    break;

                case "Audio":
                    String audioId = commits[1];
                    AdminV.displayAudio(Integer.parseInt(audioId));
                    showAdminPanel(admin);
                    break;

                case "Artists":
                    AdminV.displayArtists();
                    showAdminPanel(admin);
                    break;

                case "Artist":
                    String artistUsername = commits[1];
                    AdminV.displayArtistInfo(artistUsername);
                    showAdminPanel(admin);
                    break;

                case "Reports":
                    AdminV.displayReports(Database.getInstance().getReports());
                    showAdminPanel(admin);

                    break;

                case "AccountInfo":
                    UserV.printAccountInfo(admin);
                    showAdminPanel(admin);
                    break;

                case "AudioId":
                    SingerV.displayAudioIdsAndTitles();
                    showAdminPanel(admin);
                    break;

                case "Logout":
                    showFirstMeneu();
                    break;

                default:
                    System.out.println("Invalid command!");
            }
        }
    }

    public static void showArtistPanel(Artist artist) {
        System.out.println("welcome to Artist panel");
        System.out.println("Followes\nViewStatistics\nCalculateEarning\nNewAlbum\nviewAlbumId\nPublish\nAccountInfo\nLogout\n\n");

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            String[] commits = input.split(" -");
            switch (commits[0]) {

                case "Followers":
                    ArtistV.displayArtistFollowers(artist);
                    showArtistPanel(artist);
                    break;

                case "ViewStatistics":
                    ArtistV.displayPlayCount(artist);
                    showArtistPanel(artist);
                    break;

                case "CalculateEarning":
                    ArtistC.updateArtistIncome(artist);
                    showArtistPanel(artist);
                    break;

                case "NewAlbum":
                    String albumName = commits[1];
                    SingerC.createAlbum((Singer) artist, albumName);
                    showArtistPanel(artist);
                    break;

                case "viewAlbumId":
                    SingerV.printAlbumInfo((Singer) artist);
                    showArtistPanel(artist);
                    break;

                case "Publish":
                    String publishType = commits[1];

                    switch (publishType) {
                        case "M":
                            String title = commits[2];
                            String genre = commits[3];
                            String lyric = commits[4];
                            String link = commits[5];
                            String cover = commits[6];
                            String albumId = commits[7];

                            if (artist instanceof Singer) {
                                SingerC.publishMusic((Singer) artist, title, Genre.valueOf(genre), Integer.parseInt(albumId), link, cover, lyric);
                                showArtistPanel(artist);
                            } else {
                                System.out.println("Invalid artist type for publishing music.");
                            }
                            break;

                        case "P":
                            String titlep = commits[2];
                            String genrep = commits[3];
                            String lyricp = commits[4];
                            String linkp = commits[5];
                            String coverp = commits[6];

                            if (artist instanceof Podcaster) {
                                PodcasterC.publishPodcast((Podcaster) artist, titlep, Genre.valueOf(genrep), linkp, coverp, lyricp);
                                showArtistPanel(artist);
                            } else {

                                System.out.println("Invalid artist type for publishing podcast.");
                                showArtistPanel(artist);

                            }
                            break;

                        default:
                            System.out.println("Invalid command!");
                            showArtistPanel(artist);

                            break;
                    }
                    break;

                case "AccountInfo":
                    UserV.printAccountInfo(artist);
                    showArtistPanel(artist);
                    break;

                case "Logout":
                    showFirstMeneu();
                    break;
                default:
                    System.out.println("Invalid command!");
            }
        }
    }
}