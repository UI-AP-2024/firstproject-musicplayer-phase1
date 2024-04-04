package model.database;

import model.enums.Genre;
import model.enums.SequenceName;
import model.model.audio.*;
import model.model.report.Report;
import model.model.user.*;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Database {
    private static Database database;

    private final List<String> usernames;
    private final List<User> users;
    private final List<Artist> artists;
    private final List<Music> music;
    private final List<Podcast> podcasts;
    private final List<FreeListener> freeListeners;
    private final List<PremiumListener> premiumListeners;
    private final List<Singer> singers;
    private final List<String> audioFiles;
    private final List<Report> reports;

    private Map<String, Integer> sequences;

    private Database() {
        usernames = new ArrayList<>();
        users = new ArrayList<>();
        artists = new ArrayList<>();
        audioFiles = new ArrayList<>();
        reports = new ArrayList<>();
        freeListeners = new ArrayList<>();
        premiumListeners = new ArrayList<>();
        music = new ArrayList<>();
        podcasts = new ArrayList<>();
        singers = new ArrayList<>();
        initialSequence();
    }

    private void initialSequence() {
        sequences = new HashMap<>();
        Arrays.stream(SequenceName.values()).forEach(value -> sequences.put(value.name(), 0));
    }

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    public boolean isExistsUsername(final String username) {
        return usernames.stream().filter(item -> item.equalsIgnoreCase(username)).findFirst().orElse(null) != null;
    }

    public FreeListener fetchFreeListenerByUsername(final String username) {
        return freeListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public PremiumListener fetchPremiumListenerByUsername(final String username) {
        return premiumListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public Artist fetchArtistByUsername(final String username) {
        return artists.stream().filter(item -> item.getUsername().equalsIgnoreCase(username)).findFirst().orElse(null);
    }

    public void saveFreeListener(final FreeListener listener) throws Exception {

        if (isExistsUsername(listener.getUsername())) {
            throw new Exception("Username is exists");
        }

        usernames.add(listener.getUsername());
        freeListeners.add(listener);

    }

    public int newFreeListenerPlayList(final FreeListener freeListener, final String name) throws Exception {
        final PlayList playList = new PlayList();
        playList.setId(fetchNextValSequence(SequenceName.PLAY_LIST));
        playList.setName(name);
        playList.setCreatedBy(String.format("%s %s", freeListener.getFirstname(), freeListener.getLastname()));
        freeListener.getPlayLists().add(playList);

        final int indexFreeListener = IntStream.range(0, freeListeners.size()).filter(i -> freeListeners.get(i).getUsername().equalsIgnoreCase(freeListener.getUsername())).findFirst().orElse(-1);

        if (indexFreeListener == -1) {
            throw new Exception("Not found free listener by username, Username: " + usernames);
        }

        freeListeners.set(indexFreeListener, freeListener);

        return playList.getId();
    }

    public int newPremiumListenerPlayList(final PremiumListener premiumListener, final String name) throws Exception {
        final PlayList playList = new PlayList();
        playList.setId(fetchNextValSequence(SequenceName.PLAY_LIST));
        playList.setName(name);
        playList.setCreatedBy(String.format("%s %s", premiumListener.getFirstname(), premiumListener.getLastname()));
        premiumListener.getPlayLists().add(playList);

        final int indexFreeListener = IntStream.range(0, freeListeners.size()).filter(i -> freeListeners.get(i).getUsername().equalsIgnoreCase(premiumListener.getUsername())).findFirst().orElse(-1);

        if (indexFreeListener == -1) {
            throw new Exception("Not found free listener by username, Username: " + usernames);
        }

        premiumListeners.set(indexFreeListener, premiumListener);

        return playList.getId();
    }

    public PlayList fetchFreeListenerPlayListById(final int id) {
        return freeListeners.stream().flatMap(freeListener -> freeListener.getPlayLists().stream()).filter(playList -> playList.getId() == id).findFirst().orElse(null);
    }

    public FreeListener fetchFreeListenerByUsernameAndPassword(final String username, final String password) {
        return freeListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username) && item.getPassword().equals(password)).findFirst().orElse(null);
    }

    public User fetchUserByUsernamePassword(final String username, final String password) {
        final FreeListener freeListener = freeListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username) && item.getPassword().equals(password)).findFirst().orElse(null);
        if (freeListener != null) {
            return freeListener;
        }
        final PremiumListener premiumListener = premiumListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username) && item.getPassword().equals(password)).findFirst().orElse(null);
        if (premiumListener != null) {
            return premiumListener;
        }
        if (Admin.getAdmin().getUsername().equalsIgnoreCase(username) && Admin.getAdmin().getPassword().equals(password)) {
            return Admin.getAdmin();
        }
        return singers.stream().filter(item -> item.getUsername().equalsIgnoreCase(username) && item.getPassword().equals(password)).findFirst().orElse(null);
    }

    public PremiumListener fetchPremiumListenerByUsernameAndPassword(final String username, final String password) {
        return premiumListeners.stream().filter(item -> item.getUsername().equalsIgnoreCase(username) && item.getPassword().equals(password)).findFirst().orElse(null);
    }

    public FreeListener fetchFreeListenerByPlayListId(final int id) {
        return freeListeners.stream().filter(freeListener -> freeListener.getPlayLists().stream().anyMatch(playList -> playList.getId() == id)).findFirst().orElse(null);
    }

    public PremiumListener fetchPremiumListenerByPlayListId(final int id) {
        return premiumListeners.stream().filter(premiumListener -> premiumListener.getPlayLists().stream().anyMatch(playList -> playList.getId() == id)).findFirst().orElse(null);
    }

    public PlayList fetchPremiumListenerPlayListById(final int id) {
        return premiumListeners.stream().flatMap(premiumListener -> premiumListener.getPlayLists().stream()).filter(playList -> playList.getId() == id).findFirst().orElse(null);
    }

    public void addAudioPlayList(final FreeListener listener, final int playListId, final Audio audio) {
        listener.getPlayLists().stream().filter(playList -> playList.getId() == playListId).forEachOrdered(playList -> {
            if (playList.getAudioFiles() == null) {
                playList.setAudioFiles(new ArrayList<>());
            }
            playList.getAudioFiles().add(audio);
        });
    }

    public void addAudioPlayList(final PremiumListener listener, final int playListId, final Audio audio) {
        listener.getPlayLists().stream().filter(playList -> playList.getId() == playListId).forEachOrdered(playList -> {
            if (playList.getAudioFiles() == null) {
                playList.setAudioFiles(new ArrayList<>());
            }
            playList.getAudioFiles().add(audio);
        });
    }

    public synchronized void likeFreeListenerAudio(final String username, final int audioId) {
        freeListeners.stream()
                .filter(item -> item.getUsername().equalsIgnoreCase(username))
                .flatMap(item -> item.getPlayLists().stream())
                .flatMap(item -> item.getAudioFiles().stream())
                .filter(item -> item.getId() == audioId)
                .forEachOrdered(item -> item.setNumberOfLikes(item.getNumberOfLikes() + 1));
    }

    public synchronized void likePremiumListenerAudio(final String username, final int audioId) {
        premiumListeners.stream()
                .filter(item -> item.getUsername().equalsIgnoreCase(username))
                .flatMap(item -> item.getPlayLists().stream())
                .flatMap(item -> item.getAudioFiles().stream())
                .filter(item -> item.getId() == audioId)
                .forEachOrdered(item -> item.setNumberOfLikes(item.getNumberOfLikes() + 1));
    }

    public List<Artist> fetchFollowings(final String username) {
        return this.artists.stream()
                .filter(item ->
                        item.getFollowers().stream()
                                .filter(follower -> follower.getUsername().equalsIgnoreCase(username))
                                .findFirst()
                                .orElse(null) != null
                )
                .collect(Collectors.toList());
    }

    public void reportArtist(final Report report) {
        reports.add(report);
    }

    public void followArtist(final User user, final String artistUsername) {
        artists.stream().filter(item -> item.getUsername().equalsIgnoreCase(artistUsername)).forEachOrdered(item -> item.getFollowers().add(user));
    }

    public List<Music> fetchMusicByGenre(final List<Genre> genre) {
        return music.stream().filter(item -> genre.stream().anyMatch(itemGenre -> itemGenre.equals(item.getGenre()))).collect(Collectors.toList());
    }

    public Music fetchMusicById(final int id) {
        return music.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public Podcast fetchPodcastById(final int id) {
        return podcasts.stream().filter(item -> item.getId() == id).findFirst().orElse(null);
    }

    public Audio fetchAudioById(final int id) {
        final Music music = fetchMusicById(id);
        if (music != null) {
            return music;
        }
        return fetchPodcastById(id);
    }

    public List<Podcast> fetchPodcastByGenre(final List<Genre> genre) {
        return podcasts.stream().filter(item -> genre.stream().anyMatch(itemGenre -> itemGenre.equals(item.getGenre()))).collect(Collectors.toList());
    }

    public int fetchNextValSequence(final SequenceName sequenceName) {
        final int sequence = sequences.get(sequenceName.name()) + 1;
        sequences.replace(sequenceName.name(), sequence);
        return sequence;
    }

    public void savePremiumListener(final PremiumListener premiumListener) {
        usernames.add(premiumListener.getUsername());
        premiumListeners.add(premiumListener);
    }

    public void removeFreeListener(final String username) {
        usernames.remove(username);
        IntStream.range(0, freeListeners.size()).filter(i -> freeListeners.get(i).getUsername().equalsIgnoreCase(username)).forEach(freeListeners::remove);
    }

    public void updateFreeListener(final FreeListener freeListener) {
        IntStream.range(0, freeListeners.size()).filter(i -> freeListeners.get(i).getUsername().equalsIgnoreCase(freeListener.getUsername())).forEachOrdered(index -> freeListeners.set(index, freeListener));
    }

    public void updatePremiumListener(final PremiumListener premiumListener) {
        IntStream.range(0, premiumListeners.size()).filter(i -> premiumListeners.get(i).getUsername().equalsIgnoreCase(premiumListener.getUsername())).forEachOrdered(index -> premiumListeners.set(index, premiumListener));
    }

    public List<Music> fetchMusicPopular() {
        return music.stream().sorted((o1, o2) -> Integer.compare(o2.getNumberOfLikes(), o1.getNumberOfLikes())).limit(5).collect(Collectors.toList());
    }

    public List<Podcast> fetchPodcastPopular() {
        return podcasts.stream().sorted((o1, o2) -> Integer.compare(o2.getNumberOfLikes(), o1.getNumberOfLikes())).limit(5).collect(Collectors.toList());
    }

    public List<Music> listMusic() {
        return new ArrayList<>(music);
    }

    public List<Podcast> listPodcast() {
        return new ArrayList<>(podcasts);
    }

    public List<Report> listReport() {
        return new ArrayList<>(reports);
    }

    public void saveArtist(final Artist artist) {
        usernames.add(artist.getUsername());
        artists.add(artist);
    }

    public void savePodcast(final Podcast podcast) {
        podcast.setId(fetchNextValSequence(SequenceName.AUDIO));
        podcasts.add(podcast);
    }

    public void saveSigner(final Singer singer) {
        usernames.add(singer.getUsername());
        singers.add(singer);
    }

    public Singer fetchSingerByUsername(final String singerUsername) {
        return singers.stream().filter(item -> item.getUsername().equalsIgnoreCase(singerUsername)).findFirst().orElse(null);
    }

    public void addAlbum(final String singerUsername, final Album album) {
        singers.stream().filter(singer -> singer.getUsername().equalsIgnoreCase(singerUsername)).forEachOrdered(singer -> singer.getAlbums().add(album));
    }

    public void saveMusic(final Music music) {

    }

    public List<Artist> getArtists() {
        return artists;
    }
}
