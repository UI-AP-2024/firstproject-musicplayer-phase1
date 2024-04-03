package view;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.Audio.Audio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistV {
    public void displayArtistFollowers(Artist artist) {
        List<AccountUser> followers = artist.getFollowers();
        if (followers.isEmpty()) {
            System.out.println("This artist has no followers.");
        } else {
            System.out.println("Followers of " + artist.getFullName() + ":");
            for (AccountUser follower : followers) {
                System.out.println(follower.getFullName());
            }
        }
    }

    public void displayPlayCount(List<Audio> audios) {
        Map<String, Integer> playCountMap = new HashMap<>();

        for (Audio audio : audios) {
            String title = audio.getTitle();
            int playCount = audio.getPlayCount();
            playCountMap.put(title, playCount);
        }

        System.out.println("Play count for each audio:");
        for (Map.Entry<String, Integer> entry : playCountMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
    public void displayArtistInfo(Artist artist) {
        System.out.println("Artist Information:");
        System.out.println("Username: " + artist.getUserName());
        System.out.println("Full Name: " + artist.getFullName());
        System.out.println("Email: " + artist.getEmail());
        System.out.println("Phone Number: " + artist.getPhoneNumber());
        System.out.println("Birth Date: " + artist.getBirthDate());
        System.out.println("Biography: " + artist.getBiography());
        System.out.println("Income: $" + artist.getIncome());
    }

}
