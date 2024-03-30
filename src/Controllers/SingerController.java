package Controllers;

import Models.Album;
import Models.Audio.Audio;
import Models.Audio.Song;
import Models.User.Singer;
import Extra.Extra;

import java.util.ArrayList;

public class SingerController extends ArtistController {
    public String addAudio(Song song, Album album)
    {
        Singer singerModel = (Singer)this.getArtistModel();
        int indx = Extra.findIndxOf(singerModel.getAlbums(), album);
        if(indx == -1) return "No such album found";
        ArrayList<Album> albums = singerModel.getAlbums();
        ArrayList<Song> songs = album.getSongs();
        songs.add(song);
        albums.set
                // don't forget set  and  add to database
    }
}
