package view;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.Audio.Audio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArtistV {
    public  void displayArtistFollowers(Artist artist) {
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

}
