package controller;

import model.*;
import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.AccountUser.Listener.TypeOfListener.FreeListener;
import model.AccountUser.Listener.TypeOfListener.PremiumListener;
import model.AccountUser.Listener.TypeOfListener.premiumSub;
import model.Audio.Audio;
import view.Panels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class UserC {

    private static Map<String, Listener> users = new HashMap<>();

    //*********************************************

    public Map<String, Listener> getUsers() {
        return users;
    }


    //*********************************************
    public static boolean isValidUserEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(\\S+)$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$";
        return password.matches(passwordRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
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

    public static List<Audio> getLikeAudis(){
        return likeAudis;
    }

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
    public void likeAudis(Audio audio) {
        getLikeAudis().add(audio);
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
    //*********************************************


    public void followArtist(Artist artist) {
        followedArtists.add(artist);
        System.out.println(" is now following " + artist.getUserName());
    }

        private static List<Artist> followedArtists;

    public UserC() {
        this.followedArtists = new ArrayList<>();
    }

    public void displayFollowedArtists() {
        if (followedArtists.isEmpty()) {
            System.out.println("You are not following any artists.");
        } else {
            System.out.println("Artists you are following:");
            for (Artist artist : followedArtists) {
                System.out.println(artist.getUserName());
            }
        }
    }


    public void reportUser(Report report) {
        Report userReport = new Report();

        userReport.setReportingUser(report.getReportingUser());
        userReport.setReportedArtist(report.getReportedArtist());
        userReport.setDescription(report.getDescription());
        Database.getDatabase().getReports().add(userReport);

    }


    public void followArtist(Artist artist, AccountUser user) {
        artist.getFollowers().add(user);
        System.out.println(user.getUserName() + " is now following " + artist.getUserName());
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

    public void increaseAccountBalance(Listener listener, double amount) {
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
        for (AccountUser user : Database.getDatabase().getUsers()) {
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
        }
        String fullName = commands[4];
        String email = commands[5];
        if (!isValidUserEmail(email)) {
            System.out.println("Invalid email.");
        }
        String phoneNumber = commands[6];
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phoneNumber.");
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
        Database.getDatabase().getUsers().add(newUser);

        users.put(userName, newUser);

        System.out.println("Signed up successfully!");
        Panels.showFirstMeneu();
    }
}










