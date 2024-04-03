package controller;

import com.sun.jdi.connect.Connector;
import model.*;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ListenerController {
    private static ListenerController listenerController;

    private ListenerController() {
    }

    public static ListenerController getListenerController() {
        if (listenerController == null)
            listenerController = new ListenerController();
        return listenerController;
    }

    private UserAccount userAccount;

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
    }


    public String registration(String userName, String password, String name, String email, String number, Date birth) {
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()) {
            if (tmp.getUserName().equals(userName))
                return "Username is duplicated. Please try again";
        }
        String regex = "^([\\w-\\.]+[@]{1}[\\w]+[\\.]{1}[\\w]{2,4})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches() == false)
            return "The email entered is not valid";
        regex = "^([0]{1}[9]{1}[0-9]{9})$";
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(number);
        if (matcher.matches() == false)
            return "The phone number entered is not valid";
        Free free = new Free(userName, password, name, email, number, birth, 50, null, null, null);
        Database.getDataBase().getUserAccounts().add(free);
        return "Account created successfully";
    }

    public String login(String userName, String password) {
        for (UserAccount tmp : Database.getDataBase().getUserAccounts()) {
            if (tmp.getUserName().equals(userName) && tmp.getPassword().equals(password)) {
                setUserAccount(tmp);
                return "Login successfully";
            }
        }
        return "The username does not exist or the password is incorrect";
    }

    private static int countPlaylist = 0;

    public String createPlaylist(String name) {
        if (getUserAccount() instanceof Free) {
            ((Free) getUserAccount()).setCountNumberPlaylist(((Free) getUserAccount()).getCountNumberPlaylist()+1);
            if (((Free) getUserAccount()).getCountNumberPlaylist() <= ((Free) getUserAccount()).getLimitNumberPlaylist()) {
                Playlist playlist = new Playlist(((Free) getUserAccount()).getCountNumberPlaylist(), name, getUserAccount().getUserName(), null);
                ((Free) getUserAccount()).getPlaylists().add(playlist);
                return "The playlist was created successfully";
            } else {
                ((Free) getUserAccount()).setCountNumberPlaylist(((Free) getUserAccount()).getCountNumberPlaylist()-1);
                return "You are allowed to create 3 playlists";
            }

        } else if (getUserAccount() instanceof Premium) {
            ((Premium) getUserAccount()).setCountNumberPlaylistP(((Premium) getUserAccount()).getCountNumberPlaylistP()+1);
            Playlist playlist = new Playlist(((Premium) getUserAccount()).getCountNumberPlaylistP(), name, getUserAccount().getUserName(), null);
            ((Premium) getUserAccount()).getPlaylists().add(playlist);
            return "The playlist was created successfully";
        } else
            return "ERORR create playlist";
    }

    public String addAudioToPlaylist(String nameOfPlaylist, long id) {
        if (getUserAccount() instanceof Free) {
            for (Playlist playlist : ((Free) getUserAccount()).getPlaylists()) {
                if (playlist.getNameOfPlaylist().equals(nameOfPlaylist) && ((Free) getUserAccount()).getCountAddSong() < ((Free) getUserAccount()).getLimitAddSong()) {
                    for (Audio audio : playlist.getListAudio()) {
                        if (audio.getId() == id) {
                            ((Free) getUserAccount()).setCountAddSong(((Free) getUserAccount()).getCountAddSong()+1);
                            playlist.getListAudio().add(audio);
                            ((Free) getUserAccount()).getPlaylists().remove(playlist);
                            ((Free) getUserAccount()).getPlaylists().add(playlist);
                            return "The audio file(" + id + ") was successfully entered into the playlist(" + nameOfPlaylist + ")";
                        }
                    }
                }
            }
            if (((Free) getUserAccount()).getCountAddSong() >= ((Free) getUserAccount()).getLimitAddSong())
                return "You are allowed to add only 10 audio files";
            else
                return "The name of the playlist or the ID of the audio file is wrong";
        } else if (getUserAccount() instanceof Premium) {
            for (Playlist playlist : ((Premium) getUserAccount()).getPlaylists()) {
                if (playlist.getNameOfPlaylist().equals(nameOfPlaylist)) {
                    for (Audio audio : playlist.getListAudio()) {
                        if (audio.getId() == id) {
                            playlist.getListAudio().add(audio);
                            ((Premium) getUserAccount()).getPlaylists().remove(playlist);
                            ((Premium) getUserAccount()).getPlaylists().add(playlist);
                            return "The audio file(" + id + ") was successfully entered into the playlist(" + nameOfPlaylist + ")";
                        }
                    }
                }
            }
            return "The name of the playlist or the ID of the audio file is wrong";
        }
        return "ERORR add audio to playlist";
    }

    public String playAudioFile(long id) {
        for (Audio audio : Database.getDataBase().getAudio()) {
            if (audio.getId() == id) {
                audio.setNumberOfPlays(audio.getNumberOfPlays() + 1);
                if (getUserAccount() instanceof Listener)
                    ((Listener) getUserAccount()).getNumberPlays().replace(audio, ((Listener) getUserAccount()).getNumberPlays().get(audio) + 1);
                return "The audio file(" + audio.getName() + ") is playing";
            }
        }
        if (getUserAccount() instanceof Listener == false)
            return "You are not a listening user";
        else
            return "The desired audio file was not found";
    }

    public String likeAudioFile(long id){
        for (Audio audio : Database.getDataBase().getAudio()){
            if (audio.getId() == id){
                audio.setNumberOfLikes(audio.getNumberOfLikes()+1);
                return "The audio file was liked";
            }
        }
        return "The desired audio file was not found";
    }

    public String searchAudioFile(String name){
        String result = "";
        for ( Audio audio : Database.getDataBase().getAudio()){
            if ( audio.getName().equals(name) || audio.getNameArtist().equals(name)){
                result += "\n"+audio;
            }
        }
        if (result.equals(""))
            result = "Not found";
        return result;
    }

    public String sortAudioFile(String type){
        if (type.equals("L")){
            for (int i = 0; i < Database.getDataBase().getAudio().size()-1 ; i++) {
                for (int j = 0; j < Database.getDataBase().getAudio().size()-i-1 ; j++) {
                    if ( Database.getDataBase().getAudio().get(j).getNumberOfLikes() < Database.getDataBase().getAudio().get(j+1).getNumberOfLikes() ){
                        Collections.swap(Database.getDataBase().getAudio(),j,j+1);
                    }
                }
            }
            String result = "";
            for ( Audio audio : Database.getDataBase().getAudio())
                result += "\n"+audio;
            return result;
        }
        else if (type.equals("P")){
            for (int i = 0; i < Database.getDataBase().getAudio().size()-1 ; i++) {
                for (int j = 0; j < Database.getDataBase().getAudio().size()-i-1 ; j++) {
                    if ( Database.getDataBase().getAudio().get(j).getNumberOfPlays() < Database.getDataBase().getAudio().get(j+1).getNumberOfPlays() ){
                        Collections.swap(Database.getDataBase().getAudio(),j,j+1);
                    }
                }
            }
            String result = "";
            for ( Audio audio : Database.getDataBase().getAudio())
                result += "\n"+audio;
            return result;
        }
        return "The requested sort is not valid";
    }

    public String filterAudioFile(String type,String detail){
        if (type.equals("A")){
            String result = "";
            for ( Audio audio : Database.getDataBase().getAudio() ){
                if ( detail.equals(audio.getNameArtist()) ){
                    result += "\n"+audio;
                }
            }
            return result;
        }
        else if ( type.equals("G")){
            String result = "";
            for ( Audio audio : Database.getDataBase().getAudio() ){
                if ( detail.equals( String.valueOf(audio.getGener()) ){
                    result += "\n"+audio;
                }
            }
            return result;
        }
        else if ( type.equals("D")){
            String result = "";
            for ( Audio audio : Database.getDataBase().getAudio() ){
//                if ( detail.equals( String.valueOf(audio.getGener()) ){
//                    result += "\n"+audio;
//                }
            }
            return result;
        }
        return "The desired filter type or detail is not valid";
    }
    
}