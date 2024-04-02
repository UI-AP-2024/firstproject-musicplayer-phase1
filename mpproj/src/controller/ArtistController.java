package controller;

import java.util.Date;

import model.audio.Album;
import model.audio.Genre;
import model.audio.Music;
import model.audio.Podcast;
import model.database.Database;
import model.user.Artist;
import model.user.Podcaster;
import model.user.Singer;
import model.user.User;

public class ArtistController {
    private static ArtistController artistController;
    private ArtistController(){

    }
    public static ArtistController getArtistController(){
        if(artistController==null){
            artistController = new ArtistController();
        }
        return artistController;
    }

    private static Artist artist;
    public static Artist getArtist() {
        return artist;
    }
    public static void setArtist(Artist artist) {
        ArtistController.artist = artist;
    }

    public void loginArtist(Artist artist){
        setArtist(artist);
        if(artist instanceof Singer){
            SingerController.getSingerController().setSinger((Singer)artist);
        }
        if(artist instanceof Podcaster){
            PodcasterController.getPodcasterController().setPodcaster((Podcaster)artist);
        }
    }

    public String ShowAccountInfo(){
        calculateIncome();
        String txt="Account info:"+
        "\nuser name : "+getArtist().getUsername()+
        "\nFirst name : "+getArtist().getName()+
        "\nemail address: "+getArtist().getEmailAddress()+
        "\npassword : "+getArtist().getPassword()+
        "\nbirth date : "+String.valueOf(getArtist().getBirthDate())+
        "\nIncome : "+String.valueOf(getArtist().getIncome());
        return txt;
    }

    public String showFollowers(){
        String txt ="All Followers\n";
        for(User user : getArtist().getFollowers()){
            txt+="-"+user.getUsername()+"\n";
        }
        return txt;
    }

    public String showArtistInfo(){
        if(getArtist() instanceof Singer){
            String txt = SingerController.getSingerController().ShowSingerInfo();
            return txt;
        }
        if(getArtist() instanceof Podcaster){
            String txt = PodcasterController.getPodcasterController().ShowPodcasterInfo();
            return txt;
        }
        else return null;

    }

    public String showViewsStatics(){
        if(getArtist() instanceof Singer){
            String txt = SingerController.getSingerController().showViewsStatics();
            return txt;
        }
        if(getArtist() instanceof Podcaster){
            String txt = PodcasterController.getPodcasterController().showViewsStatics();
            return txt;
        }
        else return null;
    }

    public String createNewAlbum(String albumName){
        return SingerController.getSingerController().createNewAlbum(albumName);
    }
    public String publishAudio(String type, String title, String genre,String lyricsCaption,String link,String cover, long albumId){

        Date currentDate = new Date();
        if(type.equals("P")){
            if(getArtist() instanceof Podcaster){
                Podcast tmp = new Podcast(title,getArtist().getName(),currentDate,Genre.valueOf(genre),cover,lyricsCaption);
                Database.getDatabase().addToAllAudio(tmp);
                ((Podcaster)getArtist()).addToPodcastList(tmp);
                return "your podcast published succesfully";
            }
            else return "you are not a Podcaster You cant publish a podcast";
        }
        if(type.equals("M")){
            if(getArtist() instanceof Singer){
                Music tmp = new Music(title,getArtist().getName(),currentDate,Genre.valueOf(genre),cover,lyricsCaption);
                Database.getDatabase().addToAllAudio(tmp);
                for(Album album : ((Singer)getArtist()).getAlbumList())
                {
                    if(album.getId()==albumId){
                        album.addToMusicList(tmp);
                    }
                }

                return "your Music published succesfully";
            }
            else return "you are not a Singer You Cant publish a music ";

        }
        else return "this type of audio is not valid enter a valid type\n 'M' for Music & 'P' for podcast";
    }

    public void calculateIncome(){
        if(getArtist() instanceof Singer){
            calculateIncome((Singer)getArtist());
        }
        if(getArtist() instanceof Podcaster){
            calculateIncome((Podcaster)getArtist());
        }

    }
    public void calculateIncome(Singer singer){
        long view =0;
        for(Album album :singer.getAlbumList()){
            for(Music music : album.getMusicList()){
                view+=music.getNumberOfPlays();
            }
        }
        getArtist().setIncome(view*0.4);
    }
    public void calculateIncome(Podcaster podcaster){
        long view =0;
        for(Podcast podcast :podcaster.getPodcastList()){
            view+=podcast.getNumberOfPlays();
        }
        getArtist().setIncome(view*0.5);
    }


    
}
