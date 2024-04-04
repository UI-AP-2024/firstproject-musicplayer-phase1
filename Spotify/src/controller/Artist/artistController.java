package controller.Artist;

import model.Album.Album;
import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre.Genre;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Artist.Podcaster;
import model.UserAccounts.Artist.Singer;
import model.UserAccounts.Listener.Free;
import model.UserAccounts.Listener.Premium;
import model.UserAccounts.userAccount;

import java.util.Date;
import java.util.regex.Pattern;

public class artistController {
    private static artistController artistC;

    private Artist artistM;

    private artistController() {
    }

    public static artistController getArtistC() {
        if (artistC == null) {
            artistC = new artistController();
        }
        return artistC;
    }

    public Artist getArtistM() {
        return artistM;
    }

    public void setArtistM(Artist artistM) {
        this.artistM = artistM;
    }

    // register artist
    public int registerArtist(String userId, String type, String password, String fullName, String email, String phoneNumber, Date birthday, String bio) {
        for (model.UserAccounts.userAccount userAccount : Database.getDatabase().getAllUsersList()) {
            if (userAccount.getUserId().equals(userId)) {
                return 0; //this user name has already exist!
            }
        }
        Pattern phoneRegex = Pattern.compile("^09[01239][0-9]{8}$");
        if (!phoneRegex.matcher(phoneNumber).matches()) {
            return 1; // phone number is not true!
        }
        Pattern emailRegex = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\\\.)+[a-zA-Z]{2,}$");
        if (!emailRegex.matcher(email).matches()) {
            return 2; //email is not true
        }
        if (type.equals("S")) {
            artistM = new Singer(userId, password, fullName, email, phoneNumber, birthday, bio);
        } else if (type.equals("P")) {
            artistM = new Singer(userId, password, fullName, email, phoneNumber, birthday, bio);
        }
        Database.getDatabase().getAllUsersList().add(artistM);
        return 3; //Registering was successful!!!
    }

    // login artist
    public void loginArtist(userAccount userAccount) {
        if (userAccount instanceof Singer) {
            artistM = (Singer) userAccount;
        } else if (userAccount instanceof Podcaster) {
            artistM = (Podcaster) userAccount;
        }
    }

    ///show followers
    public String showFollowers() {
        StringBuilder context = new StringBuilder();
        for (userAccount user : artistM.getFollowersList()) {
            context.append(user.toString());
            context.append("\n");
        }
        return context.toString();
    }

    /// view plays
    public String ViewsStatistics() {
        StringBuilder context = new StringBuilder();
        for (Audio audio : Database.getDatabase().getAllAudiosList()) {
            if (audio.getArtistName().equals(artistM.getFullName())) {
                context.append(audio.getFileName());
                context.append(" : ");
                context.append(audio.getNumberOfPlays());
            }
            context.append("\n");
        }
        return context.toString();
    }


    /// share podcast
    public void sharePodcast(String podcastName, Genre genre, String caption, String link, String cover) {
        Podcast podcast = new Podcast(podcastName, artistM.getFullName(), new Date(), genre, link, cover, caption);
        Database.getDatabase().getAllAudiosList().add(podcast);
    }

    // Create new Album
    public void createAlbum(String name) {
        Album album = new Album(name, artistM.getFullName());
        ((Singer) artistM).getAlbumsList().add(album);
    }

    /// share music
    public void shareMusic(String musicName, Genre genre, String lyric, String link, String cover, int albumId) {
        Music music = new Music(musicName, artistM.getFullName(), new Date(), genre, link, cover, lyric);
        ((Singer) artistM).getAlbumsList().get(albumId).getMusicList().add(music);
        Database.getDatabase().getAllAudiosList().add(music);
    }

    ///AccountInfo
    public String AccountInfo() {
        return artistM.toString();
    }

}
