package model.UserAccount.Listener;

import model.Audio.Audio;
import model.Genre;
import model.Playlist;
import model.UserAccount.Listener.Listener;

import java.time.LocalDate;
import java.util.ArrayList;

public class Free extends Listener
{
    private final ArrayList<Playlist> playlists = new ArrayList<Playlist>(3);
    private final ArrayList<Audio> audio = new ArrayList<Audio>(10);
    public Free(String email, String name, String phoneNumber, LocalDate dateOfBirth, String userName, String passWord, Genre[] genres) {
        super(email, name, phoneNumber, dateOfBirth, userName, passWord, genres);
    }
    @Override
    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }
    public ArrayList<Audio> getAudio() {
        return audio;
    }
}
