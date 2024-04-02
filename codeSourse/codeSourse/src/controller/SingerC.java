package controller;

import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.Album;
import model.Audio.Audio;
import model.Database;
import model.Genre;

import java.util.Date;
import java.util.List;

public class SingerC {

        public static int createAlbum(Singer singer, String albumName) {
            int uniqueId = generateUniqueId();
            Album album = new Album(uniqueId, albumName, singer.getFullName());
            singer.getAlbums().add(albumName);
            return uniqueId;
        }
        private static int generateUniqueId() {

            return (int) (Math.random() * 1000) + 1;
        }

    public class AudioPublisher {
        public static void publishAudio(Singer singer, String audioFileName, String albumName, String description) {
            // Create the audio object
            Audio audio = new Audio(audioFileName, description, singer.getFullName(), 0, 0, new Date(), null);

            //
            Database.getInstance().addrAudio(audio);

            // Add the audio file to the album
            Album album = findAlbumByName(singer, albumName);
            if (album != null) {
                album.addSong(audioFileName);
            } else {
                System.out.println("Album not found!");
            }
        }

        private static Album findAlbumByName(Singer singer, String albumName) {
            List<Album> albums = singer.getAlbums();
            for (Album album : albums) {
                if (album.getName().equals(albumName)) {
                    return album;
                }
            }
            return null;
        }


    }
