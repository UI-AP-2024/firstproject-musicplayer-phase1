package Controller;

import Model.*;

import java.time.LocalDate;
import java.util.ArrayList;


public class ArtistController {
    private static ArtistController artistController;
    private Podcaster podcaster;

    private ArtistController() {
    }

    private Artist artist;

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }


    public Podcaster getPodcaster() {
        return podcaster;
    }

    public void setPodcaster(Podcaster podcaster) {
        this.podcaster = podcaster;
    }

    public static ArtistController getArtistController() {
        if (artistController == null)
            artistController = new ArtistController();
        return artistController;
    }

    public StringBuilder showFollowers() {
        int i = 1;
        StringBuilder followers = new StringBuilder();
        for (UserAccount follower : artist.getFollowersList()) {
            followers.append(i).append(".\n").append(follower).append("\n");
            i++;
        }
        return followers;
    }

    public StringBuilder showPlayCount() {
        StringBuilder playCount = new StringBuilder();
        for (Audio audio : DataBase.getDataBase().getAudioList()) {
            if (audio.getArtistName().equals(artist.getUserName())) {
                playCount.append(audio.getId()).append(" plays :");
                playCount.append(audio.getPlaycounter()).append("\n");
            }
        }
        return playCount;
    }

    public String newAlbum(String albumName) {
        Album album = new Album(albumName, artist.getUserName());
        ((Singer) artist).getAlbumsList().add(album);
        return "Album made Succesfully!";
    }

    public String publishMusic(String input, String audioName, Genre genre, String text, String link,
                               String cover, String albumID) {

        if (input.equals("M")) {
            Music music = new Music(audioName, artist.getUserName(), genre, link, cover, LocalDate.now().getYear(),
                    LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), text);
            findAlbum(albumID).getMusicsList().add(music);
        } else {
            return "You can't publish Podcast!";

        }
        return "published sucssefully";
    }

    public String publishPodcast(String input, String audioName, Genre genre, String text, String link,
                                 String cover) {
        if (input.equals("P")) {
            Podcast podcast = new Podcast(audioName, artist.getUserName(), genre, link, cover,
                    LocalDate.now().getYear(), LocalDate.now().getMonthValue(), LocalDate.now().getDayOfMonth(), text);
            ((Podcaster)artist).getPodcastsList().add(podcast);
        } else {
            return "You can't publish Music!";
        }
        return "published sucssefully";
    }



    public Album findAlbum(String albumID) {
        for (Album album : ((Singer) artist).getAlbumsList()) {
            if (album.getID().equals(albumID)) {
                return album;
            }
        }
        return null;
    }


    public void calculateArtistSalary(Audio audio, String artistname) {
        for (UserAccount user : DataBase.getDataBase().getUsersList()) {
            if (user.getUserName().equals(artistname)) {
                if (audio instanceof Music) {
                    double salary = ((Artist)user).getSalary() + 0.4;
                    ((Artist)user).setSalary(salary);
                } else {
                    double salary = ((Artist)user).getSalary() + 0.5;
                    ((Artist)user).setSalary(salary);
                }
            }
        }
    }
    public String showSalary(){
        return "Your Salary is "+artist.getSalary();
    }
}

