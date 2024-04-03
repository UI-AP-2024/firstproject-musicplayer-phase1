package controller;

import model.*;

public class ArtistController {
    private static ArtistController artistController;

    public ArtistController() {
    }

    public static ArtistController getArtistController(){
        if ( artistController == null)
            artistController = new ArtistController();
        return artistController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }

    public String viewFollowers(){
        String result = "";
        if ( getUserAccount() instanceof Artist){
            for ( UserAccount userAccount1 : ((Artist) getUserAccount()).getListFollowers()){
                result += userAccount1+"\n";
            }
        }
        if ( result.equals(""))
            result += "empty";
        return result;
    }

    public String viewNumberPlay(){
        String result = "";
        if ( getUserAccount() instanceof Podcaster){
            int count=0;
            for ( Podcast podcast : ((Podcaster) getUserAccount()).getPodcastList()){
                result += "Audio Id: "+podcast.getId()+"\tAudio Name: "+podcast.getName()+"\tplay: "+podcast.getNumberOfPlays()+"\n";
                count += podcast.getNumberOfPlays();
            }
            if ( result.equals(""))
                result += "empty";
            else
                result += "Total number of plays: "+String.valueOf(count);
            return result;
        }
        else if ( getUserAccount() instanceof Singer){
            int count=0;
            for ( Album album : ((Singer) getUserAccount()).getAlbumList() ){
                for ( Music music : album.getMusicList()) {
                    result += "Audio Id: " + music.getId() + "\tAudio Name: " + music.getName() + "\tplay: " + music.getNumberOfPlays() + "\n";
                    count += music.getNumberOfPlays();
                }
            }
            if ( result.equals(""))
                result += "empty";
            else
                result += "Total number of plays: "+String.valueOf(count);
            return result;
        }
        return "ERORR viewNumberPlay";
    }
}
