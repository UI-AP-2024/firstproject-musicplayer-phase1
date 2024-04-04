package controller;
import model.*;
import model.AccountUser.AccountUser;
import model.AccountUser.Admin;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.AccountUser.Listener.TypeOfListener.FreeListener;
import model.AccountUser.Listener.TypeOfListener.PremiumListener;
import model.AccountUser.Listener.TypeOfListener.premiumSub;
import model.Audio.Audio;
import view.Panels;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static view.Panels.showAdminPanel;

public class UserC {

    private static Map<String, Listener> users = new HashMap<>();

    //*********************************************

    public Map<String, Listener> getUsers() {
        return users;
    }


    //*********************************************
    public static boolean isValidUserEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail|email|yahoo)\\.com$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])[A-Za-z0-9.@_-]{8,16}$";
        return password.matches(passwordRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^09[0-9]{9}$";
        return phoneNumber.matches(phoneRegex);
    }

    //*********************************************

    private static List<Genre> selectFavoriteGenres() {
        List<Genre> favoriteGenres = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select your favorite genres (maximum 4):");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.println("Enter the names of your favorite genres (separated by ,):");
        String input = scanner.nextLine();
        String[] names = input.split(",");
        for (String name : names) {
            String trimmedName = name.trim();
            for (Genre genre : genres) {
                if (genre.name().equalsIgnoreCase(trimmedName) && favoriteGenres.size() < 4) {
                    favoriteGenres.add(genre);
                    break;
                }
            }
        }
        return favoriteGenres;
    }


    //**********************************************
    private static List<Audio> likeAudis;

    public static List<Audio> getLikeAudis() {
        return likeAudis;
    }



        public static void likeAudio(int audioId) {
            Audio audio = findAudioById(audioId);
            if (audio != null) {
                audio.setLikes(audio.getLikes() + 1);
                likeAudis.add(audio);
                System.out.println("Audio with ID " + audioId + " liked successfully.");
            } else {
                System.out.println("Error: Audio not found.");
            }
        }

    public void addAudio(Playlist playlist, String audio) {
        playlist.addAudio(audio);
    }

    //*********************************************


        public static void addMusicToPlaylist ( String playlistName, int musicId) {
            Playlist playlist = findPlaylistById(playlistName);

            Audio audio = findAudioById(musicId);

            if (playlist != null && audio != null) {
                playlist.addAudio(audio.getTitle());
                System.out.println("Music '" + audio.getTitle() + "' added to playlist '" + playlist.getName() + "'.");
            } else {
                System.out.println("Error: Playlist or music not found.");
            }
        }

        private static Playlist findPlaylistById(String playlistName){
            for (Playlist playlist :Listener.getPlaylists() ) {
                if (playlist.getName() == playlistName) {
                    return playlist;
                }
            }
            return null;
        }

        public static Audio findAudioById(int musicId){
            for (Audio audio : Database.getInstance().getAudiofiles()) {
                if (audio.getUniqeId() == musicId) {
                    return audio;
                }
            }
            return null;
        }


        public static Playlist createPlaylist(String playlistName,  Listener user) {
            if (user instanceof PremiumListener || (user instanceof FreeListener && ((FreeListener) user).getPlaylists().size() < 3)) {
                int playlistId = generatePlaylistId(playlistName, user.getUserName());
                Playlist playlist = new Playlist(playlistId, playlistName, user.getUserName());
                Listener.getPlaylists().add(playlist);
                System.out.println("Playlist created successfully: " + playlistName);
                return playlist;
            } else {
                System.out.println("Error: You have reached the maximum limit of playlists.");
                return null;
            }
        }

        private static int generatePlaylistId(String playlistName, String username) {
            try {
                String combinedString = playlistName + username;
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(combinedString.getBytes());

                int playlistId = 0;
                for (int i = 0; i < Math.min(hash.length, 4); i++) {
                    playlistId += (hash[i] & 0xFF) << (8 * i);
                }
                return playlistId;
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return 0;
            }
        }



    //*********************************************


    public static List<Audio> searchByKeyword(List<Audio> audioFiles, String keyword) {
        return audioFiles.stream().filter(audio -> audio.getTitle().contains(keyword) || audio.getArtist().contains(keyword)).collect(Collectors.toList());
    }

    public static List<Audio> sortByPopularity(List<Audio> audioFiles) {
        return audioFiles.stream()
                .sorted(Comparator.comparingInt(Audio::getLikes).reversed())
                .collect(Collectors.toList());
    }

    public static List<Audio> sortByPlayCount(List<Audio> audioFiles) {
        return audioFiles.stream()
                .sorted(Comparator.comparingInt(Audio::getPlayCount).reversed())
                .collect(Collectors.toList());


    }

    public static List<Audio> filterByArtist(List<Audio> audios, String artistName) {
        List<Audio> filteredAudios = new ArrayList<>();
        for (Audio audio : audios) {
            if (audio.getArtist().equals(artistName)) {
                filteredAudios.add(audio);
            }
        }
        System.out.println("done successfully.");

        return filteredAudios;
    }

    public static List<Audio> filterByGenre(List<Audio> audios, String genre) {
        List<Audio> filteredAudios = new ArrayList<>();
        for (Audio audio : audios) {
            if (audio.getGenre().equals(genre)) {
                filteredAudios.add(audio);
            }
        }
        System.out.println("done successfully.");
        return filteredAudios;
    }

    public static List<Audio> filterByReleaseDate(List<Audio> audios, String startDate, String endDate) {
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
        System.out.println("done successfully.");

        return filteredAudios;
    }
    //*********************************************


    public static void followArtistByUsername(String artistUsername) {
        for (AccountUser user : Database.getInstance().getUsers()) {
            if (user instanceof Artist && user.getUserName().equals(artistUsername)) {
                followedArtists.add((Artist) user);
                System.out.println(user.getUserName() + " is now following " + artistUsername);
            }
        }
        System.out.println("No artist found with username: " + artistUsername);
    }

    private static List<Artist> followedArtists;

    public UserC() {
        this.followedArtists = new ArrayList<>();
    }
    public static List<Artist> getFollowedArtists(){
        return followedArtists;
    }



    public static void reportUser(AccountUser reportingUser, String artistUsername, String description) {
        Artist reportedArtist = findArtistByUsername(artistUsername);

        if (reportedArtist != null) {
            Report userReport = new Report();
            userReport.setReportingUser(reportingUser);
            userReport.setReportedArtist(reportedArtist);
            userReport.setDescription(description);
            Database.getInstance().getReports().add(userReport);
            System.out.println("Report submitted successfully.");
        } else {
            System.out.println("Error: Artist not found.");
        }
    }

    private static Artist findArtistByUsername(String artistUsername) {
        for (AccountUser user : Database.getInstance().getUsers()) {
            if (user instanceof Artist && user.getUserName().equals(artistUsername)) {
                return (Artist) user;
            }
        }
        return null;
    }


    public void followArtist(Artist artist, AccountUser user) {
        artist.getFollowers().add(user);
        System.out.println(user.getUserName() + " is now following " + artist.getUserName());
    }


    public static void purchasePremiumSubscription(int daysToAdd, Listener listener) {
        double price;
        switch (daysToAdd) {
            case 30:
                price = premiumSub.ThirtyDays.getPrice();;
                break;
            case 60:
                price = premiumSub.SixtyDays.getPrice();
                break;
            case 180:
                price = premiumSub.OneHundredEightyDays.getPrice();
                break;
            default:
                System.out.println("Invalid number of days.");
                return;
        }

        if (listener.getAccountBalance() >= price) {
            listener.setAccountBalance(listener.getAccountBalance() - price);
            System.out.println("Premium subscription purchased successfully!");

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

            if (listener instanceof FreeListener) {
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
                premiumListener.setRemainingSubDays(daysToAdd);
                listener = premiumListener;
                System.out.println("User upgraded to premium.");
            } else if (listener instanceof PremiumListener) {
                PremiumListener premiumListener = (PremiumListener) listener;
                premiumListener.setRemainingSubDays(premiumListener.getRemainingSubDays() + daysToAdd);
                System.out.println("Premium subscription renewed.");
            }
        } else {
            System.out.println("Insufficient account balance.");
        }
    }

    public static List<Audio> recommendSongs(Listener user, List<Audio> allAudios, List<Audio> likeAudis) {
        List<Audio> recommendedSongs = new ArrayList<>();

        List<Genre> favoriteGenres = user.getFavoriteGenres();

        Map<Audio, Integer> playCountByAudio = user.getPlayCountByAudio();

        List<Artist> artistsWithBestMatch = findArtistsWithBestMatch(user, allAudios);

        List<Audio> likedAudios = likeAudis;

        recommendedSongs.addAll(selectSongsWithBestMatch(allAudios, favoriteGenres, artistsWithBestMatch, likedAudios));

        return recommendedSongs.subList(0, Math.min(10, recommendedSongs.size()));
    }

    public static List<Artist> findArtistsWithBestMatch(Listener user, List<Audio> allAudios) {
        List<Artist> artistsWithBestMatch = new ArrayList<>();

        List<Artist> followingArtists = followedArtists;

        List<Genre> favoriteGenres = user.getFavoriteGenres();

        for (Artist followingArtist : followingArtists) {
            if (artistHasMatchingGenres(followingArtist, favoriteGenres, allAudios)) {
                artistsWithBestMatch.add(followingArtist);
            }
        }

        return artistsWithBestMatch;
    }

    private static boolean artistHasMatchingGenres(Artist artist, List<Genre> favoriteGenres, List<Audio> allAudios) {
        for (Audio audio : allAudios) {
            if (artist.equals(audio.getArtist()) && favoriteGenres.contains(audio.getGenre())) {
                return true;
            }
        }
        return false;
    }

    private static List<Audio> selectSongsWithBestMatch(List<Audio> allAudios, List<Genre> favoriteGenres, List<Artist> artistsWithBestMatch, List<Audio> likedAudios) {
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

    public static void increaseAccountBalance(Listener listener, double amount) {
        if (listener != null && listener instanceof Listener) {
            ((Listener) listener).setAccountBalance(((Listener) listener).getAccountBalance() + amount);
        } else {
            System.out.println("User is not a listener. Cannot increase account balance.");
        }
    }

    static void showUserPanel() {

    }

    public static void loginAll(String username, String password) {
        boolean userFound = false;
        for (AccountUser user : Database.getInstance().getUsers()) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                userFound = true;
                if (user instanceof Listener) {
                    System.out.println("You are a listener. Opening listener panel...");
                    Panels.showUserPanel((Listener) user);
                } else if (user instanceof Podcaster) {
                    System.out.println("You are a podcaster. Opening podcaster panel...");
                    showUserPanel();
                } else if (user instanceof Singer) {
                    System.out.println("You are a singer. Opening singer panel...");
                    showUserPanel();
                }else if (user instanceof Admin) {
                        System.out.println("You are a Admin. Opening Admin panel...");
                    showAdminPanel((Admin) user);
                }
                break;
            }
        }
        if (!userFound) {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    public static void signUpL(String[] commands) {

        String userName = commands[2];
        if (users.containsKey(userName)) {
            System.out.println("Error: userName already exists.");
            return;
        }
        String password = commands[3];
        if (!isValidPassword(password)) {
            System.out.println("Invalid password.");
            return;
        }
        String fullName = commands[4];
        String email = commands[5];
        if (!isValidUserEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }
        String phoneNumber = commands[6];
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phoneNumber.");
            return;

        }
        String dateOfBirth = commands[7];
        Date birthDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = dateFormat.parse(dateOfBirth);
        } catch (ParseException e) {
            System.out.println("Invalid birthDate format.");
            return;
        }
        Listener newUser = new Listener(userName, password, fullName, email, phoneNumber, birthDate, 0.0, null);
        newUser.setFavoriteGenres(selectFavoriteGenres());
        newUser.setAccountBalance(50.0);
        Database.getInstance().getUsers().add(newUser);

        users.put(userName, newUser);

        System.out.println("Signed up successfully!");
        Panels.showFirstMeneu();
    }

}










