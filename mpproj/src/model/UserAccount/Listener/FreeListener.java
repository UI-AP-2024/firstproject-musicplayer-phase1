package model.UserAccount.Listener;

import java.util.Date;

public class FreeListener extends Listener{
    private static int songsInPlaylistLimit;
    private static int playlistsLimit;
    public FreeListener(String username, String password, StringBuilder name, String email, String phoneNumber, Date birthday, int accountCredit) {
        super(username, password, name, email, phoneNumber, birthday, accountCredit);
    }
}
