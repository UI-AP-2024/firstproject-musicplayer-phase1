package Controller;
import Model.*;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController extends User{
    private FreeModel model;
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
        else if(model.playlistCount < 3){
            model.getPlaylists().add(playlists);
            model.playlistCount++;
            return "Playlist made successfully";
        }

        return "Sorry! You can't creat more than 3 playlists with freeAccount.";
    }
    public String addMusicToPlaylist(String playlistName,String musicName){
        if(model.getClass().equals(PremiumSubscription.class)){
            searchAndAdd(playlistName,musicName);
            return "Music added successfully.";
        }
        else if(model.musicCount < 10){
            searchAndAdd(playlistName,musicName);
            model.musicCount++;
            return "Music added successfully.";
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
}
