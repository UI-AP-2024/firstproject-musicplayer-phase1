package controller;

import model.Database;
import model.audioRelated.*;
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
    public String makeAlbum(String albumName)
    {
        if(getArtist() instanceof SingerModel)
        {
            AlbumModel.setAlbumAmount(AlbumModel.getAlbumAmount()+1);
            AlbumModel album=new AlbumModel(albumName,getArtist().getUserName());
            char[] artistUserName=getArtist().getUserName().toCharArray();
            long albumID=0;
            for(int i=0;i<artistUserName.length;++i)
                albumID+=artistUserName[i];
            String fullID=Long.toString(albumID)+Long.toString(AlbumModel.getAlbumAmount());
            album.setAlbumID(Long.parseLong(fullID));
            ((SingerModel)getArtist()).getAlbums().add(album);
            return "Album made";
        }
        else
            return "You are not a singer";
    }
    public String publish(String title,String genre,String lyric,String link,String cover,String albumID)
    {
        String musicID=new String();
        Genre tempGenre =null;
        if(genre.compareTo("ROCK")==0)
        {
            tempGenre=Genre.ROCK;
            musicID="1";
        }
        else if(genre.compareTo("POP")==0)
        {
            tempGenre=Genre.POP;
            musicID="2";
        }
        else if(genre.compareTo("JAZZ")==0)
        {
            tempGenre=Genre.JAZZ;
            musicID="3";
        }
        else if(genre.compareTo("COUNTRY")==0)
        {
            tempGenre=Genre.COUNTRY;
            musicID="4";
        }
        else if(genre.compareTo("TRUE_CRIME")==0)
        {
            tempGenre=Genre.TRUE_CRIME;
            musicID="5";
        }
        else if(genre.compareTo("HIPHOP")==0)
        {
            tempGenre=Genre.HIPHOP;
            musicID="6";
        }
        else if(genre.compareTo("HISTORY")==0)
        {
            tempGenre=Genre.HISTORY;
            musicID="7";
        }
        else if(genre.compareTo("INTERVIEW")==0)
        {
            tempGenre=Genre.INTERVIEW;
            musicID="8";
        }
        else if(genre.compareTo("SOCIETY")==0)
        {
            tempGenre=Genre.SOCIETY;
            musicID="9";
        }
        AudioModel.setAudioAmount(AudioModel.getAudioAmount()+1);
        MusicModel music=new MusicModel(title,getArtist().getUserName(),tempGenre,link,cover,lyric);
        char[] artistUserName=getArtist().getUserName().toCharArray();
        long ID=0;
        for(int i=0;i<artistUserName.length;++i)
            ID+=artistUserName[i];
        musicID=musicID+Long.toString(ID)+Long.toString(AudioModel.getAudioAmount());
        music.setAudioID(Long.parseLong(musicID));
        Database.getDatabase().getAllAudios().add(music);
        for(AlbumModel temp:((SingerModel)getArtist()).getAlbums())
            if(temp!=null && temp.getAlbumID()==Long.parseLong(albumID))
            {
                temp.getMusics().add(music);
                return "music published";
            }
        return "Album not found";
    }
    public void publish(String title,String genre,String caption,String link,String cover)
    {
        String podcastID=new String();
        Genre tempGenre =null;
        if(genre.compareTo("ROCK")==0)
        {
            tempGenre=Genre.ROCK;
            podcastID="1";
        }
        else if(genre.compareTo("POP")==0)
        {
            tempGenre=Genre.POP;
            podcastID="2";
        }
        else if(genre.compareTo("JAZZ")==0)
        {
            tempGenre=Genre.JAZZ;
            podcastID="3";
        }
        else if(genre.compareTo("COUNTRY")==0)
        {
            tempGenre=Genre.COUNTRY;
            podcastID="4";
        }
        else if(genre.compareTo("TRUE_CRIME")==0)
        {
            tempGenre=Genre.TRUE_CRIME;
            podcastID="5";
        }
        else if(genre.compareTo("HIPHOP")==0)
        {
            tempGenre=Genre.HIPHOP;
            podcastID="6";
        }
        else if(genre.compareTo("HISTORY")==0)
        {
            tempGenre=Genre.HISTORY;
            podcastID="7";
        }
        else if(genre.compareTo("INTERVIEW")==0)
        {
            tempGenre=Genre.INTERVIEW;
            podcastID="8";
        }
        else if(genre.compareTo("SOCIETY")==0)
        {
            tempGenre=Genre.SOCIETY;
            podcastID="9";
        }
        AudioModel.setAudioAmount(AudioModel.getAudioAmount()+1);
        PodcastModel podcast=new PodcastModel(title,getArtist().getUserName(),tempGenre,link,cover,caption);
        char[] artistUserName=getArtist().getUserName().toCharArray();
        long ID=0;
        for(int i=0;i<artistUserName.length;++i)
            ID+=artistUserName[i];
        podcastID=podcastID+Long.toString(ID)+Long.toString(AudioModel.getAudioAmount());
        podcast.setAudioID(Long.parseLong(podcastID));
        Database.getDatabase().getAllAudios().add(podcast);
        ((PodcasterModel)getArtist()).getPodcasts().add(podcast);
    }
    public double CalculateEarnings()
    {
        if(getArtist() instanceof PodcasterModel)
        {
            for(PodcastModel temp:((PodcasterModel)getArtist()).getPodcasts())
                if(temp!=null)
                    getArtist().setIncome(getArtist().getIncome()+0.5*temp.getPlayAmount());
            return getArtist().getIncome();
        }
        else
        {
            for(AlbumModel albumTemp:((SingerModel)getArtist()).getAlbums())
                if(albumTemp!=null)
                {
                    for(MusicModel temp:albumTemp.getMusics())
                        if(temp!=null)
                            getArtist().setIncome(getArtist().getIncome()+0.4*temp.getPlayAmount());
                }
            return getArtist().getIncome();
        }
    }
}
