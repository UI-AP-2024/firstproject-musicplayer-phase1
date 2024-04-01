package controller;

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
        getArtist().calculateIncome();
        String txt="Account info:"+
        "\nuser name : "+getArtist().getUsername()+
        "\nFirst name : "+getArtist().getFirstName()+
        "\nLast name : "+getArtist().getLastName()+
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
    
}
