package controller;

import model.Database;
import model.users.artists.ArtistModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;

public class ArtistController
{
    private static ArtistController artistController;
    private ArtistModel artist;
    private ArtistController(){}
    public static ArtistController getArtistController()
    {
        if(artistController==null)
            artistController=new ArtistController();
        return artistController;
    }
    public void setArtist(ArtistModel artist)
    {
        this.artist = artist;
    }
    public ArtistModel getArtist()
    {
        return this.artist;
    }
    public String makeNewArtist(String userName,String password,String fullName,String email,String phoneNumber,String birthDate,String bio,String accType)
    {
        String answer=Controller.getController().makeNewAccount(userName,email,phoneNumber,birthDate);
        if(answer.compareTo("Signed up successfully")==0)
        {
            if(accType.compareTo("S")==0)
            {
                SingerModel artistTemp=new SingerModel(userName,password,fullName,email,phoneNumber,birthDate,bio);
                Database.getDatabase().getAllUsers().add(artistTemp);
            }
            else if(accType.compareTo("P")==0)
            {
                PodcasterModel artistTemp=new PodcasterModel(userName,password,fullName,email,phoneNumber,birthDate,bio);
                Database.getDatabase().getAllUsers().add(artistTemp);
            }
        }
        return answer;
    }
}
