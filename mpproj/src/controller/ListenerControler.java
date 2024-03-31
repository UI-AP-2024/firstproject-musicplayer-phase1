package controller;
import model.Audio.*;
import model.Database.Database;
import model.UserAccount.*;
import model.*;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class ListenerControler {
    private Listener listenerr;
    private static ListenerControler listenerControler;

    private ListenerControler() {
    }

    public static ListenerControler getListenerControler() {
        if (listenerControler == null)
            listenerControler = new ListenerControler();
        return listenerControler;
    }


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
        return showgenre()+"\n";
    }
    String showgenre(){
        return Genre.Country+","+Genre.Pop+","+Genre.HipHop+","+Genre.History+","+Genre.Interview+","+Genre.Jazz+","+Genre.TrueCrime+","+Genre.Society+","+Genre.Rock;
    }
    public String chooseFavoriteGenre(String genre1,String genre2,String genre3,String genre4){
        if(genre4.equals("")){
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre1));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre2));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre3));
        } else if (genre3.equals("")&&genre4.equals("")) {
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre1));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre2));
        } else if (genre2.equals("")&&genre3.equals("")&&genre4.equals("")){
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre1));
        }else {
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre1));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre2));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre3));
            listenerr.getFavoriteGenre().add(Genre.valueOf(genre4));
        }
        return "listener accaount successfully created .";
    }
    public void chooseFavoriteGenre(String genre1,String genre2,String genre3){
        chooseFavoriteGenre(genre1,genre2,genre3,"");
    }
    public void chooseFavoriteGenre(String genre1,String genre2){
        chooseFavoriteGenre(genre1,genre2,"","");
    }
    public void chooseFavoriteGenre(String genre1){
        chooseFavoriteGenre(genre1,"","","");
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
    public String followArtist(String artistusername){
        for(User user:Database.getDatabase().getUsers()){
            if(user.getUsername().equals(artistusername)){
                if(user instanceof Artist){
                    ((Artist) user).getFollowers().add(listenerr);
                    listenerr.getFollowings().add((Artist) user);
                    return "followed";
                }
            }
        }
        return "error";
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
                    for(Album album: ((Singer) artist).getAlbums()){
                        result.append(album.toString());
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
    public  String increaseCredit(int increaseAmount){
        listenerr.setCredit(listenerr.getCredit()+increaseAmount);
        return "increased";
    }
    public String buySub(SubscriptionPlan subscriptionPlan){
        String str=listenerr.getEndSubDate().toString();
        if(listenerr instanceof  RegularListener){
            int tmpindex=0;
            ArrayList<User> users=new ArrayList<>();
            for(User user : Database.getDatabase().getUsers()){
                if(user.getUsername().equals(listenerr)){
                    PrimiumListener tmplistenerr=new PrimiumListener(listenerr.getUsername(),listenerr.getPassword(),listenerr.getFullName(),listenerr.getEmail(),listenerr.getPhoneNumber(),listenerr.getDateOfBirth(),listenerr.getCredit());
                    tmplistenerr.setSubscription(subscriptionPlan);
                    Calendar calendar=Calendar.getInstance();
                    calendar.setTime(tmplistenerr.getEndSubDate());
                    if(subscriptionPlan.equals(SubscriptionPlan.Onemonth)){
                        calendar.add(Calendar.MONTH,1);
                        tmplistenerr.setEndSubDate(calendar.getTime());
                    }else if(subscriptionPlan.equals(SubscriptionPlan.TwoMonth)){
                        calendar.add(Calendar.MONTH,2);
                        tmplistenerr.setEndSubDate(calendar.getTime());
                    } else if (subscriptionPlan.equals(SubscriptionPlan.SixMonth)) {
                        calendar.add(Calendar.MONTH,6);
                        tmplistenerr.setEndSubDate(calendar.getTime());
                    }
                    listenerr=tmplistenerr;
                    users.add(listenerr);
                }else
                    users.add(user);
                tmpindex++;
            }
            Database.getDatabase().setUsers(users);
        }
        if(listenerr instanceof  PrimiumListener){
            Calendar calendar=Calendar.getInstance();
            calendar.setTime(listenerr.getEndSubDate());
            if(subscriptionPlan.equals(SubscriptionPlan.Onemonth)){
                calendar.add(Calendar.MONTH,1);
                listenerr.setEndSubDate(calendar.getTime());
            }else if(subscriptionPlan.equals(SubscriptionPlan.TwoMonth)){
                calendar.add(Calendar.MONTH,2);
                listenerr.setEndSubDate(calendar.getTime());
            } else if (subscriptionPlan.equals(SubscriptionPlan.SixMonth)) {
                calendar.add(Calendar.MONTH,6);
                listenerr.setEndSubDate(calendar.getTime());
            }
        }
        return "befor :"+str+"now :"+listenerr.getEndSubDate().toString();
    }
    public String suggestAudio(int n){
        n=10;
        Map.Entry<Integer,Integer>[] arrmap=listenerr.getListeningHistory().entrySet().toArray(new Map.Entry[listenerr.getListeningHistory().size()]);
        for (int i = 0; i <arrmap.length-1 ; i++) {
            for (int j = 0; j < arrmap.length-1-i; j++) {
                if (arrmap[j].getValue()<arrmap[j+1].getValue()) {
                    Map.Entry<Integer,Integer>tmp=arrmap[j];
                    arrmap[j]=arrmap[j+1];
                    arrmap[j+1]=tmp;
                }
            }
        }
        for(Audio audio:Database.getDatabase().getAudios()) {
            if(arrmap[0].getKey().equals(audio.getId())){
                for (User artist:Database.getDatabase().getUsers()){
  ///??????????????????????????????????????????
                }
            }
        }
    }
}
