package controller;

import model.Audio.AudioModel;
import model.Audio.MusicModel;
import model.Audio.PodcastModel;
import model.DataBase.DataBaseModel;
import model.GenreModel;
import model.PlaylistModel;
import model.ReportModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;


import java.time.LocalDate;
import java.util.*;


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
    public String favoriteGenres(String g1, String g2, String g3, String g4) {
        listener.getFavoriteGenres().add(GenreModel.valueOf(g1));
        listener.getFavoriteGenres().add(GenreModel.valueOf(g2));
        listener.getFavoriteGenres().add(GenreModel.valueOf(g3));
        listener.getFavoriteGenres().add(GenreModel.valueOf(g4));
        return "Favorite genres added";
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
    public StringBuilder showPlaylist(String name) {
        StringBuilder str = new StringBuilder();
        for (PlaylistModel playlist : listener.getPlaylists())
            if (Objects.equals(playlist.getPlaylistName(), name)) {
                for (AudioModel audio : playlist.getAudios())
                    str.append(audio.toString());
                return str;
            }
        str.append("Playlist not found");
        return str;
    }
    public String playAudio(int id) {
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (audio.getId() == id) {
                for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
                    if (userAccount instanceof ArtistModel)
                        if (Objects.equals(userAccount.getUsername(), audio.getArtistName())) {
                            int p = ((ArtistModel) userAccount).getPlaysCount() + 1;
                            ((ArtistModel) userAccount).setPlaysCount(p);
                        }
                    return audio.toString();
            }
        return "Audio not found";
    }
    public String newPlaylist(String name) {
        for (PlaylistModel playlist : listener.getPlaylists())
            if (Objects.equals(playlist.getPlaylistName(), name))
                return "This name already exists";
        PlaylistModel playlist = new PlaylistModel(name, listener.getName());
        listener.getPlaylists().add(playlist);
        return "Playlist added successfully";
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
                        playlist.getAudios().add(audioModel);
                        return "The audio was added to the playlist";
                    }
                return "Playlist not found";
            }
        return "Audio not found";
    }
    public String likeAudio(int audioID) {
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (audio.getId() == audioID) {
                audio.setLikesCount(audio.getLikesCount() + 1);
                return "The audio was liked";
            }
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
    public StringBuilder showArtists() {
        StringBuilder str = new StringBuilder();
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                str.append("\nName : ").append(userAccount.getName())
                        .append(", username : ").append(((ArtistModel) userAccount).getUsername());
        return str;
    }
    public String showArtist(String username) {
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                if (Objects.equals(userAccount.getUsername(), username))
                    return userAccount.toString();
        return "Artist not found";
    }
    public String showAccountInfo() {
        return listener.toString();
    }
    public StringBuilder searchAudioOrArtist(String name) {
        StringBuilder str = new StringBuilder();
        for (AudioModel audio : DataBaseModel.getDataBase().getAudios())
            if (Objects.equals(audio.getAudioTitle(), name))
                str.append(audio.toString()).append("\n");
        for (UserAccountModel userAccount : DataBaseModel.getDataBase().getUsers())
            if (userAccount instanceof ArtistModel)
                if (Objects.equals(userAccount.getName(), name))
                    str.append(userAccount.toString()).append("\n");
        return str;
    }
    public StringBuilder sortAudios(String sortBy) {
        StringBuilder str = new StringBuilder();
        ArrayList<AudioModel> audios = DataBaseModel.getDataBase().getAudios();
        if (Objects.equals(sortBy, "L")) {
            for (int i = 0; i < audios.size(); i++) {
                for (int j = i + 1; j < audios.size(); j++) {
                    if (audios.get(i).getLikesCount() > audios.get(j).getLikesCount()) {
                        AudioModel tmp = audios.get(i);
                        audios.set(i, audios.get(j));
                        audios.set(j, tmp);
                    }
                }
            }
        }
        else {
            for (int i = 0; i < audios.size(); i++) {
                for (int j = i + 1; j < audios.size(); j++) {
                    if (audios.get(i).getPlaysCount() > audios.get(j).getPlaysCount()) {
                        AudioModel tmp = audios.get(i);
                        audios.set(i, audios.get(j));
                        audios.set(j, tmp);
                    }
                }
            }
        }
        for (AudioModel audio : audios)
            str.append(audio.toString());
        return str;
    }
    public StringBuilder filterAudios(String filter, String filterBy) {
        StringBuilder str = new StringBuilder();
        ArrayList<AudioModel> audios = DataBaseModel.getDataBase().getAudios();
        if (Objects.equals(filter, "A")) {
            for (AudioModel audio : audios)
                if (Objects.equals(audio.getArtistName(), filterBy))
                    str.append("\nName : ").append(audio.getAudioTitle())
                            .append(", id : ").append(audio.getId());
        }
        else if (Objects.equals(filter, "G")) {
            for (AudioModel audio : audios)
                if (audio.getGenre() == GenreModel.valueOf(filterBy))
                    str.append("\nName : ").append(audio.getAudioTitle())
                            .append(", id : ").append(audio.getId());
        }
        else {
            LocalDate date = LocalDate.parse(filterBy);
            for (AudioModel audio : audios)
                if (audio.getReleaseDate().getDayOfMonth() == date.getDayOfMonth() && audio.getReleaseDate().getMonth() == date.getMonth() && audio.getReleaseDate().getYear() == date.getYear())
                    str.append("\nName : ").append(audio.getAudioTitle())
                            .append(", id : ").append(audio.getId());
        }
        return str;
    }

}
