package controller.Artist;

import model.UserAccounts.Artist.Artist;

public class artistController  {
    private static artistController artistC;

    private Artist artistM;
    private artistController(){}

    public static artistController getArtistC() {
        if (artistC==null){
            artistC=new artistController();
        }
        return artistC;
    }

    public Artist getArtistM() {
        return artistM;
    }

    public void setArtistM(Artist artistM) {
        this.artistM = artistM;
    }

}
