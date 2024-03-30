package model;

import model.Audio.Audio;

import java.util.ArrayList;

public class Playlist {
    private static int tempID = 0;
    private final int id;
    private final String playlistName;
    private final String creatorName;
    private final ArrayList<Audio> audios;

    public Playlist(int id, String playlistName, String creatorName) {
        this.id = ++tempID;
        this.playlistName = playlistName;
        this.creatorName = creatorName;
        this.audios = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getPlaylistName() {
        return playlistName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }
}
