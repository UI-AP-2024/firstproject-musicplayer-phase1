package Model;

import java.util.Date;

public class FreeModel extends ListenerModel{
    public static final int maxAudio=10;
    public static final int maxPlaylist=3;
    public FreeModel(String username, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(username, password, fullName, email, phoneNumber, birthDate);
        Database.getDatabase().getUsers().add(this);

    }
}
