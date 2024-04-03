package controller;

import model.Database;
import model.audioRelated.*;
import model.report.Report;
import model.users.AccountUserModel;
import model.users.Subscription;
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
            tempListener.setListenerCredit(50);
            tempListener.setSubscriptionExpiration(null);
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
    public void changeToFree()
    {
        String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
        FreeListenerModel freeListener=new FreeListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
        freeListener.setSubscriptionExpiration(null);
        freeListener.setListenerCredit(getListener().getListenerCredit());
        freeListener.setFollowings(getListener().getFollowings());
        freeListener.setFavGenres(getListener().getFavGenres());
        freeListener.setLikedAudios(getListener().getLikedAudios());
        freeListener.setPlayingAmount(getListener().getPlayingAmount());
        freeListener.setPlayLists(getListener().getPlayLists());
        Database.getDatabase().getAllUsers().remove(getListener());
        Database.getDatabase().getAllUsers().add(freeListener);
        setListener(freeListener);
    }
    public String addToPlayList(String playListName,String audioID)
    {
        if (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()<=0)
        {
            changeToFree();
            return addToPlayList(playListName,audioID);
        }
        else if((getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()>0) || (getListener() instanceof FreeListenerModel && (((FreeListenerModel) getListener()).getAddedAudios()<FreeListenerModel.getAddAudioLimit())))
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
            if(getListener() instanceof  FreeListenerModel)
                ((FreeListenerModel)getListener()).setAddedAudios(((FreeListenerModel) getListener()).getAddedAudios()+1);
            return "audio added to playlist successfully";
        }
        else
            return "you already added 10 audios buy premium for more";
    }
    public String showArtists()
    {
        StringBuilder answer=new StringBuilder("Artists usernames:\n");
        for(AccountUserModel temp:Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel)
                answer.append(temp.getUserName()).append("\n");
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
            if(temp!=null)
            {
                if(firstRound)
                    answer.append("Audios:\n");
                if(temp.getAudioName().compareTo(audioOrArtistName)==0)
                    answer.append("Audio name: ").append(temp.getAudioName()).append("\nAudio ID: ").append(temp.getAudioID()).append("\n");
            }
            firstRound=false;
        }
        firstRound=true;
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
        {
            if(temp!=null)
            {
                if(firstRound)
                    answer.append("Artists:\n");
                if(temp instanceof ArtistModel && temp.getFullName().compareTo(audioOrArtistName)==0)
                    answer.append("User name: ").append(temp.getUserName()).append("\nFull name: ").append(temp.getFullName()).append("\n");
            }
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
                answer.append("AudioID: ").append(outPut.getAudioID()).append("\nAudio name: ").append(outPut.getAudioName()).append("\nlike amount: ").append(outPut.getLikeAmount()).append("\n");
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
                answer.append("AudioID: ").append(outPut.getAudioID()).append("\nAudio name: ").append(outPut.getAudioName()).append("\nplay amount: ").append(outPut.getPlayAmount()).append("\n");
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
                        {
                            answer.append("Album name: ").append(albumTemp.getAlbumName()).append("\nAlbum ID: ").append(albumTemp.getAlbumID()).append("\nArtist username: ").append(albumTemp.getNameOfArtist()).append("\n");
                            for(MusicModel musicTemp:albumTemp.getMusics())
                                if(musicTemp!=null)
                                    answer.append("Music name: ").append(musicTemp.getAudioName()).append("\nMusic ID: ").append(musicTemp.getAudioID()).append("\n");
                        }
                }
                else if(artist instanceof PodcasterModel)
                {
                    answer.append("Artist username: ").append(artist.getUserName()).append("\n");
                    for(PodcastModel temp:((PodcasterModel)artist).getPodcasts())
                        if(temp!=null)
                            answer.append("Podcast name: ").append(temp.getAudioName()).append("\nPodcast ID: ").append(temp.getAudioID()).append("\n");
                }
                return answer.toString();
            }
            return "artist not found";
        }
        else if(filter.compareTo("G")==0)
        {
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!= null && temp.getGenre().toString().compareTo(filterBy)==0)
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\n");
            return answer.toString();
        }
        else if(filter.compareTo("D")==0)
        {
            String dateRegex="^\\d{4}/([1][0-2]|[1-9]|[0][1-9])/([1-2][0-9]|30|[0-9]|0[0-9])$";
            Pattern datePattern=Pattern.compile(dateRegex);
            if(!datePattern.matcher(filterBy).matches())
                return "date isn't valid";
            Calendar date =Calendar.getInstance();
            date.setTime(new Date(filterBy));
            for(AudioModel temp:Database.getDatabase().getAllAudios())
                if(temp!=null && temp.getReleaseDate().compareTo(date)==0)
                    answer.append("AudioID: ").append(temp.getAudioID()).append("\nAudio name: ").append(temp.getAudioName()).append("\n");
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
        int audioAmount=0;
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
            if(audioAmount==10)
                break;
            if(temp instanceof PodcasterModel)
            {
                for(PodcastModel audioTemp:((PodcasterModel)temp).getPodcasts())
                {
                    if(counter>=3)
                        break;
                    if(audioTemp!=null)
                        for(Genre genreTemp:genres)
                        {
                            if(genreTemp!=null && audioTemp.getGenre().compareTo(genreTemp)==0)
                            {
                                answer.append("AudioID: ").append(audioTemp.getAudioID()).append("\nAudio name: ").append(audioTemp.getAudioName()).append("\n");
                                counter++;
                            }
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
                                        answer.append("AudioID: ").append(musicTemp.getAudioID()).append("\nAudio name: ").append(musicTemp.getAudioName()).append("\n");
                                        counter++;
                                    }
                                }
            }
            audioAmount+=counter;
           counter=0;
        }
        if(audioAmount==10)
            return answer.toString();
        else
        {
            ArrayList <AudioModel>audios=getMostPlayedAudios();
            for(int i=0;i<10-audioAmount;++i)
                if(audios.get(i)!=null)
                    answer.append("AudioID: ").append(audios.get(i).getAudioID()).append("\nAudio name: ").append(audios.get(i).getAudioName()).append("\n");
            return answer.toString();
        }
    }
    public String makePlayList(String playlistName)
    {
        if (getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()<=0)
        {
            changeToFree();
            return makePlayList(playlistName);
        }
        else if((getListener() instanceof PremiumListenerModel && ((PremiumListenerModel)getListener()).getRemainingDays()>0) || (getListener() instanceof FreeListenerModel && ((FreeListenerModel) getListener()).getCreatedPlayLists()<FreeListenerModel.getPlayListLimit()))
        {
            PlayListModel temp=new PlayListModel(playlistName,getListener().getUserName());
            long playListID=0;
            char[] userName=getListener().getUserName().toCharArray();
            for(int i=0;i<userName.length;++i)
                playListID+=userName[i];
            String fullID=Long.toString(playListID)+Long.toString(PlayListModel.getAmountOfPlaylists());
            temp.setPlayListID(Long.parseLong(fullID));
            getListener().getPlayLists().add(temp);
            if (getListener() instanceof FreeListenerModel)
                ((FreeListenerModel)getListener()).setCreatedPlayLists(((FreeListenerModel) getListener()).getCreatedPlayLists()+1);
            return "playList made successfully";
        }
        else
            return "you already created 3 playlists";
    }
    public String showPlayLists()
    {
        StringBuilder answer=new StringBuilder("Playlists name:\n");
        for(PlayListModel temp: getListener().getPlayLists())
            if(temp!=null)
                answer.append(temp.getPlayListName()).append("\n");
        return answer.toString();
    }
    public String showPlayList(String playListName)
    {
        for(PlayListModel temp: getListener().getPlayLists())
            if(temp!=null && temp.getPlayListName().compareTo(playListName)==0)
                return temp.toString();
        return "playlist not found";
    }
    public String playAudio(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                temp.setPlayAmount(temp.getPlayAmount()+1);
                if(!getListener().getPlayingAmount().containsKey(temp))
                    getListener().getPlayingAmount().put(temp,1L);
                else
                    getListener().getPlayingAmount().replace(temp,getListener().getPlayingAmount().get(temp)+1);
                return temp.toString();
            }
        return "Audio not found";
    }
    public String likeAudio(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                temp.setLikeAmount(temp.getLikeAmount()+1);
                getListener().getLikedAudios().add(temp);
                return "liked audio";
            }
        return "Audio not found";
    }
    public String showLyricOrCaption(String audioID)
    {
        for(AudioModel temp:Database.getDatabase().getAllAudios())
            if(temp!=null && temp.getAudioID()==Long.parseLong(audioID))
            {
                if(temp instanceof MusicModel)
                    return ((MusicModel)temp).getLyric();
                else
                    return ((PodcastModel)temp).getCaption();
            }
        return "Audio not found";
    }
    public String showFollowings()
    {
        StringBuilder answer=new StringBuilder("Following usernames:\n");
        for(ArtistModel temp:getListener().getFollowings())
            if(temp!=null)
                answer.append(temp.getUserName()).append("\n");
        return answer.toString();
    }
    public String report(String artistUserName,String explanation)
    {
        for(AccountUserModel temp :Database.getDatabase().getAllUsers())
            if(temp instanceof ArtistModel && temp.getUserName().compareTo(artistUserName)==0)
            {
                Report report=new Report((ArtistModel)temp,getListener(),explanation);
                Database.getDatabase().getReports().add(report);
                return "reported successfully";
            }
        return "Artist not found";
    }
    public void increaseCredit(String credit)
    {
        getListener().setListenerCredit(getListener().getListenerCredit()+Double.parseDouble(credit));
    }
    public String buyPremium(String pack)
    {
        Subscription subscription;
        if(pack.compareTo("30")==0)
        {
            subscription=Subscription.THIRTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            getListener().setSubscriptionExpiration(Calendar.getInstance());
            getListener().getSubscriptionExpiration().add(Calendar.DATE,30);
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-premiumListener.getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-premiumListener.getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-premiumListener.getSubscriptionExpiration().get(Calendar.DATE)));
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-getListener().getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-getListener().getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-getListener().getSubscriptionExpiration().get(Calendar.DATE)));
            return "package bought successfully";
        }
        else if(pack.compareTo("60")==0)
        {
            subscription=Subscription.SIXTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            getListener().setSubscriptionExpiration(Calendar.getInstance());
            getListener().getSubscriptionExpiration().add(Calendar.DATE,60);
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-premiumListener.getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-premiumListener.getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-premiumListener.getSubscriptionExpiration().get(Calendar.DATE)));
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-getListener().getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-getListener().getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-getListener().getSubscriptionExpiration().get(Calendar.DATE)));
            return "package bought successfully";
        }
        else if(pack.compareTo("180")==0)
        {
            subscription=Subscription.ONEHEIGHTY_DAYS;
            if(getListener().getListenerCredit()<subscription.getPrice())
                return "your credit isn't enough";
            getListener().setListenerCredit(getListener().getListenerCredit()-subscription.getPrice());
            getListener().setSubscriptionExpiration(Calendar.getInstance());
            getListener().getSubscriptionExpiration().add(Calendar.DATE,180);
            if(getListener() instanceof FreeListenerModel)
            {
                String birthDate=Integer.toString(getListener().getBirthDate().get(Calendar.YEAR))+"/"+Integer.toString((getListener().getBirthDate().get(Calendar.MONTH)+1))+"/"+Integer.toString(getListener().getBirthDate().get(Calendar.DATE));
                PremiumListenerModel premiumListener=new PremiumListenerModel(getListener().getUserName(),getListener().getPassword(),getListener().getFullName(),getListener().getEmail(),getListener().getPhoneNumber(),birthDate);
                premiumListener.setListenerCredit(getListener().getListenerCredit());
                premiumListener.setFollowings(getListener().getFollowings());
                premiumListener.setFavGenres(getListener().getFavGenres());
                premiumListener.setLikedAudios(getListener().getLikedAudios());
                premiumListener.setPlayingAmount(getListener().getPlayingAmount());
                premiumListener.setPlayLists(getListener().getPlayLists());
                premiumListener.setSubscriptionExpiration(getListener().getSubscriptionExpiration());
                premiumListener.setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-premiumListener.getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-premiumListener.getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-premiumListener.getSubscriptionExpiration().get(Calendar.DATE)));
                Database.getDatabase().getAllUsers().remove(getListener());
                Database.getDatabase().getAllUsers().add(premiumListener);
                setListener(premiumListener);
                return "package bought successfully";
            }
            ((PremiumListenerModel)getListener()).setRemainingDays(((Calendar.getInstance().get(Calendar.YEAR)-getListener().getSubscriptionExpiration().get(Calendar.YEAR))*365)+((Calendar.getInstance().get(Calendar.MONTH)-getListener().getSubscriptionExpiration().get(Calendar.MONTH))*30)+(Calendar.getInstance().get(Calendar.DATE)-getListener().getSubscriptionExpiration().get(Calendar.DATE)));
            return "package bought successfully";
        }
        else
            return "package not found";
    }
    public String getAccInfo()
    {
        if(getListener() instanceof PremiumListenerModel)
        {
            ((PremiumListenerModel)getListener()).setRemainingDays(((PremiumListenerModel)getListener()).getRemainingDays()-1);
            getListener().getSubscriptionExpiration().add(Calendar.DATE,-1);
        }
        return getListener().toString();
    }
}
