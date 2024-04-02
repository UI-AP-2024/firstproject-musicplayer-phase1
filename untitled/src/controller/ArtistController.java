package controller;

import model.Database;
import model.audioRelated.AlbumModel;
import model.audioRelated.MusicModel;
import model.audioRelated.PodcastModel;
import model.users.AccountUserModel;
import model.users.artists.ArtistModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;

import java.util.ArrayList;

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
    public String showFollowers()
    {
        StringBuilder answer=new StringBuilder();
        for(AccountUserModel temp :getArtist().getFollowers())
            if(temp!=null)
                answer.append(temp).append("\n");
        return answer.toString();
    }
    public String showViewsStatistics()
    {
        StringBuilder answer=new StringBuilder();
        if(getArtist() instanceof PodcasterModel)
        {
            PodcastModel[] podcasts=((PodcasterModel) getArtist()).getPodcasts().toArray(new PodcastModel[((PodcasterModel) getArtist()).getPodcasts().size()]);
            for(int i=0;i<podcasts.length-1;++i)
                for(int j=i+1;j<podcasts.length;++j)
                    if(podcasts[i].getPlayAmount()<podcasts[j].getPlayAmount())
                    {
                        PodcastModel temp=podcasts[i];
                        podcasts[i]=podcasts[j];
                        podcasts[j]=temp;
                    }
            for(PodcastModel temp:podcasts)
                if(temp!=null)
                    answer.append(temp).append("\n");
            return answer.toString();
        }
        else
        {
            ArrayList <MusicModel>musicsTemp=new ArrayList<>();
            for(AlbumModel temp:((SingerModel)getArtist()).getAlbums())
                if(temp!=null)
                {
                    for(MusicModel music:temp.getMusics())
                        if(music!=null)
                            musicsTemp.add(music);
                }
            MusicModel[] musics=musicsTemp.toArray(new MusicModel[musicsTemp.size()]);
            for(int i=0;i<musics.length-1;++i)
                for(int j=i+1;j<musics.length;++j)
                    if(musics[i].getPlayAmount()<musics[j].getPlayAmount())
                    {
                        MusicModel temp=musics[i];
                        musics[i]=musics[j];
                        musics[j]=temp;
                    }
            for(MusicModel temp:musics)
                if(temp!=null)
                    answer.append(temp).append("\n");
            return answer.toString();
        }
    }
}
