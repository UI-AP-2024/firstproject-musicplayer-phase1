package controller;

import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.Audio.Audio;
import model.Genre;

import java.util.Date;
import java.util.List;

public class PodcasterC {


    public void publishPodcast(Podcaster podcaster, int uniqueId, String title, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover) {
        Audio podcast = new Audio(uniqueId, title, podcaster.getFullName(), playCount, likes, releaseDate, genre, audioLink, cover);

        podcaster.getPodcasts().add(title);

        System.out.println("Podcast '" + title + "' published successfully by " + podcaster.getFullName() + ".");
    }
}
