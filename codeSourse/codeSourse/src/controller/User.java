package controller;

import model.*;
import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.AccountUser.Listener.TypeOfListener.FreeListener;
import model.AccountUser.Listener.TypeOfListener.PremiumListener;
import model.AccountUser.Listener.TypeOfListener.premiumSub;
import model.Audio.Audio;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;


public class User {

    private List<Audio> likeAudis;

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
    public void likeAudis(Audio audio) {
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


//    public List<Audio> recommendSongs(Singer artist, List<Playlist> playlists, List<Genre> favoriteGenres, List<Album> allAlbums) {
//        List<Audio> recommendedSongs = new ArrayList<>();
//        for (Playlist playlist : playlists) {
//            for (String audioName : playlist.getAudios()) {
//                Audio song = findAudioByName(audioName, artist, allAlbums);
//                if (song != null && song.getArtist().equals(artist) && favoriteGenres.contains(song.getGenre())) {
//                    recommendedSongs.add(song);
//                }
//            }
//        }
//        return recommendedSongs.subList(0, Math.min(10, recommendedSongs.size()));
//    }
//
//    private Audio findAudioByName(String audioName, Singer singer, List<Audio> allAudios, List<Genre> favoriteGenres) {
//        for (Audio audio : allAudios) {
//            if (audio.getTitle().equals(audioName) && audio.getArtist().equals(singer.getFullName())) {
//                if (favoriteGenres.contains(audio.getGenre())) {
//                    return audio;
//                }
//            }
//        }
//        return null;
//    }
//
//    private Album findAlbumByName(String albumName, List<Album> allAlbums) {
//        for (Album album : allAlbums) {
//            if (album.getName().equals(albumName)) {
//                return album;
//            }
//        }
//        return null;
//    }

    public static void printAccountInfo(AccountUser user) {
        System.out.println("Username: " + user.getUserName());
        System.out.println("Full Name: " + user.getFullName());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Phone Number: " + user.getPhoneNumber());
        System.out.println("Birth Date: " + user.getBirthDate());
    }


    public void purchasePremiumSubscription(int packageChoice, Listener listener) {
        double price;
        int daysToAdd;
        switch (packageChoice) {
            case 1:
                price = premiumSub.ThirtyDays.getPrice();
                daysToAdd = 30;
                break;
            case 2:
                price = premiumSub.SixtyDays.getPrice();
                daysToAdd = 60;
                break;
            case 3:
                price = premiumSub.OneHundredEightyDays.getPrice();
                daysToAdd = 180;
                break;
            default:
                System.out.println("Invalid package choice.");
                return;
        }

        if (listener.getAccountBalance() >= price) {
            listener.setAccountBalance(listener.getAccountBalance() - price);
            System.out.println("Premium subscription purchased successfully!");

            // Update subscription end date
            Date currentDate = new Date();
            Date subscriptionEndDate = listener.getSubscriptionEndDate();
            if (subscriptionEndDate == null || currentDate.after(subscriptionEndDate)) {
                subscriptionEndDate = currentDate;
            }
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(subscriptionEndDate);
            calendar.add(Calendar.DATE, daysToAdd);
            subscriptionEndDate = calendar.getTime();
            listener.setSubscriptionEndDate(subscriptionEndDate);

            // Convert user to premium listener if necessary
            if (listener instanceof FreeListener) {
                // Upgrade listener to premium
                FreeListener freeListener = (FreeListener) listener;
                PremiumListener premiumListener = new PremiumListener(
                        freeListener.getUserName(),
                        freeListener.getPassword(),
                        freeListener.getFullName(),
                        freeListener.getEmail(),
                        freeListener.getPhoneNumber(),
                        freeListener.getBirthDate(),
                        freeListener.getAccountBalance(),
                        subscriptionEndDate,
                        daysToAdd);
                premiumListener.setPlaylists(freeListener.getPlaylists());
                premiumListener.setPlayCountByAudio(freeListener.getPlayCountByAudio());
                premiumListener.setFavoriteGenres(freeListener.getFavoriteGenres());
                // Set remaining subscription days
                premiumListener.setRemainingSubDays(daysToAdd);
                // Update listener reference
                listener = premiumListener;
                System.out.println("User upgraded to premium.");
            } else if (listener instanceof PremiumListener) {
                // Update remaining subscription days
                PremiumListener premiumListener = (PremiumListener) listener;
                premiumListener.setRemainingSubDays(premiumListener.getRemainingSubDays() + daysToAdd);
                System.out.println("Premium subscription renewed.");
            }
        } else {
            System.out.println("Insufficient account balance.");
        }
    }


    public class RecommendationSystem {

        public List<Audio> recommendSongs(Listener user, List<Audio> allAudios) {
            List<Audio> recommendedSongs = new ArrayList<>();

            // Favorite genres of the user
            List<Genre> favoriteGenres = user.getFavoriteGenres();

            // Audios that have been played most by the user
            Map<Audio, Integer> playCountByAudio = user.getPlayCountByAudio();

            // Artists that match the user's taste the most
            List<Artist> artistsWithBestMatch = findArtistsWithBestMatch(user, allAudios);

            // Audios that the user has liked
            List<Audio> likedAudios = likeAudis;

            // Select songs with the best match
            recommendedSongs.addAll(selectSongsWithBestMatch(allAudios, favoriteGenres, artistsWithBestMatch, likedAudios));

            // Return the top 10 recommended songs
            return recommendedSongs.subList(0, Math.min(10, recommendedSongs.size()));
        }

        public List<Artist> findArtistsWithBestMatch(Listener user, List<Audio> allAudios) {
            List<Artist> artistsWithBestMatch = new ArrayList<>();

            // Following artists of the user
            List<Artist> followingArtists = user.getFollowers();

            // Favorite genres of the user
            List<Genre> favoriteGenres = user.getFavoriteGenres();

            // Check if artists have matching genres with user's favorite genres based on following artists
            for (Artist followingArtist : followingArtists) {
                if (artistHasMatchingGenres(followingArtist, favoriteGenres, allAudios)) {
                    artistsWithBestMatch.add(followingArtist);
                }
            }

            return artistsWithBestMatch;
        }

        private boolean artistHasMatchingGenres(Artist artist, List<Genre> favoriteGenres, List<Audio> allAudios) {
            // Check if any of the artist's songs match the user's favorite genres
            for (Audio audio : allAudios) {
                if (artist.equals(audio.getArtist()) && favoriteGenres.contains(audio.getGenre())) {
                    return true;
                }
            }
            return false;
        }

        private List<Audio> selectSongsWithBestMatch(List<Audio> allAudios, List<Genre> favoriteGenres, List<Artist> artistsWithBestMatch, List<Audio> likedAudios) {
            List<Audio> selectedSongs = new ArrayList<>();

            for (Audio audio : allAudios) {
                if (favoriteGenres.contains(audio.getGenre()) &&
                        artistsWithBestMatch.contains(audio.getArtist()) &&
                        likedAudios.contains(audio)) {
                    selectedSongs.add(audio);
                }
            }

            return selectedSongs;
        }
    }
        public void increaseAccountBalance(Listener listener, double amount) {
            if (listener != null && listener instanceof Listener) {
                ((Listener) listener).setAccountBalance(((Listener) listener).getAccountBalance() + amount);
            } else {
                System.out.println("User is not a listener. Cannot increase account balance.");
            }
        }
    }









