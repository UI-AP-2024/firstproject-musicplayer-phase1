package view;

import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.Album;
import model.Audio.Audio;
import model.Database;

import java.util.List;

public class SingerV {
        public static void printAlbumInfo(Singer singer) {
            System.out.println("Albums created by this singer:");
            for (Album album : singer.getAlbums()) {
                System.out.println("Album ID: " + album.getUniqeId() + ", Album Name: " + album.getName());
            }
        }


        public static void displayAudioIdsAndTitles() {
            List<Audio> audiofiles = Database.getInstance().getAudiofiles();
            if (audiofiles.isEmpty()) {
                System.out.println("No audio files available.");
            } else {
                System.out.println("List of audio files:");
                for (Audio audio : audiofiles) {
                    System.out.println("ID: " + audio.getUniqeId() + ", Title: " + audio.getTitle());
                }
            }
        }
    }

