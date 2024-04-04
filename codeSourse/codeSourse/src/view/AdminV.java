package view;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Database;
import model.Report;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AdminV {
    public static void displayPopularAudios(List<Audio> likedAudios, Map<Audio, Integer> playCount) {
        List<Audio> popularAudios = likedAudios.stream()
                .sorted(Comparator.comparingInt(playCount::get).reversed())
                .limit(10)
                .collect(Collectors.toList());
        System.out.println("Top 10 popular audios based on likes:");
        for (int i = 0; i < popularAudios.size(); i++) {
            System.out.println((i + 1) + ". " + popularAudios.get(i).getTitle());
        }
    }

    public static void displayArtistInfo(String artistUsername) {
        boolean found = false;
        for (AccountUser user : Database.getInstance().getUsers()) {
            if (user instanceof Artist && user.getUserName().equals(artistUsername)) {
                found = true;
                Artist artist = (Artist) user;
                System.out.println("Artist Information:");
                System.out.println("Username: " + artist.getUserName());
                System.out.println("Full Name: " + artist.getFullName());
                System.out.println("Email: " + artist.getEmail());
                System.out.println("Phone Number: " + artist.getPhoneNumber());
                System.out.println("Birth Date: " + artist.getBirthDate());
                System.out.println("Income: " + artist.getIncome());
                System.out.println("Biography: " + artist.getBiography());
                System.out.println("Followers: " + artist.getFollowers());
                System.out.println();
                break;
            }
        }
        if (!found) {
            System.out.println("Artist with the username '" + artistUsername + "' not found.");
        }

    }

    public static void displayArtists() {
        for (AccountUser user : Database.getInstance().getUsers()) {
            if (user instanceof Artist) {
                System.out.println("Username: " + user.getUserName());
            }
        }
    }
    public static void displayAudios() {
        for(Audio audio : Database.getInstance().getAudiofiles()){
            System.out.println("Audio: " + audio.getTitle());
        }

    }


    public static void displayAudio(int audioId) {
        List<Audio> audios = Database.getInstance().getAudiofiles();
        boolean found = false;
        for (Audio audio : audios) {
            if (audio.getUniqeId() == audioId) {
                found = true;
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
                break;
            }
        }
        if (!found) {
            System.out.println("Audio with ID " + audioId + " not found.");
        }
    }

    public static void displayReports(List<Report> reports) {
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


