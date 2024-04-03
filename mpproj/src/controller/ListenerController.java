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
            getListener().addToFavoriteGenres(Genre.valueOf(string));
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
        }
        return txt;
    }

    public void getPremium(int days,int payment){
        if(getListener().getAccountCredit()>=payment){
            getListener().setAccountCredit(getListener().getAccountCredit()-payment);
            if(getListener() instanceof FreeListener){
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DAY_OF_MONTH, days);
                Date expDate = cal.getTime(); 
                PremiumListener tmp = new PremiumListener(getListener().getPassword(), getListener().getUsername(), getListener().getName(), getListener().getEmailAddress(), getListener().getPhoneNumber(), getListener().getBirthDate(), getListener().getAccountCredit(),days,expDate);
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
        }

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
            }
        }
        getListener().getAudioPlays().put(id, (long)1);
        return AudioController.getAudioController().playAudio(id);
    
    }
    
    public String addAudioLikes(long id){
        for(Map.Entry<Long,Long> entry : getListener().getAudioLikes().entrySet())
        {
            if(entry.getKey()==(Long)id){
                entry.setValue(entry.getValue()+1);
            }
        }
        getListener().getAudioLikes().put(id, (long)1);
        return AudioController.getAudioController().likeAudio(id);
        
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
        return "you cant create more playlists , get a premium account and you have no limit";
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
                playlist.addToAudioList(tmp);
                return " the audio added to your playList";
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
            txt+="-"+playlist.getPlayListName()+"("+String.valueOf(playlist.getAudioList().size())+"audios)\n";
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
            }
            else return "you dont have a play list with this name , make sure you typed it correctly";
        }
        return txt;
    }

    public void suggestion(){
        Map<Long,Long> map = getListener().getAudioPlays();
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
        ArrayList<Long> artists = new ArrayList<>();
        


    }
    
    
}
