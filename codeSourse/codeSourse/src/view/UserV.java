package view;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.Audio.Audio;
import model.Playlist;

import java.util.List;

public class UserV {
    public void playAudio(Audio Audi, String audio) {
        System.out.println(
                "uniqueId=" + Audi.getUniqeId() +
                        ", title='" + Audi.getTitle() + '\'' +
                        ", artist='" + Audi.getArtist() + '\'' +
                        ", playCount=" + Audi.getPlayCount() +
                        ", likes=" + Audi.getLikes() +
                        ", releaseDate=" + Audi.getReleaseDate() +
                        ", genre=" + Audi.getGenre() +
                        ", audioLink='" + Audi.getAudioLink() + '\'' +
                        ", cover='" + Audi.getCover() + '\'' +
                        '}'
        );
    }
    public void displayArtistFollowings(Artist artist) {
        List<AccountUser> followers = artist.getFollowers();
        System.out.println("Followers of " + artist.getUserName() + ":");
        for (AccountUser follower : followers) {
            System.out.println(follower.getUserName());
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
    public void viewPlaylists(List<Playlist> playlists) {
        System.out.println("Playlists created by the listener:");
        for (Playlist playlist : playlists) {
            System.out.println("Playlist: " + playlist.getName());
        }
    }
    public void viewPlaylistContents(Playlist playlist) {
        System.out.println("Playlist Name: " + playlist.getName());
        System.out.println("Created by: " + playlist.getUserName());
        System.out.println("Contents of playlist \"" + playlist.getName() + "\":");
        for (String audio : playlist.getAudios()) {
            System.out.println("  - " + audio);
        }
    }
    public static void printAccountInfo(AccountUser user) {
        System.out.println("Username: " + user.getUserName());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Birth Date: " + user.getBirthDate());
    }
}
