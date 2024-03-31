package model.AccountUser.Listener.TypeOfListener;

import model.AccountUser.Listener.Listener;

import java.util.Date;

public class FreeListener extends Listener {
    private static final int MAX_PLAYLIST_SIZE = 10;
    private static final int MAX_PLAYLISTS = 3;

    //*********************************************
    public FreeListener(String userName, String password, String fullName, String email, String phoneNumber, Date birthDate,double accountBalance,Date subEndDate) {
        super( userName,password,  fullName, email, phoneNumber, birthDate, accountBalance, subEndDate);
    }
    //*********************************************
    public int getMaxPlaylistSize() {
        return MAX_PLAYLIST_SIZE;
    }

    public int getMaxPlaylists() {
        return MAX_PLAYLISTS;
    }
    //*********************************************
    @Override
    public String toString() {
        return "NormalListener{" +
                "userName='" + getUserName() + '\'' +
                ", fullName='" + getFullName() + '\'' +
                ", accountBalance=" + getAccountBalance() +
                ", playlists=" + getPlaylists().size() +
                ", favoriteGenres=" + getFavoriteGenres() +
                '}';
    }
}
