package controller;

import model.Audio.AudioModel;
import model.DataBase.DataBaseModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;

import java.util.Map;

public class ArtistController {
    private ArtistModel artist;
    public ArtistModel getArtist() {
        return artist;
    }
    public void setListener(ArtistModel artist) {
        this.artist = artist;
    }

    private static ArtistController artistController;

    public static ArtistController getArtistController() {
        if (artistController == null)
            artistController = new ArtistController();
        return artistController;
    }
    public StringBuilder showFollowers() {
        StringBuilder str = new StringBuilder();
        for (UserAccountModel listener : artist.getFollowers())
            str.append(listener.toString()).append("\n");
        return str;
    }
    public StringBuilder viewsStatistics() {
        StringBuilder str = new StringBuilder();
        for (Map<AudioModel, Integer> map : artist.get)
    }
}
