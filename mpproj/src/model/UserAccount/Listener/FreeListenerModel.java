package model.UserAccount.Listener;

import java.util.Date;

public class FreeListenerModel extends ListenerModel{
    private final static int musicsInPlaylistLimit = 0;
    private final static int playlistsLimit = 0;
    public FreeListenerModel(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, double accountCredit) {
        super(username, password, name, email, phoneNumber, birthday, accountCredit);
    }
}
