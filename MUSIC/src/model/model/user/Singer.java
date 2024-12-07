package model.model.user;


import model.model.audio.Album;

import java.util.List;

// خواننده
public class Singer extends Artist {
    private List<Album> albums;

    public List<Album> getAlbums() {
        return albums;
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
    }

    @Override
    public String toString() {
        return "Singer{" +
                "albums=" + albums +
                "} " + super.toString();
    }
}
