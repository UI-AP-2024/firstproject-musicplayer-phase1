package controller;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.Audio.Audio;
import model.Database;
import model.Playlist;
import model.Report;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class Listener {

    private List<String> likeAudis;

    public void addAudio(Playlist playlist, String audio) {
        playlist.addAudio(audio);
    }


    //*********************************************
    public static Playlist createPlaylist(int id, String name, String userName, boolean isPremium) {
        if (!isPremium) {
            if (id > 3) {
                System.out.println("Error: Maximum number of playlists reached for a normal user.");
                return null;
            }
        }

        return new Playlist(id, name, userName);
    }

    public void addAudioToPlaylist(Playlist playlist, String audio, boolean isPremiumUser) {
        if (!isPremiumUser && playlist.getAudios().size() >= 10) {
            System.out.println("Error: Maximum number of audio files reached for a free user.");
            return;
        }

        playlist.addAudio(audio);
    }

    //*********************************************
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

    //*********************************************
    public void likeAudis(String audio) {
        likeAudis.add(audio);
    }


    public List<Audio> searchByKeyword(List<Audio> audioFiles, String keyword) {
        return audioFiles.stream().filter(audio -> audio.getTitle().contains(keyword) || audio.getArtist().contains(keyword)).collect(Collectors.toList());
    }

    public List<Audio> sortByPopularity(List<Audio> audioFiles) {
        return audioFiles.stream()
                .sorted(Comparator.comparingInt(Audio::getLikes).reversed())
                .collect(Collectors.toList());
    }

    public List<Audio> sortByPlayCount(List<Audio> audioFiles) {
        return audioFiles.stream()
                .sorted(Comparator.comparingInt(Audio::getPlayCount).reversed())
                .collect(Collectors.toList());
    }

    public List<Audio> filterByArtist(List<Audio> audios, String artistName) {
        List<Audio> filteredAudios = new ArrayList<>();
        for (Audio audio : audios) {
            if (audio.getArtist().equals(artistName)) {
                filteredAudios.add(audio);
            }
        }
        return filteredAudios;
    }

    public List<Audio> filterByGenre(List<Audio> audios, String genre) {
        List<Audio> filteredAudios = new ArrayList<>();
        for (Audio audio : audios) {
            if (audio.getGenre().equals(genre)) {
                filteredAudios.add(audio);
            }
        }
        return filteredAudios;
    }

    public List<Audio> filterByReleaseDate(List<Audio> audios, String startDate, String endDate) {
        List<Audio> filteredAudios = new ArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date start = dateFormat.parse(startDate);
            Date end = dateFormat.parse(endDate);
            for (Audio audio : audios) {
                Date releaseDate = dateFormat.parse(String.valueOf(audio.getReleaseDate()));
                if (releaseDate.compareTo(start) >= 0 && releaseDate.compareTo(end) <= 0) {
                    filteredAudios.add(audio);
                }
            }
        } catch (ParseException e) {
            System.out.println("Error parsing dates: " + e.getMessage());
        }
        return filteredAudios;
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


    public void reportUser(Report report) {
        Report userReport = new Report();

        userReport.setReportingUser(report.getReportingUser());
        userReport.setReportedArtist(report.getReportedArtist());
        userReport.setDescription(report.getDescription());
        Database.addreport(userReport);

    }


    public void followArtist(Artist artist, AccountUser user) {
        artist.getFollowers().add(user);
        System.out.println(user.getUserName() + " is now following " + artist.getUserName());
    }
}







