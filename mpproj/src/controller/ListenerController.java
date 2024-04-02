package controller;

import model.Audio.AudioModel;
import model.Audio.MusicModel;
import model.Audio.PodcastModel;
import model.DataBase.DataBaseModel;
import model.PlaylistModel;
import model.ReportModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;


import java.util.Objects;



public class ListenerController {
    private ListenerModel listener;
    public ListenerModel getListener() {
        return listener;
    }
    public void setListener(ListenerModel listener) {
        this.listener = listener;
    }

    private static ListenerController listenerController;

    public static ListenerController getListenerController() {
        if (listenerController == null)
                listenerController = new ListenerController();
        return listenerController;
    }
    public String sinUp(String accountType,String username, String password, String name, String email, String phoneNumber, String birthDate) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), username)) return "This username is already in use. Try another one!";
        return "";
    }
    public String report(String artistUsername, String description) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (Objects.equals(userAccount.getUsername(), artistUsername)) {
                ReportModel report = new ReportModel(listener,(ArtistModel)userAccount, description);
                DataBaseModel.getDataBase().getReports().add(report);
                return "The report was successfully registered";
            }
        return "Artist not found";
    }
    public StringBuilder showPlaylists() {
        StringBuilder str = new StringBuilder();
        for (PlaylistModel playlist : listener.getPlaylists()) {
            str.append(playlist.toString()).append("\n");
        }
        return str;
    }
    public StringBuilder showFollowings() {
        StringBuilder str = new StringBuilder();
        for (ArtistModel artist : listener.getFollowings())
            str.append(artist.toString());
        return str;
    }
    public String followArtist(String username) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                if (Objects.equals(userAccount.getUsername(), username)) {
                    if (((ArtistModel) userAccount).getFollowers().contains(listener))
                        return "You are currently following this artist";
                    listener.getFollowings().add((ArtistModel)userAccount);
                    ((ArtistModel) userAccount).getFollowers().add(listener);
                    return "You are now following this artist";
                }
        return "Username not found";
    }
    public String addAudioToPlaylist(String playlistName, int audioID) {
        for (AudioModel audioModel : DataBaseModel.getDataBase().getAudios())
            if (audioModel.getId() == audioID) {
                for (PlaylistModel playlist : listener.getPlaylists())
                    if (Objects.equals(playlist.getPlaylistName(), playlistName)) {
                        for (AudioModel audio : playlist.getAudios())
                            if (audio.getId() == audioID) return "This audio is currently in this playlist";
                        return "The audio was added to the playlist";
                    }
                return "Playlist not found";
            }
        return "Audio not found";
    }
    public String likeAudio(int audioID) {
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (audio.getId() == audioID)
                return "The audio was liked";
        return "Audio not found";
    }
    public String showLyrics(int audioID) {
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (audio.getId() == audioID) {
                if (audio instanceof PodcastModel)
                    return ((PodcastModel) audio).getCaption();
                else if (audio instanceof MusicModel)
                    return ((MusicModel) audio).getLyrics();
            }
        return "Audio not found";
    }
    public String increaseCredit(double value) {
        listener.setAccountCredit(listener.getAccountCredit() + value);
        return "Value added to the accountCredit";
    }

}
