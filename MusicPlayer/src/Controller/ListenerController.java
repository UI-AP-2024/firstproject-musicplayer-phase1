package Controller;
import Model.*;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController {
    private ListenerModel model;
    private int count=0;

    public String signUp(String username, String password, String fullName, String email, String phoneNumber, Date birthDate){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(username)){
                return "This Username already used!";
            }
        }
        Pattern emailPattern = Pattern.compile(".*@.*(\\.com|\\.ir|\\.org)$");
        Matcher matcherEmail = emailPattern.matcher(email);
        Pattern phonepattern = Pattern.compile("^\\d{8,11}$");
        Matcher matcherPhone = phonepattern.matcher(phoneNumber);
        Pattern passwordPattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,16}$");
        Matcher matcherPassword = passwordPattern.matcher(password);
        if(!matcherEmail.matches())
            return "The email is not valid!";
        else if(!matcherPhone.matches())
            return "The phone number is not valid!";
        else if(!matcherPassword.matches())
            return "Weak password! Pleas use better password.";

        model = new ListenerModel(username, password, fullName, email, phoneNumber, birthDate);
        model.setCredit(50);
        return "Done!";
    }
    public String logIn(String username, String password){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(username) && Database.getDatabase().getUsers().get(i).getPassword().equals(password)){
                User user = Database.getDatabase().getUsers().get(i);

                return "Done!";
            }
        }
        return "Username or password is wrong!";
    }
    public void selectGenres(Genre genre){

        if( count < 4 ){
            model.getFavoriteGenres()[count] = genre;
            count++;
        }
    }
    public String makeNewPlaylist(String playlistName,String userName){

        if(model.getClass().equals(Model.PremiumModel.class)) {
            model.getPlaylists().add(new Playlists(playlistName,userName));
            return "Playlist made successfully";
        }
        else if(model.playlistCount < FreeModel.maxPlaylist){
            model.getPlaylists().add(new Playlists(playlistName,userName));
            model.playlistCount++;
            return "Playlist made successfully";
        }

        return "Sorry! You can't creat more than 3 playlists with freeAccount.";
    }
    public String addMusicToPlaylist(String playlistName,int ID){
        if(model.getClass().equals(Model.PremiumModel.class)){
            searchAndAdd(playlistName,ID);
            return "Music added to playlist successfully.";
        }
        else if(model.musicCount < FreeModel.maxAudio){
            searchAndAdd(playlistName,ID);
            model.musicCount++;
            return "Music added to playlist successfully.";
        }
        return "Sorry! You can't add more than 10 Songs to the playlist with freeAccount.";
    }
    private void searchAndAdd(String playlistName,int ID){
        for (int i = 0; i < model.getPlaylists().size(); i++) {
            if(model.getPlaylists().get(i).getPlaylistName().equals(playlistName)){
                for (int j = 0; j < Database.getDatabase().getAudios().size(); j++) {
                    if(Database.getDatabase().getAudios().get(j).getIDCount()==ID){
                        model.getPlaylists().get(i).getAudio().add(Database.getDatabase().getAudios().get(j));
                    }
                }
            }
        }
    }
    public String playAudio(int ID){
        String show = "";
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getIDCount()==ID){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show = "Audio: " + audio.getAudioName() + "\tArtis: " + audio.getArtistName() + "\tLikes: " + audio.getLikeCount() +"\nGenre: " + audio.getGenre() + "\tRelease Date: " + audio.getReleaseDate() + "\tID: " + audio.getIDCount();
                audio.setPlayedCount(audio.getPlayedCount() + 1);
                model.Count++;
                model.getPlayCount().put(audio,model.Count);
            }
        }
        return show;
    }
    public void likeAudio(int ID){
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getIDCount()==ID){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                audio.setLikeCount(audio.getLikeCount() + 1);
                break;
            }
        }
    }
    public String showLyric(int ID){
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getIDCount()==ID){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                if(audio.getClass().equals(Music.class))
                    return ((Music) audio).getLyrics();
            }
        }
        return "Audio Not Found!";
    }
    public String searchAudio(String name){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getAudioName().equals(name) || Database.getDatabase().getAudios().get(i).getArtistName().equals(name)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio name: " + audio.getAudioName() + "\tArtist: " + audio.getArtistName() + "\t Genre: " + audio.getGenre() + "\n");
            }
        }
        return show.toString();
    }
    public void sortingAudioFilesBasedOnLikes(){

        for (int i = 0; i < Database.getDatabase().getAudios().size() - 1; i++) {
            int likeBefore = Database.getDatabase().getAudios().get(i).getLikeCount();
            for (int j = i + 1; j < Database.getDatabase().getAudios().size(); j++) {
                int likeAfter = Database.getDatabase().getAudios().get(j).getLikeCount();
                if(likeAfter > likeBefore){
                    int temp = likeAfter;
                    likeAfter = likeBefore;
                    likeBefore = temp;
                }
            }

        }

    }
    public void sortingAudioFilesBasedOnPlayCount(){

        for (int i = 0; i < Database.getDatabase().getAudios().size() - 1; i++) {
            int playCountBefore = Database.getDatabase().getAudios().get(i).getPlayedCount();
            for (int j = i + 1; j < Database.getDatabase().getAudios().size(); j++) {
                int playCountAfter = Database.getDatabase().getAudios().get(j).getPlayedCount();
                if(playCountAfter > playCountBefore){
                    int temp = playCountAfter;
                    playCountAfter = playCountBefore;
                    playCountBefore = temp;
                }
            }

        }

    }
    public String filterAudioFilesBasedOnArtist(String artistName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getArtistName().equals(artistName)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio.getAudioName() + "\tID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnGenres(Genre genre){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getGenre().equals(genre)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio.getAudioName() +"\tArtist: "+audio.getArtistName() +"\tID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnDate(Date releaseDate){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getDate().getYear()==releaseDate.getYear()){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio.getAudioName() + "\tArtist: "+audio.getArtistName()+"\tID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedAmongDate(Date releaseDate1,Date releaseDate2){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(releaseDate1.getYear() <= Database.getDatabase().getAudios().get(i).getDate().getYear() && Database.getDatabase().getAudios().get(i).getDate().getYear()<=releaseDate2.getYear()){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio.getAudioName() + "\tArtist: "+audio.getArtistName()+"\tID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public void ArtistReport(String userName,String description){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(userName)){
                ArtistModel artist =(ArtistModel) Database.getDatabase().getUsers().get(i);
                Database.getDatabase().getReports().add(new Report(model,artist,description));
                break;
            }
        }
    }
    public String showFollowing(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getFallowingArtist().size(); i++) {
            show.append("Artist: "+ model.getFallowingArtist().get(i).getFullName()+"\n");
        }
        return show.toString();
    }
    public String showArtistList(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getClass().equals(Model.SingerModel.class) || Database.getDatabase().getUsers().get(i).getClass().equals(Model.PodcasterModel.class)){
                show.append("Artist Name: " + Database.getDatabase().getUsers().get(i).getFullName() + "\n");
            }
        }
        return show.toString();
    }
    public String showArtist(String userName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(userName)){
                ArtistModel artist = (ArtistModel) Database.getDatabase().getUsers().get(i);
                show.append("Artist Name: " + artist.getFullName() + "\n");
                if(Database.getDatabase().getUsers().get(i).getClass().equals(Model.SingerModel.class)){
                    show.append("Albums Name:\n");
                    for (int r = 0; r < ((SingerModel) artist).getAlbums().size(); r++) {
                        show.append(((SingerModel) artist).getAlbums().get(r).getAlbumName()+"\n");
                    }
                }
                else{
                    PodcasterModel podcaster = (PodcasterModel) artist;
                    show.append("Podcasts Name:\n");
                    for (int j = 0; j < podcaster.getPodcasts().size(); j++) {
                        show.append(podcaster.getPodcasts().get(j).getAudioName()+"\n");
                    }
                }
                break;
            }
        }
        return show.toString();
    }
    public void followArtist(String userName){
        Database database = Database.getDatabase();
        for (User user : database.getUsers()) {
            if (user.getUsername().equals(userName) && user instanceof ArtistModel) {
                ArtistModel artist = (ArtistModel) user;
                model.getFallowingArtist().add(artist);
                artist.getFollowers().add(model);
                break;
            }
        }
    }
    public String showPlaylists(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getPlaylists().size(); i++) {
            show.append("Playlist name: "+model.getPlaylists().get(i).getPlaylistName()+"\n");
        }
        return show.toString();
    }
    public String selectPlaylist(String playlistName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getPlaylists().size(); i++){
            if(model.getPlaylists().get(i).getPlaylistName().equals(playlistName)){
                for (int j = 0; j < model.getPlaylists().get(i).getAudio().size(); j++) {
                    show.append("Audio name: "+model.getPlaylists().get(i).getAudio().get(j).getAudioName() + "\tArtist Name: " +model.getPlaylists().get(i).getAudio().get(j).getArtistName()+"\n");
                }
            }
        }
        return show.toString();
    }
    public String getSuggestions(int n){
        StringBuilder show = new StringBuilder();
        int count=0;

        for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
            if(count<n) {
                if (Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[0]) || Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[1]) || Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[2]) || Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[3])) {
                    show.append("Audio name: " + Database.getDatabase().getAudios().get(i).getAudioName() + "\tArtist name: " + Database.getDatabase().getAudios().get(i).getArtistName() + "\tGenre: " + Database.getDatabase().getAudios().get(i).getGenre() + "\n");
                    count++;
                }
            }
        }


        return show.toString();
    }
    public String showUserInfo(){
        return "Name: "+model.getFullName() + "\tUsername: " + model.getUsername() + "\tPhone number: " + model.getPhoneNumber() + "\nEmail: "+ model.getEmail() + "\tBorn: " + model.getBirthDate() ;
    }
    public String buyOrExtendSubscription(int day){
        if(model.getClass().equals(ListenerModel.class)){
          //  model = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());

            return check(day) + model.getClass();
        }
        else if(model.getClass().equals(PremiumModel.class)){
            ((PremiumModel) model).setRemainingSubscriptionDay(((PremiumModel)model).getRemainingSubscriptionDay()+day);
            return check(day);
        }
        return null;
    }
    private String check(int day){
        if(day==30) {
            if(model.getCredit()-PremiumSubscription.DAYS30.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS30.getSubPrize());
        }
        else if(day==60) {
            if(model.getCredit()-PremiumSubscription.DAYS60.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS60.getSubPrize());
        }
        else {
            if(model.getCredit()-PremiumSubscription.DAYS180.getSubPrize() < 0)
                return "Your credit is low!";
            PremiumModel per = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            per.setCredit(model.getCredit());
            per.setPlaylists(model.getPlaylists());
            per.setFavoriteGenres(model.getFavoriteGenres());
            model=per;
            per= (PremiumModel)model;
            ((PremiumModel) model).setRemainingSubscriptionDay(day);

            model.setCredit(model.getCredit() - PremiumSubscription.DAYS180.getSubPrize());
        }
        return "Done!";
    }
    public void increaseCredit(int value){
        model.setCredit(model.getCredit()+value);
    }
}
