package Controller;
import Model.*;

import java.lang.reflect.Array;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController extends User{
    private ListenerModel model;
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
        int count=0;
        while(genre!=null && count < 4){
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
    public String addMusicToPlaylist(String playlistName,String musicName){
        if(model.getClass().equals(PremiumSubscription.class)){
            searchAndAdd(playlistName,musicName);
            return "Music added to playlist successfully.";
        }
        else if(model.musicCount < FreeModel.maxAudio){
            searchAndAdd(playlistName,musicName);
            model.musicCount++;
            return "Music added to playlist successfully.";
        }
        return "Sorry! You can't add more than 10 Songs to the playlist with freeAccount.";
    }
    private void searchAndAdd(String playlistName,String musicName){
        for (int i = 0; i < model.getPlaylists().size(); i++) {
            if(model.getPlaylists().get(i).getPlaylistName().equals(playlistName)){
                for (int j = 0; j < Database.getDatabase().getAudios().size(); j++) {
                    if(Database.getDatabase().getAudios().get(j).getAudioName().equals(musicName)){
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
    public String searchAudioByName(String audioName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getAudioName().equals(audioName)){
                AudioModel audio = Database.getDatabase().getAudios().get(i);
                show.append("Audio name: " + audio.getAudioName() + " Artist: " + audio.getArtistName() + " Genre: " + audio.getGenre() + "\n");
            }
        }
        return show.toString();
    }
    public String searchAudioByArtist(String artistName){
        StringBuilder show = new StringBuilder();
        for (int i = 0; i <Database.getDatabase().getAudios().size(); i++) {
            if(Database.getDatabase().getAudios().get(i).getArtistName().equals(artistName)){
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
        for (int i = 0; i < Database.getDatabase().getArtists().size(); i++) {
            if(Database.getDatabase().getArtists().get(i).getUsername().equals(userName)){
                ArtistModel artist = Database.getDatabase().getArtists().get(i);
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
        for (int i = 0; i < Database.getDatabase().getArtists().size(); i++) {
            if(Database.getDatabase().getArtists().get(i).getUsername().equals(userName)){
                show.append("Artist Name: " + Database.getDatabase().getArtists().get(i).getFullName() + "\n");
                if(Database.getDatabase().getArtists().get(i).getClass().equals(SingerModel.class)){
                    for (int r = 0; r < Database.getDatabase().getSinger().get(i).getAlbums().size(); r++) {
                        show.append("Albums Name: "+ Database.getDatabase().getSinger().get(i).getAlbums().get(r)+"\n");
                    }
                }
                else{
                    for (int j = 0; j < Database.getDatabase().getPodcaster().get(i).getPodcasts().size(); j++) {
                        show.append("Podcasts Name: " + Database.getDatabase().getPodcaster().get(i).getPodcasts().get(j)+"\n");
                    }
                }
                break;
            }
        }
        return show.toString();
    }
    public void fallowArtist(String userName){
        for (int i = 0; i < Database.getDatabase().getArtists().size(); i++) {
            if(Database.getDatabase().getArtists().get(i).getUsername().equals(userName)){
                model.getFallowingArtist().add(Database.getDatabase().getArtists().get(i));
                Database.getDatabase().getArtists().get(i).getFollowers().add(model);
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
            if(model.getCredit() < day)
                return "Your credit is low!";
            else{
                model = new PremiumModel(model.getUsername(),model.getPassword() ,model.getFullName(),model.getEmail(), model.getPhoneNumber(), model.getBirthDate());
                ((PremiumModel) model).setRemainingSubscriptionDay(day);
                return "Done!";
            }
        }
        else{
            if(model.getCredit() < day)
                return "Your credit is low!";
            else {
                ((PremiumModel) model).setRemainingSubscriptionDay(((PremiumModel)model).getRemainingSubscriptionDay()+day);
                return "Done!";
            }
        }
    }
}
