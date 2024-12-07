package controller;

import model.enums.Genre;
import model.enums.PremiumSubscriptionPackages;
import model.model.audio.Audio;
import model.model.audio.Music;
import model.model.audio.PlayList;
import model.model.audio.Podcast;
import model.model.report.Report;
import model.model.user.*;
import model.validation.Validation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ListenerControllerImp extends Controller implements FreeListenerController, PremiumListenerController {

    @Override
    public void register(final FreeListener listener) throws Exception {

        if (listener == null) {
            throw new NullPointerException("Listener is null");
        }

        Validation.usernameValidation(listener.getUsername());
        Validation.emailValidation(listener.getEmail());
        Validation.phoneValidation(listener.getPhoneNumber());
        Validation.isEmpty(listener.getPassword(), "Password");
        Validation.isEmpty(listener.getFirstname(), "Firstname");
        Validation.isEmpty(listener.getLastname(), "Lastname");

        if (database.isExistsUsername(listener.getUsername())) {
            throw new Exception("Username is exists");
        }

        if (listener.getFavoriteGenre() == null || listener.getFavoriteGenre().isEmpty() || listener.getFavoriteGenre().size() > 4) {
            throw new Exception("Please select max 4 genre");
        }

        listener.setAccountCredit(50);

        database.saveFreeListener(listener);

    }

    @Override
    public int newFreeListenerPlayList(final String username, final String name) throws Exception {

        final FreeListener freeListener = database.fetchFreeListenerByUsername(username);

        if (freeListener == null) {
            throw new Exception("Not found free listener by username, Username: " + username);
        }

        if (freeListener.getPlayLists().size() >= FreeListener.LIMIT_NUMBER_OF_PLAY_LIST) {
            throw new Exception("Full limit play list");
        }

        final int playListId = database.newFreeListenerPlayList(freeListener, name);

        if (playListId <= 0) {
            throw new Exception("Error save play list");
        }

        return playListId;
    }

    @Override
    public int newPremiumListenerPlayList(final String username, final String name) throws Exception {

        final PremiumListener premiumListener = database.fetchPremiumListenerByUsername(username);

        if (premiumListener == null) {
            throw new Exception("Not found premium listener by username, Username: " + username);
        }

        final int playListId = database.newPremiumListenerPlayList(premiumListener, name);

        if (playListId <= 0) {
            throw new Exception("Error save play list");
        }

        return playListId;
    }

    @Override
    public void addAudioFreeListenerPlayList(final int playListId, final Audio audio) throws Exception {

        final PlayList playList = database.fetchFreeListenerPlayListById(playListId);
        if (playList == null) {
            throw new Exception("Not found play list by id, PlayListId: " + playListId);
        }

        final FreeListener freeListener = database.fetchFreeListenerByPlayListId(playListId);

        if (playList.getAudioFiles() == null) {
            playList.setAudioFiles(new ArrayList<>());
        }

        if (playList.getAudioFiles().size() >= FreeListener.LIMIT_NUMBER_OF_ADD_MUSIC_PLAY_LIST) {
            throw new Exception("Full limit play list");
        }

        database.addAudioPlayList(freeListener, playListId, audio);

    }

    @Override
    public void addAudioPremiumListenerPlayList(final int playListId, final Audio audio) throws Exception {

        final PlayList playList = database.fetchPremiumListenerPlayListById(playListId);
        if (playList == null) {
            throw new Exception("Not found play list by id, PlayListId: " + playListId);
        }

        final PremiumListener premiumListener = database.fetchPremiumListenerByPlayListId(playListId);

        database.addAudioPlayList(premiumListener, playListId, audio);

    }

    @Override
    public void likeAudio(final String username, final int audioId) throws Exception {

        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        if (database.fetchFreeListenerByUsername(username) != null) {
            database.likeFreeListenerAudio(username, audioId);
        } else if (database.fetchPremiumListenerByUsername(username) != null) {
            database.likePremiumListenerAudio(username, audioId);
        }

    }


    @Override
    public List<Artist> fetchFollowings(final String username) throws Exception {

        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        return database.fetchFollowings(username);
    }


    @Override
    public void reportArtist(final String username, final String artistUsername, final String description) throws Exception {

        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }
        if (!database.isExistsUsername(artistUsername)) {
            throw new Exception("Not found artist by username, Username: " + artistUsername);
        }

        final Artist artist = database.fetchArtistByUsername(username);

        final Report report = new Report();
        report.setDescription(description);
        report.setReportedArtist(artist);

        final FreeListener freeListener = database.fetchFreeListenerByUsername(username);
        if (freeListener != null) {
            report.setReportedBy(freeListener);
        } else {
            final PremiumListener premiumListener = database.fetchPremiumListenerByUsername(username);
            if (premiumListener != null) {
                report.setReportedBy(premiumListener);
            }
        }

        database.reportArtist(report);

    }

    @Override
    public void followArtist(final String username, final String artistUsername) throws Exception {
        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }
        if (!database.isExistsUsername(artistUsername)) {
            throw new Exception("Not found artist by username, Username: " + artistUsername);
        }

        final Artist artist = database.fetchArtistByUsername(artistUsername);

        final User user = artist.getFollowers().stream().filter(item -> item.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
        if (user != null) {
            throw new Exception("Has exists in followers, Username: " + username);
        }

        final FreeListener freeListener = database.fetchFreeListenerByUsername(username);
        if (freeListener != null) {
            database.followArtist(freeListener, artistUsername);
        } else {
            final PremiumListener premiumListener = database.fetchPremiumListenerByUsername(username);
            if (premiumListener != null) {
                database.followArtist(premiumListener, artistUsername);
            }
        }

    }

    @Override
    public FreeListener fetchFreeListener(final String username) throws Exception {
        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }
        return database.fetchFreeListenerByUsername(username);
    }

    @Override
    public PremiumListener fetchPremiumListener(final String username) throws Exception {
        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }
        return database.fetchPremiumListenerByUsername(username);
    }

    @Override
    public List<Audio> fetchAudioByGenre(final String username) throws Exception {
        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        final List<Music> music = new ArrayList<>();
        final List<Podcast> podcasts = new ArrayList<>();

        final FreeListener freeListener = database.fetchFreeListenerByUsername(username);
        if (freeListener != null) {
            music.addAll(database.fetchMusicByGenre(freeListener.getFavoriteGenre()));
            podcasts.addAll(database.fetchPodcastByGenre(freeListener.getFavoriteGenre()));
        } else {
            final PremiumListener premiumListener = database.fetchPremiumListenerByUsername(username);
            if (premiumListener != null) {
                music.addAll(database.fetchMusicByGenre(premiumListener.getFavoriteGenre()));
                podcasts.addAll(database.fetchPodcastByGenre(premiumListener.getFavoriteGenre()));
            }
        }

        if (music.isEmpty() || podcasts.isEmpty()) {
            return null;
        }

        final List<Audio> audio = new ArrayList<>();

        Collections.shuffle(music);
        Collections.shuffle(podcasts);

        audio.addAll(music.stream().limit(music.size() / 2).collect(Collectors.toList()));
        audio.addAll(podcasts.stream().limit(podcasts.size() / 3).collect(Collectors.toList()));

        return audio;
    }

    @Override
    public Listener validationLoginInfo(final String username, final String password) throws Exception {

        Validation.usernameValidation(username);
        Validation.isEmpty(password, "Password");

        Listener listener = database.fetchFreeListenerByUsernameAndPassword(username, password);
        if (listener == null) {
            listener = database.fetchPremiumListenerByUsernameAndPassword(username, password);
        }

        if (listener == null) {
            throw new Exception("Invalid login info, Username: " + username + " , Password: " + password);
        }

        return listener;
    }

    @Override
    public void buyPremium(final String username, final PremiumSubscriptionPackages premiumSubscriptionPackages) throws Exception {

        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        final Listener listener = database.fetchFreeListenerByUsername(username);
        if (listener == null) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        final PremiumListener premiumListener = new PremiumListener();
        premiumListener.setNumberOfRemainingDaysSubscription(premiumSubscriptionPackages.days);
        premiumListener.setUsername(listener.getUsername());
        premiumListener.setPassword(listener.getPassword());
        premiumListener.setFirstname(listener.getFirstname());
        premiumListener.setLastname(listener.getLastname());
        premiumListener.setEmail(premiumListener.getEmail());
        premiumListener.setAccountCredit(listener.getAccountCredit());
        premiumListener.setPhoneNumber(listener.getPhoneNumber());
        premiumListener.setDateOfBirth(listener.getDateOfBirth());
        premiumListener.setPlayLists(listener.getPlayLists() == null ? new ArrayList<>() : new ArrayList<>(listener.getPlayLists()));
        premiumListener.setNumberOfPlayMusic(listener.getNumberOfPlayMusic() == null ? new HashMap<>() : new HashMap<>(listener.getNumberOfPlayMusic()));
        premiumListener.setSubscriptionExpirationDate(LocalDate.now().plusDays(premiumSubscriptionPackages.days));
        premiumListener.setFavoriteGenre(listener.getFavoriteGenre());

        database.removeFreeListener(username);
        database.savePremiumListener(premiumListener);
    }

    @Override
    public void updateFavouriteGenres(final String username, final List<Genre> genres) throws Exception {

        final FreeListener freeListener = database.fetchFreeListenerByUsername(username);
        if (freeListener != null) {
            freeListener.setFavoriteGenre(genres);
            database.updateFreeListener(freeListener);
            return;
        }

        final PremiumListener premiumListener = database.fetchPremiumListenerByUsername(username);
        if (premiumListener != null) {
            premiumListener.setFavoriteGenre(genres);
            database.updatePremiumListener(premiumListener);
            return;
        }

        throw new Exception("Not found listener");

    }

    @Override
    public synchronized void renewalPremium(final String username, final PremiumSubscriptionPackages premiumSubscriptionPackages) throws Exception {

        if (!database.isExistsUsername(username)) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        final PremiumListener listener = database.fetchPremiumListenerByUsername(username);
        if (listener == null) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        LocalDate subscriptionExpirationDate = listener.getSubscriptionExpirationDate();
        if (subscriptionExpirationDate == null || subscriptionExpirationDate.isAfter(LocalDate.now())) {
            subscriptionExpirationDate = LocalDate.now();
        }
        subscriptionExpirationDate = subscriptionExpirationDate.plusDays(premiumSubscriptionPackages.days);

        listener.setSubscriptionExpirationDate(subscriptionExpirationDate);

        database.updatePremiumListener(listener);

    }


}
