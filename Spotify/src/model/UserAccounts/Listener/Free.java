package model.UserAccounts.Listener;

import model.Genre.Genre;
import model.Playlist.Playlist;
import model.UserAccounts.Listener.Listener;

import java.util.ArrayList;
import java.util.Date;

public class Free extends Listener {
    private static final int limitCreatePlaylist=3;
    private static final int limitAddMusicToPlaylist=10;

    public Free(String userId, String password, String fullName, String email, String phoneNumber, Date birthday) {
        super(userId, password, fullName, email, phoneNumber, birthday);
    }

    public static int getLimitCreatePlaylist(){
        return limitCreatePlaylist;
    }
    public static int getLimitAddMusicToPlaylist() {
        return limitAddMusicToPlaylist;
    }

    @Override
    public String toString(){
        StringBuilder context = new StringBuilder(super.toString());
        context.append("\n");
        context.append("limit Create Playlist : ");
        context.append(limitCreatePlaylist);
        context.append("\n");
        context.append("limit Create Playlist : ");
        context.append(limitCreatePlaylist);
        context.append("\n");
        return context.toString();
    }
}
