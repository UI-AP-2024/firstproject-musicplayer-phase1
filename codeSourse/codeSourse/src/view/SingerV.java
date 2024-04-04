package view;

import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.Album;

public class SingerV {

        public static void printAlbumIds(Singer singer) {
            System.out.println("Album IDs created by this singer:");
            for (Album album : singer.getAlbums()) {
                System.out.println(album.getUniqeId());
            }
        }
    }
