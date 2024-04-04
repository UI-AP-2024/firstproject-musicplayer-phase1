package controller;

import model.enums.SequenceName;
import model.model.audio.Album;
import model.model.audio.Music;
import model.model.user.Singer;

public class SingerController extends Controller {

    public void saveSigner(final Singer singer) throws Exception {
        if (database.isExistsUsername(singer.getUsername())) {
            throw new Exception("Username is exists");
        }
        database.saveSigner(singer);
    }

    public void saveAlbum(final String singerUsername, final String albumName) throws Exception {
        final Singer singer = database.fetchSingerByUsername(singerUsername);
        if (singer == null) {
            throw new Exception("Not found singer by username, Username: " + singerUsername);
        }

        final Album album = new Album();
        album.setId(database.fetchNextValSequence(SequenceName.ALBUM));
        album.setName(albumName);
        album.setSingerName(String.format("%s %s", singer.getFirstname(), singer.getLastname()));

        database.addAlbum(singerUsername, album);
    }

    public void saveMusic(final String singerUsername, final Music music) throws Exception {
        final Singer singer = database.fetchSingerByUsername(singerUsername);
        if (singer == null) {
            throw new Exception("Not found singer by username, Username: " + singerUsername);
        }
        music.setArtist(singer);
        database.saveMusic(music);
    }


}
