package Controller;
import Model.*;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController extends User{
    private ListenerModel model;
    private int count=0;
    public ListenerController(String username, String password, String fullName, String email, String phoneNumber, Date birthDate) {
        super(username, password, fullName, email, phoneNumber, birthDate);
    }
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

        model = new FreeModel(username, password, fullName, email, phoneNumber, birthDate);
        model.setCredit(50);
        return "Done!";
    }
    public String logIn(String username, String password){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(username) && Database.getDatabase().getUsers().get(i).getPassword().equals(password)){
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
        Playlists playlists = new Playlists(playlistName,userName);

        if(model.getClass().equals(PremiumSubscription.class)) {
            model.getPlaylists().add(playlists);
            return "Playlist made successfully";
        }
        else if(model.playlistCount < FreeModel.maxPlaylist){
            model.getPlaylists().add(playlists);
            model.playlistCount++;
            return "Playlist made successfully";
        }

        return "Sorry! You can't creat more than 3 playlists with freeAccount.";
    }
    public String addMusicToPlaylist(String playlistName,int ID){
        if(model.getClass().equals(PremiumSubscription.class)){
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
    public String playAudio(String audioName){
        String show = "";
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getAudioName().equals(audioName)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show = "Audio: " + audioName + " Artis: " + audio.getArtistName() + " Likes: " + audio.getLikeCount() +"\nGenre: " + audio.getGenre() + " Release Date: " + audio.getReleaseDate() + " ID: " + audio.getIDCount();
                audio.setPlayedCount(audio.getPlayedCount() + 1);
                model.Count++;
                model.getPlayCount().put(audio,model.Count);
            }
        }
        return show;
    }
    public void likeAudio(String audioName){
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getAudioName().equals(audioName)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                audio.setLikeCount(audio.getLikeCount() + 1);
                break;
            }
        }
    }
    public String searchAudio(String name){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getAudioName().equals(name) || Database.getDatabase().getAudios().get(i).getArtistName().equals(name)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio name: " + audio.getAudioName() + " Artist: " + audio.getArtistName() + " Genre: " + audio.getGenre() + "\n");
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
                show.append("Audio: "+audio + " ID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnGenres(Genre genre){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getGenre().equals(genre)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio + " ID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedOnDate(Date releaseDate){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getDate().getYear()==releaseDate.getYear()){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio + " Artist: "+audio.getArtistName()+" ID: "+audio.getIDCount()+"\n");
            }
        }
        return show.toString();
    }
    public String filterAudioFilesBasedAmongDate(Date releaseDate1,Date releaseDate2){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(releaseDate1.getYear() <= Database.getDatabase().getAudios().get(i).getDate().getYear() && Database.getDatabase().getAudios().get(i).getDate().getYear()<=releaseDate2.getYear()){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio: "+audio + " Artist: "+audio.getArtistName()+" ID: "+audio.getIDCount()+"\n");
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
            show.append("Artist: "+ model.getFallowingArtist().get(i)+"\n");
        }
        return show.toString();
    }
    public String showArtistList(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getClass().equals(ArtistModel.class)){
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
                if(Database.getDatabase().getUsers().get(i).getClass().equals(SingerModel.class)){
                    SingerModel singer = (SingerModel) artist;
                    for (int r = 0; r < singer.getAlbums().size(); r++) {
                        show.append("Albums Name: "+ singer.getAlbums().get(r)+"\n");
                    }
                }
                else{
                    PodcasterModel podcaster = (PodcasterModel) artist;
                    for (int j = 0; j < podcaster.getPodcasts().size(); j++) {
                        show.append("Podcasts Name: " + podcaster.getPodcasts().get(j)+"\n");
                    }
                }
                break;
            }
        }
        return show.toString();
    }
    public void followArtist(String userName){
        for (int i = 0; i < Database.getDatabase().getUsers().size(); i++) {
            if(Database.getDatabase().getUsers().get(i).getUsername().equals(userName)){
                ArtistModel artist = (ArtistModel) Database.getDatabase().getUsers().get(i);
                model.getFallowingArtist().add(artist);
                artist.getFollowers().add(model);
                break;
            }
        }
    }
    public String showPlaylists(){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getPlaylists().size(); i++) {
            show.append("Playlist name: "+model.getPlaylists().get(i).getPlaylistName());
        }
        return show.toString();
    }
    public String selectPlaylist(String playlistName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i < model.getPlaylists().size(); i++){
            if(model.getPlaylists().get(i).getPlaylistName().equals(playlistName)){
                for (int j = 0; j < model.getPlaylists().get(i).getAudio().size(); j++) {
                    show.append("Audio name; "+model.getPlaylists().get(i).getAudio().get(j).getAudioName() + " Artist Name: " +model.getPlaylists().get(i).getAudio().get(j).getArtistName()+"\n");
                }
            }
        }
        return show.toString();
    }
    public String getSuggestions(int n){
        StringBuilder show = new StringBuilder();
        int count=0;
        while (count<n){
            for (int i = 0; i < Database.getDatabase().getAudios().size(); i++) {
                if(Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[0]) || Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[1]) ||  Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[2]) || Database.getDatabase().getAudios().get(i).getGenre().equals(model.getFavoriteGenres()[3])){
                    show.append("Audio name: "+Database.getDatabase().getAudios().get(i).getAudioName() + " Artist name: "+Database.getDatabase().getAudios().get(i).getArtistName() + " Genre: " + Database.getDatabase().getAudios().get(i).getGenre()+"\n");
                    count++;
                }
            }
        }

        return show.toString();
    }
    public String showUserInfo(){
        return "Name: "+model.getFullName() + " Username: " + model.getUsername() + " Phone number: " + model.getPhoneNumber() + "\nEmail: "+ model.getEmail() + " Born: " + model.getBirthDate() ;
    }
    public String buyOrExtendSubscription(int day){
        if(model.getClass().equals(FreeModel.class)){
            model = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
            ((PremiumModel) model).setRemainingSubscriptionDay(day);
            return check(day);
        }
        else{
            ((PremiumModel) model).setRemainingSubscriptionDay(((PremiumModel)model).getRemainingSubscriptionDay()+day);
            return check(day);
        }
    }
    private String check(int day){
        if(day==30) {
            if(model.getCredit()-PremiumSubscription.DAYS30.getSubPrize() < 0)
                return "Your credit is low!";
            model.setCredit(model.getCredit() - PremiumSubscription.DAYS30.getSubPrize());
        }
        else if(day==60) {
            if(model.getCredit()-PremiumSubscription.DAYS60.getSubPrize() < 0)
                return "Your credit is low!";
            model.setCredit(model.getCredit() - PremiumSubscription.DAYS60.getSubPrize());
        }
        else {
            if(model.getCredit()-PremiumSubscription.DAYS180.getSubPrize() < 0)
                return "Your credit is low!";
            model.setCredit(model.getCredit() - PremiumSubscription.DAYS180.getSubPrize());
        }
        return "Done!";
    }
    public void increaseCredit(int value){
        model.setCredit(model.getCredit()+value);
    }
}
