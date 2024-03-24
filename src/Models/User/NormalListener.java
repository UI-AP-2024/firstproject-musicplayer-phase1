package Models.User;
import java.util.*;
public class NormalListener extends Listener {
    private static final int SONGS_PER_PLAYLIST_LIMIT = 50;
    private static final int PLAYLIST_LIMIT = 10;

    public NormalListener(String username, String password, String name, String email, String phoneNumber,
                          Date dateOfBirth, double credit, Date expirementDate) {
        super(username, password, name, email, phoneNumber, dateOfBirth, credit, expirementDate);
    }
    public int getSongsPerPlaylistLimit() {
        return SONGS_PER_PLAYLIST_LIMIT;
    }

    public int getPlaylistLimit() {
        return PLAYLIST_LIMIT;
    }
}
