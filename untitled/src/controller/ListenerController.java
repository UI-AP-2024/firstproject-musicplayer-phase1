package controller;

import model.Database;
import model.audioRelated.*;
import model.users.AccountUserModel;
import model.users.artists.ArtistModel;
import model.users.artists.PodcasterModel;
import model.users.artists.SingerModel;
import model.users.listeners.FreeListenerModel;
import model.users.listeners.ListenerModel;
import model.users.listeners.PremiumListenerModel;

import java.util.*;
import java.util.regex.Pattern;

public class ListenerController
{
    private static ListenerController listenerController;
    private ListenerModel listener;
    private static long amountOfPlayLists;
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
    public static long getAmountOfPlayLists()
    {
        return amountOfPlayLists;
    }
    public static void setAmountOfPlayLists(long amountOfPlayLists)
    {
        ListenerController.amountOfPlayLists = amountOfPlayLists;
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
            ((FreeListenerModel)getListener()).setAddedAudios(((FreeListenerModel) getListener()).getAddedAudios()+1);
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
    public Object showArtist(String artistUserName)
    {
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
                return temp;
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
    public String sort(String sortType)
    {
        StringBuilder answer=new StringBuilder();
        if(sortType.compareTo("L")==0)
        {
            for(int i=0;i<Database.getDatabase().getAllAudios().size()-1;++i)
            {
                AudioModel outPut=Database.getDatabase().getAllAudios().get(i);
                for(int j=i+1;j<Database.getDatabase().getAllAudios().size();++j)
                    if(outPut.getLikeAmount()<Database.getDatabase().getAllAudios().get(j).getLikeAmount())
                    {
                        outPut=Database.getDatabase().getAllAudios().get(j);
                    }
                answer.append(outPut).append("\n");
            }
            return answer.toString();
        }
        else if(sortType.compareTo("P")==0)
        {
            for(int i=0;i<Database.getDatabase().getAllAudios().size()-1;++i)
            {
                AudioModel outPut=Database.getDatabase().getAllAudios().get(i);
                for(int j=i+1;j<Database.getDatabase().getAllAudios().size();++j)
                    if(outPut.getPlayAmount()<Database.getDatabase().getAllAudios().get(j).getPlayAmount())
                    {
                        outPut=Database.getDatabase().getAllAudios().get(j);
                    }
                answer.append(outPut).append("\n");
            }
            return answer.toString();
        }
        else
            return "wrong order";
    }
    public String doFilter(String filter,String filterBy)
    {
        StringBuilder answer=new StringBuilder();
        if(filter.compareTo("A")==0)
        {
            ArtistModel artist=(showArtist(filterBy) instanceof ArtistModel)?(ArtistModel)showArtist(filterBy):null;
            if(artist!=null)
            {
                if(artist instanceof SingerModel)
                {
                    for(AlbumModel albumTemp :((SingerModel) artist).getAlbums())
                        if(albumTemp!=null)
                            for(MusicModel musicTemp:albumTemp.getMusics())
                                if(musicTemp!=null)
                                    answer.append(musicTemp).append("\n");
                }
                else if(artist instanceof PodcasterModel)
                {
                    for(PodcastModel temp:((PodcasterModel)artist).getPodcasts())
                        if(temp!=null)
                            answer.append(temp).append("\n");
                }
                return answer.toString();
            }
            return "artist not found";
        }
        else if(filter.compareTo("G")==0)
        {
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!= null && temp.getGenre().toString().compareTo(filterBy)==0)
                    answer.append(temp).append("\n");
            return answer.toString();
        }
        else if(filter.compareTo("D")==0)
        {
            String dateRegex="^\\d{4}/([1][0-2]|[1-9]|[0][1-9])/([1-2][0-9]|30|[0-9]|0[0-9])$";
            Pattern datePattern=Pattern.compile(dateRegex);
            if(!datePattern.matcher(filterBy).matches())
                return "birth date isn't valid";
            Calendar date =Calendar.getInstance();
            date.setTime(new Date(filterBy));
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!=null && temp.getReleaseDate().compareTo(date)==0)
                    answer.append(temp).append("\n");
            return answer.toString();
        }
        else
            return "wrong order";
    }
    public Map.Entry <Genre,Long>[] getTopTenMostPlayedGenre()
    {
        Map.Entry <AudioModel,Long>[] mostPlayed=getListener().getPlayingAmount().entrySet().toArray(new Map.Entry[getListener().getPlayingAmount().size()]);
        for(int i=0;i<mostPlayed.length-1;++i)
            for(int j=i+1;j<mostPlayed.length;++j)
                if(mostPlayed[i].getValue()<mostPlayed[j].getValue())
                {
                    Map.Entry <AudioModel,Long>temp=mostPlayed[j];
                    mostPlayed[j]=mostPlayed[i];
                    mostPlayed[i]=temp;
                }
        Map <Genre,Long> mostPlayedGenre=new HashMap<>();
        for(int i=0;i<mostPlayed.length;++i)
            if(mostPlayed[i].getValue()>0 && i<10)         //top ten most played
            {
                if(!mostPlayedGenre.containsKey(mostPlayed[i].getKey().getGenre()))
                    mostPlayedGenre.put(mostPlayed[i].getKey().getGenre(), 1L);
                else
                    mostPlayedGenre.replace(mostPlayed[i].getKey().getGenre(),mostPlayedGenre.get(mostPlayed[i].getKey().getGenre())+1);
            }
        Map.Entry <Genre,Long>[] favGenres=mostPlayedGenre.entrySet().toArray(new Map.Entry[mostPlayedGenre.size()]);
        for(int i=0;i<favGenres.length-1;++i)
            for(int j=i+1;j<favGenres.length;++j)
                if(favGenres[i].getValue()<favGenres[j].getValue())
                {
                    Map.Entry <Genre,Long>temp=favGenres[i];
                    favGenres[i]=favGenres[j];
                    favGenres[j]=temp;
                }
        return favGenres;
    }
    public Map.Entry <Genre,Long>[] getGenreOfLikedAudios()
    {
        Map <Genre,Long> genreOfLikedAudios=new HashMap<>();
        for(AudioModel temp: getListener().getLikedAudios())
            if(temp!=null)
            {
                if(!genreOfLikedAudios.containsKey(temp.getGenre()))
                    genreOfLikedAudios.put(temp.getGenre(),1L);
                else
                    genreOfLikedAudios.replace(temp.getGenre(),genreOfLikedAudios.get(temp.getGenre())+1);
            }
        Map.Entry <Genre,Long>[] genres=genreOfLikedAudios.entrySet().toArray(new Map.Entry[genreOfLikedAudios.size()]);
        for(int i=0;i<genres.length-1;++i)
            for(int j=i+1;j<genres.length;++j)
                if(genres[i].getValue()<genres[j].getValue())
                {
                    Map.Entry <Genre,Long>temp=genres[i];
                    genres[i]=genres[j];
                    genres[j]=temp;
                }
        return genres;
    }
    public ArrayList <AudioModel> getMostPlayedAudios()
    {
        AudioModel[] audios;
        audios=Database.getDatabase().getAllAudios().toArray(new AudioModel[Database.getDatabase().getAllAudios().size()]);
        for(int i=0;i<audios.length-1;++i)
            for(int j=i+1;j<audios.length;++j)
                if(audios[i].getPlayAmount()<audios[j].getPlayAmount())
                {
                    AudioModel temp=audios[i];
                    audios[i]=audios[j];
                    audios[j]=temp;
                }
        return new ArrayList<>(Arrays.asList(audios));
    }
    public String getSuggestions()
    {
        StringBuilder answer=new StringBuilder();
        int counter=0;
        Map.Entry <Genre,Long>[] mostPlayedGenre=getTopTenMostPlayedGenre();
        Map.Entry <Genre,Long>[] likedGenres=getGenreOfLikedAudios();
        ArrayList <Genre> genres=new ArrayList<>();
        if(mostPlayedGenre[0].getKey()!=null)
            genres.add(mostPlayedGenre[0].getKey());
        if(mostPlayedGenre[1].getKey()!=null)
            genres.add(mostPlayedGenre[1].getKey());
        if(likedGenres[0].getKey()!=null)
            genres.add(likedGenres[0].getKey());
        if(likedGenres[1].getKey()!=null)
            genres.add(likedGenres[1].getKey());
        if(getListener().getFavGenres().get(0)!=null)
            genres.add(getListener().getFavGenres().get(0));
        if(getListener().getFavGenres().get(1)!=null)
            genres.add(getListener().getFavGenres().get(1));
        for(ArtistModel temp:getListener().getFollowings())
        {
            if(counter==10)
                break;
            if(temp instanceof PodcasterModel)
            {
                BREAK:
                for(PodcastModel audioTemp:((PodcasterModel)temp).getPodcasts())
                    if(audioTemp!=null)
                        for(Genre genreTemp:genres)
                        {
                            if(counter>=3)
                                break BREAK;
                            if(genreTemp!=null && audioTemp.getGenre().compareTo(genreTemp)==0)
                            {
                                answer.append(audioTemp).append("\n");
                                counter++;
                            }
                        }
            }
            else
            {
                BREAK:
                for(AlbumModel albumTemp:((SingerModel)temp).getAlbums())
                    if(albumTemp!=null)
                        for(MusicModel musicTemp:albumTemp.getMusics())
                            if(musicTemp!=null)
                                for(Genre genreTemp:genres)
                                {
                                    if(counter>=3)
                                        break BREAK;
                                    if(genreTemp!=null && musicTemp.getGenre().compareTo(genreTemp)==0)
                                    {
                                        answer.append(musicTemp).append("\n");
                                        counter++;
                                    }
                                }
            }

        }
        if(counter==10)
            return answer.toString();
        else
        {
            ArrayList <AudioModel>audios=getMostPlayedAudios();
            for(int i=0;i<10-counter;++i)
                if(audios.get(i)!=null)
                    answer.append(audios.get(i)).append("\n");
            return answer.toString();
        }
    }
    public String makePlayList(String playlistName)
    {
        if(getListener() instanceof PremiumListenerModel || (getListener() instanceof FreeListenerModel && ((FreeListenerModel) getListener()).getCreatedPlayLists()<FreeListenerModel.getPlayListLimit()))
        {
            amountOfPlayLists++;
            PlayListModel temp=new PlayListModel(playlistName,getListener().getUserName());
            long playListID=0;
            char[] userName=getListener().getUserName().toCharArray();
            for(int i=0;i<userName.length;++i)
                playListID+=userName[i];
            String fullID=Long.toString(playListID)+Long.toString(amountOfPlayLists);
            temp.setPlayListID(Long.parseLong(fullID));
            getListener().getPlayLists().add(temp);
            ((FreeListenerModel)getListener()).setCreatedPlayLists(((FreeListenerModel) getListener()).getCreatedPlayLists()+1);
            return "playList made successfully";
        }
        else
            return "you already created 3 playlists";
    }
    public String showPlayLists()
    {
        StringBuilder answer=new StringBuilder();
        for(PlayListModel temp: getListener().getPlayLists())
            if(temp!=null)
                answer.append(temp).append("\n");
        return answer.toString();
    }
    public String showPlayList(String playListName)
    {
        for(PlayListModel temp: getListener().getPlayLists())
            if(temp!=null && temp.getPlayListName().compareTo(playListName)==0)
                return temp.toString();
        return "playlist not found";
    }
}
