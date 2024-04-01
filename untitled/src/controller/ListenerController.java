package controller;

import model.Database;
import model.audioRelated.AudioModel;
import model.audioRelated.Genre;
import model.audioRelated.PlayListModel;
import model.users.AccountUserModel;
import model.users.artists.ArtistModel;
import model.users.listeners.FreeListenerModel;
import model.users.listeners.ListenerModel;
import model.users.listeners.PremiumListenerModel;

public class ListenerController
{
    private static ListenerController listenerController;
    private ListenerModel listener;
    private ListenerController(){}
    public static ListenerController getListenerController()
    {
        if(listenerController==null)
            listenerController=new ListenerController();
        return listenerController;
    }
    public ListenerModel getListener()
    {
        return this.listener;
    }
    public void setListener(ListenerModel listener)
    {
        this.listener = listener;
    }
    public String makeNewListener(String userName,String password,String fullName,String email,String phoneNumber,String birthDate)
    {
        String answer=Controller.getController().makeNewAccount(userName,email,phoneNumber,birthDate);
        if(answer.compareTo("Signed up successfully")==0)
        {
            ListenerModel tempListener;
            tempListener=new FreeListenerModel(userName,password,fullName,email,phoneNumber,birthDate);
            setListener(tempListener);
            Database.getDatabase().getAllUsers().add(tempListener);
        }
        return answer;
    }
    public String getGenres()
    {
        StringBuilder temp=new StringBuilder();
        for(Genre genre:Genre.values())
            temp.append(genre).append(" ");
        return temp.toString();
    }
    public boolean addFavGenres(String genre)
    {
        if(genre.compareTo("ROCK")==0)
        {
            getListener().getFavGenres().add(Genre.ROCK);
            return true;
        }
        else if(genre.compareTo("POP")==0)
        {
            getListener().getFavGenres().add(Genre.POP);
            return true;
        }
        else if(genre.compareTo("JAZZ")==0)
        {
            getListener().getFavGenres().add(Genre.JAZZ);
            return true;
        }
        else if(genre.compareTo("COUNTRY")==0)
        {
            getListener().getFavGenres().add(Genre.COUNTRY);
            return true;
        }
        else if(genre.compareTo("TRUE_CRIME")==0)
        {
            getListener().getFavGenres().add(Genre.TRUE_CRIME);
            return true;
        }
        else if(genre.compareTo("HIPHOP")==0)
        {
            getListener().getFavGenres().add(Genre.HIPHOP);
            return true;
        }
        else if(genre.compareTo("HISTORY")==0)
        {
            getListener().getFavGenres().add(Genre.HISTORY);
            return true;
        }
        else if(genre.compareTo("INTERVIEW")==0)
        {
            getListener().getFavGenres().add(Genre.INTERVIEW);
            return true;
        }
        else if(genre.compareTo("SOCIETY")==0)
        {
            getListener().getFavGenres().add(Genre.SOCIETY);
            return true;
        }
        else
            return false;
    }
    public String addToPlayList(String playListName,String audioID)
    {
        if((getListener() instanceof PremiumListenerModel) || (getListener() instanceof FreeListenerModel && (((FreeListenerModel) getListener()).getAddedAudios()<FreeListenerModel.getAddAudioLimit())))
        {
            boolean check=false;
            AudioModel chosenAudio =null;
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
                {
                    check=true;
                    chosenAudio=temp;
                    break;
                }
            if(!check)
                return "audio doesn't exist";
            check=false;
            for(PlayListModel temp:listener.getPlayLists())
                if(temp!=null && temp.getPlayListName().compareTo(playListName)==0)
                {
                    temp.getAudios().add(chosenAudio);
                    check=true;
                    break;
                }
            if(!check)
                return "playlist doesn't exist";
            return "audio added to playlist successfully";
        }
        else
            return "you already added 10 audios buy premium for more";
    }
    public String showArtists()
    {
        StringBuilder answer=new StringBuilder();
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel)
                answer.append(temp).append("\n");
        return answer.toString();
    }
    public String showArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
                return temp.toString();
        return "artist doesn't exist";
    }
    public String followArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
            {
                ((ArtistModel) temp).getFollowers().add(getListener());
                getListener().getFollowings().add((ArtistModel)temp);
                return "added to followings";
            }
        return "artist doesn't exist";
    }
    public String search(String audioOrArtistName)
    {
        boolean firstRound=true;
        StringBuilder answer=new StringBuilder("");
        for(AudioModel temp:Database.getDatabase().getAllAudios())
        {
            if(firstRound)
                answer.append("Audios:\n");
            if(temp!=null && temp.getAudioName().compareTo(audioOrArtistName)==0)
                answer.append(temp).append("\n");
            firstRound=false;
        }
        firstRound=true;
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
        {
            if(firstRound)
                answer.append("Artists:\n");
            if(temp instanceof ArtistModel && temp.getFullName().compareTo(audioOrArtistName)==0)
                answer.append(temp).append("\n");
            firstRound=false;
        }
        if(answer.toString().compareTo("")!=0)
            return answer.toString();
        return "no result";
    }
}
