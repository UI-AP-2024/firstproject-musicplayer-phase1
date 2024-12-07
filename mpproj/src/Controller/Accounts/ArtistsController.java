package Controller.Accounts;

import Model.Accounts.AccountsModel;
import Model.Accounts.Artists.ArtistModel;
import Model.Accounts.Artists.PodcasterModel;
import Model.Accounts.Artists.SingerModel;
import Model.Accounts.Listeners.ListenerModel;
import Model.Audios.AlbumModel;
import Model.Audios.AudioModel;
import Model.Audios.MusicModel;
import Model.Audios.PodcastModel;
import Model.Database.Database;

import java.util.ArrayList;
import java.util.Date;

public class ArtistsController {

    private ArtistModel artist;

    private static ArtistsController artistsController;

    private ArtistsController(){}

    public static ArtistsController getArtistsController(){
        if(artistsController == null){
            artistsController = new ArtistsController();
        }
        return artistsController;
    }

    public void loginArtist(AccountsModel artist){
        this.artist = (ArtistModel) artist;
    }

    public StringBuilder showFollower(){
        StringBuilder message = new StringBuilder();
        message.append("\nFollowers :\n");
        for (AccountsModel follower : this.artist.getFollowers()){
            message.append(follower);
            message.append("\n\n");
        }
        return message;
    }

    public StringBuilder showAudiosPlayCount(){
        StringBuilder message = new StringBuilder();
        ArrayList<AudioModel> audios = new ArrayList<AudioModel>();

        if (this.artist instanceof SingerModel singer){
            audios = singer.getMusicList();
        }
        if (this.artist instanceof PodcasterModel podcaster){
            audios = podcaster.getPodcastList();
        }

        message.append("\nArtist's Audios play count :\n");
        for (AudioModel audio : audios) {
            message.append(audio.getAudioName()).append(" : ").append(audio.getPlayCount());
            message.append("\n\n");
        }

        return message;
    }


    public double calculateIncome(){
        if (this.artist instanceof SingerModel singer){
            return singerIncomeCalculate(singer);
        }

        if (this.artist instanceof PodcasterModel podcaster){
            return podcasterIncomeCalculate(podcaster);
        }
        return 0;
    }

    private double singerIncomeCalculate(SingerModel singer){
        ArrayList<AudioModel> songs = singer.getMusicList();
        int songsPlayCount  = 0;
        for (AudioModel song : songs){
            songsPlayCount += song.getPlayCount();
        }
        return songsPlayCount * .4;
    }
    private double podcasterIncomeCalculate(PodcasterModel podcaster){
        ArrayList<AudioModel> podcasts = podcaster.getPodcastList();
        int podcastsPlayCount = 0;
        for (AudioModel podcast : podcasts){
            podcastsPlayCount += podcast.getPlayCount();
        }
        return podcastsPlayCount * .5;
    }

    public String showAccountInfo(){
        calculateIncome();
        return "\n"+ this.artist.toString() +
                "\nEmail : "+ this.artist.getEmail() +
                "\nPhone Number : "+ this.artist.getPhoneNumber() +
                "\nBio : "+ this.artist.getBio() +
                "\nIncome : "+ this.artist.getIncome() +
                "\nBirthday"+ this.artist.getBirthday().toString();
    }

    public String releaseAudio(String title , String genre , String lyricOrCaption , String link , String cover , int ... albumID){
        if (this.artist instanceof SingerModel singer){
            return releaseSong(singer , title , genre , lyricOrCaption , link , cover , albumID[0]);
        }
        if (this.artist instanceof PodcasterModel podcaster){
            return releasePodcaster(podcaster , title , genre , lyricOrCaption , link , cover);
        }
        return "\nsomething went wrong";
    }

    private String releaseSong(SingerModel singer ,String title , String genre , String lyric , String link , String cover , int albumID){
        MusicModel music = new MusicModel(title , this.artist.getUsername() , new Date() , genre , link , cover , lyric);
        AlbumModel album = findAlbumByID(singer , albumID);
        if (album == null){
            return "\nCould not find album";
        }
        album.addSongToAlbum(music);
        Database.getDatabase().addAudio(music);
        singer.addMusic(music);
        return "\nSong released successfully";
    }

    private AlbumModel findAlbumByID(SingerModel singer , int albumID){
        ArrayList<AlbumModel> albums = singer.getAlbumList();
        for (AlbumModel album : albums){
            if (album.getID() == albumID){
                return album;
            }
        }
        return null;
    }

    private String releasePodcaster(PodcasterModel podcaster , String title , String genre , String caption , String link , String cover){
        PodcastModel podcast = new PodcastModel(title , this.artist.getUsername() , new Date() , genre , link , cover , caption);
        Database.getDatabase().addAudio(podcast);
        podcaster.addPodcast(podcast);
        return "\nPodcast released successfully";
    }

    public String createAlbum(String albumName){
        if (this.artist instanceof PodcasterModel){
            return "\nOnly singers can create Album";
        }
        AlbumModel album = new AlbumModel(albumName , this.artist.getFullName());
        SingerModel singer = (SingerModel) this.artist;
        singer.addAlbum(album);
        return "\nAlbum created successfully";
    }
}
