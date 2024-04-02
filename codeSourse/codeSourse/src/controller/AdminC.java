package controller;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Report;


public class AdminC {
    private UserRegistration userRegistration;

    public AdminC(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

    public void login(String username, String password) {
        if (checkCredentials(username, password)) {
            showUserPanel(username);
        } else {
            System.out.println("Invalid username or password");
        }
    }

    private void showUserPanel(String username) {
    }

    private boolean checkCredentials(String username, String password) {
        List<Listener> users = new ArrayList<>(userRegistration.getUsers().values());
        for (Listener user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }


        public void displayPopularAudios(List<Audio> likedAudios, Map<Audio, Integer> playCountByAudio) {
            List<Audio> popularAudios = likedAudios.stream()
                    .sorted(Comparator.comparingInt(playCountByAudio::get).reversed())
                    .limit(10)
                    .collect(Collectors.toList());

            System.out.println("Top 10 popular audios based on likes:");
            for (int i = 0; i < popularAudios.size(); i++) {
                System.out.println((i + 1) + ". " + popularAudios.get(i).getTitle());
            }
        }



        public void displayArtists(List<Artist> artists) {
            System.out.println("List of artists:");
            for (Artist artist : artists) {
                System.out.println("Username: " + artist.getUserName());
                System.out.println("Full Name: " + artist.getFullName());
                System.out.println("Email: " + artist.getEmail());
                System.out.println("Phone Number: " + artist.getPhoneNumber());
                System.out.println("Birth Date: " + artist.getBirthDate());
                System.out.println("Income: " + artist.getIncome());
                System.out.println("Biography: " + artist.getBiography());
                System.out.println("Followers: " + artist.getFollowers());
                System.out.println();
            }
        }

        public void displayAudios(List<Audio> audios) {
            System.out.println("List of audios:");
            for (Audio audio : audios) {
                System.out.println("Unique ID: " + audio.getUniqeId());
                System.out.println("Title: " + audio.getTitle());
                System.out.println("Artist: " + audio.getArtist());
                System.out.println("Play Count: " + audio.getPlayCount());
                System.out.println("Likes: " + audio.getLikes());
                System.out.println("Release Date: " + audio.getReleaseDate());
                System.out.println("Genre: " + audio.getGenre());
                System.out.println("Audio Link: " + audio.getAudioLink());
                System.out.println("Cover: " + audio.getCover());
                System.out.println();
            }
        }
    public void displayReports(List<Report> reports) {
        for (Report report : reports) {
            System.out.println(report);
        }
    }


        public void printAccountInfo(AccountUser accountUser) {
            System.out.println("Account Information:");
            System.out.println("Username: " + accountUser.getUserName());
            System.out.println("Full Name: " + accountUser.getFullName());
            System.out.println("Email: " + accountUser.getEmail());
            System.out.println("Phone Number: " + accountUser.getPhoneNumber());
            System.out.println("Birth Date: " + accountUser.getBirthDate());
        }
    }




