package controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import model.audio.Album;
import model.audio.Audio;
import model.audio.Genre;
import model.audio.PlayList;
import model.database.Database;
import model.user.Artist;
import model.user.FreeListener;
import model.user.Listener;
import model.user.Podcaster;
import model.user.PremiumListener;
import model.user.Report;
import model.user.Singer;
import model.user.User;

public class ListenerController {
    private static ListenerController listenerController;
    protected ListenerController(){

    }
    public static ListenerController getListenerController(){
        if(listenerController==null){
            listenerController = new ListenerController();
        }
        return listenerController;
    }
    private Listener listener;
    public Listener getListener(){
        return listener;
    }
    public void setListener(Listener listener){//in login
        this.listener = listener;
    }
    public void loginListener(Listener listener){
        setListener(listener);
    }
 
    public void getFavoriteGenres(ArrayList<String> genres){
        for (String string : genres) {
            getListener().addToFavoriteGenres(Genre.valueOf(string.toUpperCase()));
        }  
    }

    public String ShowAccountInfo(){
        String txt="Account info:"+
        "\nuser name : "+getListener().getUsername()+
        "\nFirst name : "+getListener().getName()+
        "\nemail address: "+getListener().getEmailAddress()+
        "\npassword : "+getListener().getPassword()+
        "\nbirth date : "+String.valueOf(getListener().getBirthDate())+
        "\nAccount Credit : "+String.valueOf(getListener().getAccountCredit())+"\n";
        if(getListener() instanceof PremiumListener){
            txt+="Premium Expiration Date : "+String.valueOf(((PremiumListener)getListener()).getPremiumExpirationDate());
            txt+="RemainingDaysOfPremium : "+String.valueOf(((PremiumListener)getListener()).getRemainingDaysOfPremium());
        }
        return txt;
    }

    public String getPremium(int days,int payment){//2ta overload
        if(getListener().getAccountCredit()>=payment){
            getListener().setAccountCredit(getListener().getAccountCredit()-payment);
            if(getListener() instanceof FreeListener){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expDate = cal.getTime(); 
                PremiumListener tmp = new PremiumListener(getListener().getPassword(), getListener().getUsername(), getListener().getName(), getListener().getEmailAddress(), getListener().getPhoneNumber(), getListener().getBirthDate(), getListener().getAccountCredit(),days,expDate);
                tmp.setAudioPlays(getListener().getAudioPlays());
                tmp.setFavoriteGenres(getListener().getFavoriteGenres());
                tmp.setLikedAudios(getListener().getLikedAudios());
                tmp.setListOfPlayLists(getListener().getListOfPlayLists());
                for(User user : Database.getDatabase().getAllUsers()){
                    if(user instanceof Artist){
                        if(((Artist)user).getFollowers().contains(getListener())){
                            ((Artist)user).getFollowers().remove(getListener());
                            ((Artist)user).getFollowers().add(tmp);
                        }
                    }
                }
                if(Database.getDatabase().getAllUsers().contains(getListener())){
                    boolean a =(Database.getDatabase().getAllUsers().remove(getListener()));
                    a = (Database.getDatabase().getAllUsers().add(tmp));
                }
                for(Report report : Database.getDatabase().getAllReports()){
                    if(report.getReportingUser().getUsername().equals(getListener().getUsername())){
                        report.setReportingUser(tmp);
                    }
                }
                setListener(tmp);
                //change the method with type casting?to nut remove user
                //exchanging two listeners in all felds they are in->database,artist,report
            }
            else{
                Calendar cal = Calendar.getInstance();
                Date date = getListener().getPremiumExpirationDate();
                cal.setTime(date);
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expirationDate = cal.getTime(); 
                getListener().setPremiumExpirationDate(expirationDate);
                Listener tmp = getListener();
                if(tmp instanceof PremiumListener){
                    PremiumListener tmpp = (PremiumListener)tmp;
                    tmpp.setRemainingDaysOfPremium(tmpp.getRemainingDaysOfPremium()+days);
                }
            }
            return "your account got premium";
        }
        return "you dont have enough credit to get premium";

    }

    public String showAllArtists(){
        String txt = "All Artists\n";
        for(User user:Database.getDatabase().getAllUsers()){
            if(user instanceof Artist){
                txt+="-"+user.getUsername()+"\n";
            }
        }
        return txt;
    }

    public String showArtistInfo(String username){
        User user =UserController.getUserController().findUser(username);
        
        if(user instanceof Singer){
            SingerController.getSingerController().setSinger((Singer)user);
            String txt = SingerController.getSingerController().ShowSingerInfo();
            return txt;
        }
        if(user instanceof Podcaster){
            PodcasterController.getPodcasterController().setPodcaster((Podcaster)user);
            String txt =PodcasterController.getPodcasterController().ShowPodcasterInfo();
            return txt;
        }
           
        return null;
    }

    public String followArtist(String username){
        User user = UserController.getUserController().findUser(username);
        if(user instanceof Artist){
            ((Artist)user).addFollowers(getListener());
            getListener().setNumberOfFollowing(getListener().getNumberOfFollowing()+1);
            return"you've followed '"+username+"' succesfullly";
        }
        else if(user == null){
            return"the username is not valid";
        }
        else{
            return "this user is not an Artist! enter an artist username for following";
        }
    }

    public String increaseAccountCredit(double amount){
        getListener().setAccountCredit(getListener().getAccountCredit()+amount);
        return "the amount added to your credit";
    }

    public String ShowFollowings(){
        String txt="All Followings\n";
        if(getListener().getNumberOfFollowing()==0){
            txt+="you have no following yet";
            return txt;
        }
        for(User user : Database.getDatabase().getAllUsers()){
            if(user instanceof Artist){
                Artist artist = (Artist)user;
                for(User listenerp : artist.getFollowers()){
                    if(listenerp.getUsername().equals(getListener().getUsername())){
                        txt+="-"+artist.getUsername()+"\n";
                    }

                }
            }
        }
        return txt;
    }

    public String reportArtist(String username , String explanation){
        User user = UserController.getUserController().findUser(username);
        if(user instanceof Artist){
            Report tmp = new Report(getListener(), null, explanation);
            Database.getDatabase().addToAllReports(tmp);
            return "thanks for your feedback , your report has been recieved!";
        }
        else{
            return "invalid username for an Artist ! make sure to enter an Artist's username ";
        }
    }

    public String addAudioPlay(long id){
        for(Map.Entry<Long,Long> entry : getListener().getAudioPlays().entrySet())
        {
            if(entry.getKey()==(Long)id){
                entry.setValue(entry.getValue()+1);
                return AudioController.getAudioController().playAudio(id);
            }
        }
        getListener().getAudioPlays().put(id, (long)1);
        return AudioController.getAudioController().playAudio(id);
    
    }
    
    public String likeAudio(long id){
        boolean check = AudioController.getAudioController().likeAudio(id);
        if(check){
            for(Long audioId: getListener().getLikedAudios()){
                if(audioId==id){
                    return "you can like an audio only one time";
                }
            }
            getListener().addToLikedAudios(id);
            return "thanks for your feedback";
        }
        else{
            return "not a valid audio id";
        }
        
    }

    public String createNewPlaylist(String name){
        if(getListener() instanceof FreeListener){
            return createNewPlaylist((FreeListener)getListener(),name);
        }
        if(getListener() instanceof PremiumListener){
            return createNewPlaylist((PremiumListener)getListener(),name);
        }

        return null;
    }
    public String createNewPlaylist(FreeListener freeListener,String name){
        if(freeListener.getListOfPlayLists().size()<FreeListener.getPlayListLimit()){
            freeListener.addToListOfPlayLists(new PlayList(name, freeListener.getUsername()));
            return "your playlist created succesfully";
        }
        return "you cant create more playlists , get a premium account and you will have no limit";
    }
    public String createNewPlaylist(PremiumListener premiumListener, String name){
        premiumListener.addToListOfPlayLists(new PlayList(name, premiumListener.getUsername()));

        return "your playlist created succesfully";
    }

    public String addAudioToPlaylist(String playlistName, long audioId){
        if(getListener() instanceof FreeListener){
            return addAudioToPlaylist((FreeListener)getListener(), playlistName,audioId);
        }
        if(getListener() instanceof PremiumListener){
            return addAudioToPlaylist((PremiumListener)getListener(), playlistName,audioId);
        }
        return null;
        
    }
    public String addAudioToPlaylist(FreeListener free, String playlistName, long audioId)
    {
        Audio tmp= null;
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if(audio.getId()==audioId){
                tmp= audio;
            }
        }
        if(tmp==null){
            return "couldnt find this audio , make sure you are entering the correct id";
        }
        for(PlayList playlist : free.getListOfPlayLists()){
            if(playlist.getPlayListName().equals(playlistName)){
                if(playlist.getAudioList().size()==FreeListener.getAddSongToPLaylistLimit()){
                    return "you cant add more song to a playlist , get premium to have no limits";
                }
                for(Audio audio:playlist.getAudioList()){
                    if(audio.getId()==audioId){
                        return "you alraedy added this audio to this playlist";
                    }
                }
                playlist.addToAudioList(tmp);
                return "the audio added to your playList";
            }
        }
        return "playlist with this name doesnt exist in your account";
    }
    public String addAudioToPlaylist(PremiumListener premium, String playlistName, long audioId)
    {
        Audio tmp= null;
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if(audio.getId()==audioId){
                tmp= audio;
            }
        }
        if(tmp==null){
            return "couldnt find this audio , make sure you are entering the correct id";
        }
        for(PlayList playlist : premium.getListOfPlayLists()){
            if(playlist.getPlayListName().equals(playlistName)){
                for(Audio audio:playlist.getAudioList()){
                    if(audio.getId()==audioId){
                        return "you alraedy added this audio to this playlist";
                    }
                }
                playlist.addToAudioList(tmp);
                return " the audio added to your playList";
            }
        }
        return "playlist with this name doesnt exist in your account";
    }

    public String showPlaylists(){
        String txt= "All playlists\n";
        if(getListener().getListOfPlayLists().size()==0){
            return "no playlist has been created, create a playlist by 'NewPlaylist -[playlist’s name]' command";
        }
        for(PlayList playlist : getListener().getListOfPlayLists()){
            txt+="-"+playlist.getPlayListName()+"("+String.valueOf(playlist.getAudioList().size())+" audios)\n";
        }
        return txt;
    }
    public String selectPlaylist(String name){
        String txt="play list ("+ name + ")\n";
        if(getListener().getListOfPlayLists().size()==0){
            return "your have not created any playLists and cant have access to other playlists";
        }
        for(PlayList playlist : getListener().getListOfPlayLists()){
            if(playlist.getPlayListName().equals(name)){
                txt+="creator - "+playlist.getCreaterName()+"\n"+
                "list of audios : \n";
                if(playlist.getAudioList().size()==0){
                    txt+="no audios added to this playlist";
                }
                for(Audio audio : playlist.getAudioList()){
                    txt+="-"+audio.getAudioName()+"("+String.valueOf(audio.getNumberOfPlays())+")\n";
                }
                return txt;
            }
        }
        return "you dont have a play list with this name , make sure you typed it correctly";
    }
    private int suggestByViews(int count,ArrayList<Long> suggestId,Map.Entry<Long,Long>[] arrmap){
        int c=0;
        for(int i = 0; i <arrmap.length; i++){
            if((suggestId.contains(arrmap[i].getKey())==false)){
                            System.out.println(suggestId.contains(arrmap[i].getKey()));
                            System.out.println("not contain view "+arrmap[i].getKey());
                            suggestId.add(arrmap[i].getKey());
                            c++;
                            count--;
                            if(count==0){
                                break;
                            }
                        }
                }
        // if(arrmap.length>=count){
        //     for(int i = 0; i <count; i++){
        //         if((suggestId.contains(arrmap[i].getKey())==false)){
        //             System.out.println(suggestId.contains(arrmap[i].getKey()));
        //             System.out.println("not contain view "+arrmap[i].getKey());
        //             suggestId.add(arrmap[i].getKey());
        //             c++;
        //         }
                
        //     }
        // }
        // else{
        //     for(int i = 0; i <arrmap.length; i++){
        //         suggestId.add(arrmap[i].getKey());
        //         c++;
        //     }
        // }
        return c;
    }
    private int suggestByLikes(int count,ArrayList<Long> suggestId){
        int c=0;
        for(Long id : getListener().getLikedAudios()){
            if((suggestId.contains(id))==false){
                            System.out.println(suggestId.contains(id));
                            System.out.println("not contain like "+id);
                            suggestId.add(id);
                            c++;
                            count--;
                            if (count==0) {
                                break;
                            }
                        }
        }
        // if(getListener().getLikedAudios().size()>=count){
        //     for(int i = 0; i <count; i++){
        //         if((suggestId.contains(getListener().getLikedAudios().get(i)))==false){
        //             System.out.println(suggestId.contains(getListener().getLikedAudios().get(i)));
        //             System.out.println("not contain like "+getListener().getLikedAudios().get(i));
        //             suggestId.add(getListener().getLikedAudios().get(i));
        //             c++;
        //         }
        //     }
        // }
        // else{
        //     for(Long id : getListener().getLikedAudios()){
        //         suggestId.add(id);
        //         c++;
        //     }
        // }
        return c;
    }
    private int suggestByArtist(int count,ArrayList<Long> suggestId,ArrayList<Long> artistAudio){
        int c=0;
        for(Long id : artistAudio){
            if((suggestId.contains(id))==false){
                System.out.println(suggestId.contains(id));
                System.out.println("not contain artist "+id);
                suggestId.add(id);
                count--;
                c++;
                if(count==0){
                    break;
                }
            }
        }
        return c;
    }
    private int suggestByGenre(int count,ArrayList<Long> suggestId,ArrayList<Long> genreAudio){
        int c=0;
        for(Long id : genreAudio){
            if((suggestId.contains(id))==false){
                System.out.println(suggestId.contains(id));
                System.out.println("not contain genre "+id);
                suggestId.add(id);
                count--;
                c++;
                if(count==0){
                    break;
                }
            }
        }
        return c;
    }
    private Map.Entry<Long,Long>[] sortAudioPlaysMap(Map<Long,Long> map){
        Map.Entry<Long,Long>[] arrmap =map.entrySet().toArray(new Map.Entry[map.size()]);
        for (int i = 0; i < arrmap.length-1; i++) {
            for (int j = i+1; j < arrmap.length; j++) {
                if(arrmap[j].getValue()>arrmap[i].getValue()){
                    Map.Entry<Long,Long> tmp = arrmap[i];
                    arrmap[i] = arrmap[j];
                    arrmap[j] =tmp;
                }
            }
        }
        return arrmap;
    }
    private ArrayList<Long> getArtistAudioArr(){
        ArrayList<String> followings =new ArrayList<>();
        for(User user : Database.getDatabase().getAllUsers()){
            if(user instanceof Artist){
                Artist artist = (Artist)user;
                for(User listenerp : artist.getFollowers()){
                    if(listenerp.getUsername().equals(getListener().getUsername())){
                        followings.add(artist.getUsername());
                    }

                }
            }
        }
        ArrayList<Long> artistAudio =new ArrayList<>();
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if(followings.contains(audio.getArtistUsername())){
                artistAudio.add(audio.getId());
            }
        }
        return artistAudio;

    }
    
    private ArrayList<Long> getGenreAudioArr(){
        ArrayList<Long> genreAudio =new ArrayList<>();
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if(getListener().getFavoriteGenres().contains(audio.getGenre())){
                    genreAudio.add(audio.getId());
                }
        }
        return genreAudio;
    }
    
    //get sort audio plays
    //get artist audio 
    //get genre audio


    public String suggestion(int n){
        Map<Long,Long> map = getListener().getAudioPlays();
        Map.Entry<Long,Long>[] arrmap = sortAudioPlaysMap(map);
        ArrayList<Long> artistAudio = getArtistAudioArr();
        ArrayList<Long> genreAudio = getGenreAudioArr();
        ArrayList<Long> suggestId = new ArrayList<>();
        Map<Long,Long> order = new HashMap<>();
        order.put(1L, (long)arrmap.length);
        order.put(2L, (long)artistAudio.size());
        order.put(3L, (long)genreAudio.size());
        order.put(4L, (long)getListener().getLikedAudios().size());
        Map.Entry<Long,Long>[] arrOrder =order.entrySet().toArray(new Map.Entry[order.size()]);
        for (int i = 0; i < arrOrder.length-1; i++) {
            for (int j = i+1; j < arrOrder.length; j++) {
                if(arrOrder[j].getValue()<= arrOrder[i].getValue()){
                    Map.Entry<Long,Long> tmp = arrOrder[i];
                    arrOrder[i] = arrOrder[j];
                    arrOrder[j] =tmp;
                }
            }
        }int c=0;
        int count = n/4;
        int tmp = n;

        for (int i = 0; i < 4; i++) {
            count = tmp/(4-i);
            if(arrOrder[i].getKey()==1L){
                c = suggestByViews(count, suggestId, arrmap);
                tmp-=c;
            }
            if(arrOrder[i].getKey()==2L){
                c = suggestByLikes(count, suggestId);
                tmp-=c;
            }
            if(arrOrder[i].getKey()==3L){
                c = suggestByArtist(count, suggestId, artistAudio);
                tmp-=c;
            }
            if(arrOrder[i].getKey()==4L){
                c = suggestByGenre(count, suggestId, genreAudio);
                tmp-=c;
            }
        }
        System.out.println("view");
        for(int i = 0; i <arrmap.length; i++){
            System.out.print(arrmap[i].getKey()+",");
           
        }
        System.out.println();
        System.out.println("artist "+artistAudio);
        System.out.println("genre "+genreAudio);
        System.out.println("like "+getListener().getLikedAudios());
        System.out.println("suggest "+suggestId);
        String txt = "suggestion Audios for you\n";
        for(Audio audio : Database.getDatabase().getAllAudio()){
            if(suggestId.contains(audio.getId())){
                txt+="-"+audio.getAudioName()+"\n";
            }
        }
        return txt;


    }
    
    
}
