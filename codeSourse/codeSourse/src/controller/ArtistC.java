package controller;

import model.AccountUser.Artist.Artist;
import model.Audio.Audio;
import model.Audio.Podcast;
import model.Database;

import java.util.List;

public class ArtistC {

        public static void updateArtistIncome(Artist artist) {
            double totalIncome = 0;

            for (Audio audio : Database.getInstance().getAudiofiles()) {
                if (audio.getArtist().equals(artist.getFullName())) {
                    int playCount = audio.getPlayCount();
                    if (audio instanceof Podcast) {
                        totalIncome += playCount * 0.5;
                    } else {
                        totalIncome += playCount * 0.4;
                    }
                }
            }
            artist.setIncome(totalIncome);
        }
    }



