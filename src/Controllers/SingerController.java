package Controllers;

import Models.Album;
import Models.Audio.Audio;
import Models.Audio.Song;
import Models.User.Singer;
import Extra.Extra;

import java.util.ArrayList;

public class SingerController extends ArtistController {
    private static  SingerController singerController;
    private SingerController()
    {
        super();
    }
    public SingerController getSingerController()
    {
        if(singerController == null) singerController = new SingerController();
        return singerController;
    }
    public String addSong(Song song, int albumId)
    {
        Singer singerModel = (Singer)this.getArtistModel();
        ArrayList<Album> singerModelAlbums = singerModel.getAlbums();
        int tmpIndx = 0;
        for(Album tmpAlbum : singerModelAlbums)
        {
            if(tmpAlbum.getId() == albumId) break;
            tmpIndx++;
        }
        if(tmpIndx == singerModelAlbums.size()) return "No such album found";

        Album selectedAlbum = singerModelAlbums.get(tmpIndx);
        ArrayList<Song> selectedAlbumSongs = selectedAlbum.getSongs();
        selectedAlbumSongs.add(song);
        return "song added to the album successfully";
    }

    public double calculateIncome()
    {
        int playedCount = ArtistController.getPlayedCount(this.getArtistModel());
        this.getArtistModel().setIncome(0.4 * playedCount);
        return this.getArtistModel().getIncome();
    }
}
