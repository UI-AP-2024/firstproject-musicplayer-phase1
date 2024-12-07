package controller;

import model.enums.Genre;
import model.model.audio.Audio;
import model.model.user.Artist;
import model.model.user.Listener;

import java.util.List;

public interface ListenerController {
    void likeAudio(final String username, final int audioId) throws Exception;

    List<Artist> fetchFollowings(final String username) throws Exception;

    void reportArtist(final String username, final String artistUsername, final String description) throws Exception;

    void followArtist(final String username, final String artistUsername) throws Exception;

    List<Audio> fetchAudioByGenre(final String username) throws Exception;

    Listener validationLoginInfo(final String username, final String password) throws Exception;

    void updateFavouriteGenres(final String username, final List<Genre> genres) throws Exception;

}


