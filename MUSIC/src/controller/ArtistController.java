package controller;

import model.model.audio.Audio;
import model.model.user.Artist;

public class ArtistController extends Controller {

    private void register(final Artist artist) throws Exception {
        if (database.isExistsUsername(artist.getUsername())) {
            throw new Exception("Username is exists");
        }

        database.saveArtist(artist);
    }

    private Artist fetchArtistInfo(final String username) throws Exception {
        final Artist artist = database.fetchArtistByUsername(username);
        if (artist == null) {
            throw new Exception("Not found listener by username, Username: " + username);
        }

        return artist;
    }

    public Audio fetchAudioInfo(final String username, final int audioId) throws Exception {
        final Artist artist = database.fetchArtistByUsername(username);
        if (artist == null) {
            throw new Exception("Not found artist by username, Username: " + username);
        }
        final Audio audio = database.fetchAudioById(audioId);
        if (audio == null) {
            throw new Exception("Not found audio by id, Id: " + audioId);

        }
        return audio;
    }

    public int fetchNumberOfPlayAudio(final String username, final int audioId) throws Exception {
        final Artist artist = database.fetchArtistByUsername(username);
        if (artist == null) {
            throw new Exception("Not found artist by username, Username: " + username);
        }

        final Audio audio = database.fetchAudioById(audioId);
        if (audio == null) {
            throw new Exception("Not found audio by id, Id: " + audioId);

        }

        return audio.getNumberOfPlay();
    }

    public Artist fetchAccountInfo(final String username) throws Exception {
        final Artist artist = database.fetchArtistByUsername(username);
        if (artist == null) {
            throw new Exception("Not found artist by username, Username: " + username);
        }
        return artist;
    }

}
