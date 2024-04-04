package view;

import controller.UserC;
import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Database;
import model.Playlist;

import java.util.List;

public class UserV {
    public static void playAudio(int musicId) {
        Audio audio = UserC.findAudioById(musicId);

        if (audio != null) {
            System.out.println("Information of audio with ID " + musicId + ":");
            System.out.println("Unique ID: " + audio.getUniqeId());
            System.out.println("Title: " + audio.getTitle());
            System.out.println("Artist: " + audio.getArtist());
            System.out.println("Play count: " + audio.getPlayCount());
            System.out.println("Likes: " + audio.getLikes());
            System.out.println("Release date: " + audio.getReleaseDate());
            System.out.println("Genre: " + audio.getGenre());
            System.out.println("Audio link: " + audio.getAudioLink());
            System.out.println("Cover: " + audio.getCover());
        } else {
            System.out.println("Error: Audio not found.");
        }
    }



    public void displayArtistInfoAndWorks(Artist artist) {
        System.out.println("Artist Information:");
        System.out.println("Username: " + artist.getUserName());
        System.out.println("Full Name: " + artist.getFullName());
        System.out.println("Income: $" + artist.getIncome());
        System.out.println("Biography: " + artist.getBiography());
        System.out.println("\nArtworks by " + artist.getUserName() + ":");
    }
    public static void viewPlaylists(List<Playlist> playlists) {
        System.out.println("Playlists created by the listener:");
        for (Playlist playlist : playlists) {
            System.out.println("Playlist: " + playlist.getName());
        }
    }
    public static void viewPlaylistContents(String playlistName) {
        Playlist playlist = findPlaylistByName(playlistName);
        if (playlist != null) {
            System.out.println("Playlist Name: " + playlist.getName());
            System.out.println("Created by: " + playlist.getUserName());
            System.out.println("Contents of playlist \"" + playlist.getName() + "\":");
            for (String audio : playlist.getAudios()) {
                System.out.println("  - " + audio);
            }
        } else {
            System.out.println("Error: Playlist not found.");
        }
    }

    private static Playlist findPlaylistByName(String playlistName) {
        for (Playlist playlist : Listener.getPlaylists()) {
            if (playlist.getName().equals(playlistName)) {
                return playlist;
            }
        }
        return null;
    }
    public static void printAccountInfo(AccountUser user) {
        System.out.println("Username: " + user.getUserName());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Birth Date: " + user.getBirthDate());
    }

    public static void displayFollowedArtists() {
        if (UserC.getFollowedArtists().isEmpty()) {
            System.out.println("You are not following any artists.");
        } else {
            System.out.println("Artists you are following:");
            for (Artist artist : UserC.getFollowedArtists()) {
                System.out.println(artist.getUserName());
            }
        }
    }
}
