package controller.Listener;
import model.Audio.Audio;
import model.Audio.Music;
import model.Audio.Podcast;
import model.Database.Database;
import model.Genre.Genre;
import model.Playlist.Playlist;
import model.Report.Report;
import model.UserAccounts.Artist.Artist;
import model.UserAccounts.Listener.Free;
import model.UserAccounts.Listener.Listener;
import model.UserAccounts.userAccount;
import sun.font.TrueTypeFont;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class listenerController {
    private static listenerController listenerC;
    private Free freeM;
    private Listener listenerM;
    private listenerController(){
    }

    public static listenerController getListenerC() {
        if(listenerC==null){
            listenerC = new listenerController();
        }
        return listenerC;
    }

    public Free getFreeM() {
        return freeM;
    }

    public void setFreeM(Free freeM) {
        this.freeM = freeM;
    }
    public Listener getListenerM() {
        return listenerM;
    }

    public void setListenerM(Listener listenerM) {
        this.listenerM = listenerM;
    }

    //// Register free listener
    public boolean registerListener(String userId, String password, String fullName, String email, String phoneNumber, Date birthday, double credit, ArrayList<Playlist> playlists, ArrayList<Genre> favouriteGenres){
        for(userAccount userAccount:Database.getDatabase().getAllUsersList()){
            if (userAccount.getUserId().equals(userId)){
                return false;
            }
        }
        listenerM =freeM;

        Free tempListener = new Free(userId,password,fullName,email,phoneNumber,birthday,credit,playlists,favouriteGenres);
        setFreeM(tempListener);
        freeM.setCredit(50);
        Database.getDatabase().getAllUsersList().add(freeM);
        return true;
    }

    //Show Genres to  registered listener;

    //choose Genres to listener:
    public void chooseFourGenres(Genre genre1,Genre genre2,Genre genre3,Genre genre4){
        getFreeM().getFavouriteGenres().add(genre1);
        getFreeM().getFavouriteGenres().add(genre2);
        getFreeM().getFavouriteGenres().add(genre3);
        getFreeM().getFavouriteGenres().add(genre4);
    }

    /// Login free listener
    public boolean loginListener(String userId,String password){
        for(userAccount userAccount:Database.getDatabase().getAllUsersList()){
            if (userAccount.getUserId().equals(userId)&&userAccount.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }


    /// Create playlist
    public boolean createPlaylist(String name){
        if (listenerM instanceof Free){
            if (listenerM.getPlaylists().size()>=3){
                return false;
            }
        }
        Playlist playlist = new Playlist(name,listenerM.getUserId(),new ArrayList<Audio>());
        listenerM.getPlaylists().add(playlist);
        return true;
    }

    // Add audio to playlist

    public boolean addAudioToPlaylist(String name,int audioId){
        for (Playlist playlist:listenerM.getPlaylists()){
            if (playlist.getPlayListName().equals(name)){
                for (Audio audio:Database.getDatabase().getAllAudiosList()){
                    if (audio.getAudioId()==audioId){
                        playlist.getAudioList().add(audio);
                        return true;
                    }
                }

            }
        }
        return false;
    }

    // Play the audio
    public String playAudio(int audioId){
        for (Audio audio:Database.getDatabase().getAllAudiosList()){
            if (audio.getAudioId()==audioId){
                if (listenerM.getPlayFiles().containsKey(audio)){
                    listenerM.getPlayFiles().put(audio,listenerM.getPlayFiles().get(audio)+1);
                }
                else {
                    listenerM.getPlayFiles().putIfAbsent(audio,0);
                }
                return audio.toString();
            }
        }
        return "not found";
    }

    /// like audio
    public void likeAudio(int audioId){
        for (Audio audio:Database.getDatabase().getAllAudiosList()){
            if (audio.getAudioId()==audioId){
                audio.setNumberOfLikes(audio.getNumberOfLikes()+1);
            }
        }
    }

    /// show lyric
    public String lyricAudio(int audioId){
        for (Audio audio:Database.getDatabase().getAllAudiosList()){
            if (audio.getAudioId()==audioId){
                if(audio instanceof Music){
                    return ((Music) audio).getLyric();
                } else if (audio instanceof Podcast) {
                    return ((Podcast) audio).getCaption();
                }
            }
        }
        return "not found";
    }

    /// follow artist
    public void followArtist(String userId){
        for (userAccount user: Database.getDatabase().getAllUsersList()){
            if (user.getUserId().equals(userId)){
                if (user instanceof Artist){
                    ((Artist) user).getFollowersList().add(listenerM);
                    listenerM.getFollowings().add((Artist)user);
                }
            }
        }
    }

    //// show followings
    public String showFollowings(){
        StringBuilder context=new StringBuilder();
        if (listenerM.getFollowings().isEmpty()){
            return "There is no following for you!";
        }
        for (Artist artist:listenerM.getFollowings()){
            context.append(artist.toString());
            context.append("\n");
        }
        return context.toString();
    }

    /// report artist

    public void reportArtist(String userId,String explanation){
        for (userAccount user:Database.getDatabase().getAllUsersList()){
            if (user instanceof Artist && user.getUserId().equals(userId)) {
                Database.getDatabase().getReportsList().add(new Report(listenerM,(Artist)user,explanation));
            }
        }
    }

    /// increase credit
    public void increaseCredit(double value){
        listenerM.setCredit(listenerM.getCredit()+value);
    }

}
