package controller;
import model.Audio.*;
import model.Database.Database;
import model.UserAccount.*;
import model.*;

import java.util.ArrayList;
import java.util.Date;

public class ListenerControler {
    Listener listenerr;

    public Listener getListenerr() {
        return listenerr;
    }

    public void setListenerr(Listener listenerr) {
        this.listenerr = listenerr;
    }

    public String signUpListener(String username, String pasword, String name, String email, String phoneNum, Date birthDate){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username)){
                return "error : this user name already exist .";
            }
        }
        Listener listener=new Listener(username,pasword,name,email,phoneNum,birthDate,50);
        setListenerr(listener);
        //add to database
        listener.setIsLogin(true);
        return showgenre()+"\n"+"listener accaount successfully created .";
    }
    String showgenre(){
        return Genre.Country+","+Genre.Pop+","+Genre.HipHop+","+Genre.History+","+Genre.Interview+","+Genre.Jazz+","+Genre.TrueCrime+","+Genre.Society+","+Genre.Rock;
    }
    public String login(String username,String password){
        ArrayList<User>users=Database.getDatabase().getUsers();
        for(User user:users){
            if(user.getUsername().equals(username) && user.getPassword().equals(password)){
                if(listenerr.getIsLogin()==true)
                    return "you are in already in your profile .";
                setListenerr((Listener) user);
                listenerr.setIsLogin(true);
                return "login successful .";
            }
        }
        return "error : user name or password is wrong";
    }
    public String logout(){
        ArrayList<User>users=Database.getDatabase().getUsers();
        listenerr.setIsLogin(false);
        return "logout successfull";
    }
    public String makePlaylist(String playlistName) {
        for (Playlist playlist : listenerr.getPlaylists()) {
            if (playlist.getName().equals (playlistName)) {
                return "this play list already exist.";
            }
        }
        if (!(listenerr instanceof PrimiumListener)) {
            if (listenerr.getPlaylistcounter() >= 3) {
                return "you reached maximum limit .";
            }
        }
        listenerr.setPlaylistcounter(listenerr.getPlaylistcounter(), 1);
        listenerr.getPlaylists().add(new Playlist(generateIdPlaylist(), playlistName, listenerr.getFullName()));
        return "play list added .";
    }
    public boolean islogin(){
        return listenerr.getIsLogin();
    }
    private int generateIdPlaylist(){
        return listenerr.getPlaylistcounter()+ Playlist.getIdcounter();
    }
    public String AddAudio(String playListname,int auidioId) {
        for (Playlist playlist : listenerr.getPlaylists()) {
            if (playlist.getName().equals(playListname)) {
                if (listenerr instanceof RegularListener) {
                    if (playlist.getAudios().size() >= ((RegularListener) listenerr).getAddLimit()) {
                        return "maximum add is 10.";
                    }
                }
                for (Audio audio : Database.getDatabase().getAudios()) {
                    if (audio.getId() == auidioId) {
                        playlist.getAudios().add(audio);
                        return "audio successfully added . ";
                    }
                }
            }
        }
        return "error: no match for audio id or play list name .";
    }
    public String playAudio(int audioId){
        for (Audio audio:Database.getDatabase().getAudios()){
            if(audio.getId()==audioId){
                audio.setPlayCount(audio.getPlayCount()+1);
                return "playing "+audio.getTitle();
            }
        }
        return "not found";
    }
    public String likeAudio(int audioId){
        for (Audio audio:Database.getDatabase().getAudios()){
            if(audio.getId()==audioId){
                audio.setLikes(audio.getLikes()+1);
                return "liked "+audio.getTitle();
            }
        }
        return "not found";
    }
    public String showFollowing(){
        StringBuilder result=new StringBuilder("you are following :\n");
        for(Artist artist:listenerr.getFollowings()){
            result.append(artist.getFullName()).append("\n");
        }
        return String.valueOf(result);
    }
    public String reportArtist(String artistusername,String explanation){
        for(User artist:Database.getDatabase().getUsers()){
            if(artist instanceof Artist){
                if (artist.getUsername().equals(artistusername)){
                    ArrayList<Report> reports=Database.getDatabase().getReports();
                    Report report=new Report(listenerr, (Artist) artist,explanation);
                    reports.add(report);
                    return "report send";
                }
            }
        }
        return "error";
    }
    public String showArtists(){
        StringBuilder result=new StringBuilder("Artis:\n");
        for(User artist:Database.getDatabase().getUsers()) {
            if (artist instanceof Artist) {
                result.append("artis name :").append(artist.getFullName()).append(" followers : ").append(((Artist) artist).getFollowers()).append(" biography : ").append(((Artist) artist).getBiography()).append("\n");
                if(artist instanceof Singer){
                    result.append("musics : ");
                    for(Music music: ((Singer) artist).getMusics()){
                        result.append(music.getTitle());
                    }
                }
                if(artist instanceof Podcaster){
                    result.append("podcasts : ");
                    for(Podcast podcast: ((Podcaster) artist).getPodcasts()){
                        result.append(podcast.getTitle());
                    }
                }
            }
        }
        return String.valueOf(result);
    }
    public String userInfo(){
        return listenerr.toString();
    }
    //جستجو، مرتبسازی و فیلتر فالهای صوتی
    public String showmyPlaylist(){
        ArrayList<Playlist>myList=listenerr.getPlaylists();
        StringBuilder result=new StringBuilder("my play list : \n");
        for(Playlist playlist:myList){
            result.append(playlist.getName()).append(" , ");
        }
        return String.valueOf(result);
    }
    public  String myListInfo(String playlistName){
        ArrayList<Playlist>myList=listenerr.getPlaylists();
        for(Playlist playlist:myList){
            if(playlistName.equals(playlist.getName())){
                StringBuilder result=new StringBuilder("my song on this  play list : \n");
                for (Audio audio:playlist.getAudios()){
                    result.append(audio.getTitle()).append(" , ");
                }
                return String.valueOf(result);
            }
        }
        return "error : not foound play llist";
    }
//● مشاهدۀ فال صوتیهای ͖یشنهاد شده به کاربر با توجه به ژانر و آرتیست مورد علاقۀ او
//● مشاهدۀ اطلاعات حساب کاربری
// خرید یا تمدید اشتراک متفاوت برای عادی ورموم و افزایش اعتبار حساب کاربری
}
