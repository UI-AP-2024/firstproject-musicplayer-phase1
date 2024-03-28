package Models.User;
import Models.Genre;

import java.util.*;
public class NormalListener extends Listener {
    private static final int SONGS_PER_PLAYLIST_LIMIT = 10;
    private static final int PLAYLIST_LIMIT = 3;

    public NormalListener(String username, String password, String name, String email, String phoneNumber,
                          LocalDate dateOfBirth, double credit, LocalDate expirementDate, ArrayList<Genre> favoriteGenres) {
        super(username, password, name, email, phoneNumber, dateOfBirth, credit, expirementDate, favoriteGenres);
    }
    public int getSongsPerPlaylistLimit() {
        return SONGS_PER_PLAYLIST_LIMIT;
    }

    public int getPlaylistLimit() {
        return PLAYLIST_LIMIT;
    }
}
