package controller;

import model.AlbumModel;
import model.Audio.AudioModel;
import model.Audio.MusicModel;
import model.Audio.PodcastModel;
import model.DataBase.DataBaseModel;
import model.GenreModel;
import model.UserAccount.Artist.ArtistModel;
import model.UserAccount.Artist.PodcasterModel;
import model.UserAccount.Artist.SingerModel;
import model.UserAccount.Listener.ListenerModel;
import model.UserAccount.UserAccountModel;

import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;

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
        for (Map.Entry<AudioModel, Integer> map : artist.getAudiosPlayCount().entrySet())
            str.append("Audio name : ").append(map.getKey().getAudioTitle())
                    .append(", number of plays : ").append(map.getValue()).append("\n");
        return str;
    }
    public double calculateEarnings() {
        if (artist instanceof SingerModel) artist.setIncome(0.4 * artist.getPlaysCount());
        if (artist instanceof PodcasterModel) artist.setIncome(0.5 * artist.getPlaysCount());
        return artist.getIncome();
    }
    public String publishMusic(String title, String genre, String lyric, String link, String cover, int albumID) {
        MusicModel music = new MusicModel(title,artist.getName(), LocalDate.now(), GenreModel.valueOf(genre), link, cover, lyric);
        if (artist instanceof SingerModel)
            for (AlbumModel album : ((SingerModel) artist).getAlbums())
                if (album.getId() == albumID) {
                    DataBaseModel.getDataBase().getAudios().add(music);
                    album.getMusics().add(music);
                    return "Music added to the album successfully";
                }
        return "Album not found";
    }
    public String publishPodcast(String title, String genre, String caption, String link, String cover) {
        PodcastModel podcast = new PodcastModel(title, artist.getName(), LocalDate.now(), GenreModel.valueOf(genre), link, cover, caption);
        if (artist instanceof PodcasterModel)
            ((PodcasterModel) artist).getPodcasts().add(podcast);
        DataBaseModel.getDataBase().getAudios().add(podcast);
        return "Podcast added successfully";
    }
    public String newAlbum(String name) {
        AlbumModel album = new AlbumModel(name, artist.getName());
        if (artist instanceof SingerModel)
            for (AlbumModel albumModel : ((  SingerModel) artist).getAlbums())
                if (Objects.equals(albumModel.getAlbumName(), name))
                    return "This name has been used before";
        if (artist instanceof SingerModel)
            ((SingerModel) artist).getAlbums().add(album);
        return "Album created successfully";
    }
    public String showAccountInfo() {
        return artist.toString();
    }
}
