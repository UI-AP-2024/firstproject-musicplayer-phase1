package controller;

import model.*;

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
            countPlaylist++;
            if (countPlaylist <= ((Free) getUserAccount()).getLimitNumberPlaylist()) {
                Playlist playlist = new Playlist(countPlaylist, name, getUserAccount().getUserName(), null);
                ((Free) getUserAccount()).getPlaylists().add(playlist);
                return "The playlist was created successfully";
            } else {
                countPlaylist--;
                return "You are allowed to create 3 playlists";
            }

        } else if (getUserAccount() instanceof Premium) {
            countPlaylist++;
            Playlist playlist = new Playlist(countPlaylist, name, getUserAccount().getUserName(), null);
            ((Premium) getUserAccount()).getPlaylists().add(playlist);
            return "The playlist was created successfully";
        } else
            return "ERORR create playlist";
    }

    private static int countAddAudioPlaylist = 0;

    public String addAudioToPlaylist(String nameOfPlaylist, long id) {
        if (getUserAccount() instanceof Free) {
            for (Playlist playlist : ((Free) getUserAccount()).getPlaylists()) {
                if (playlist.getNameOfPlaylist().equals(nameOfPlaylist) && countAddAudioPlaylist < ((Free) getUserAccount()).getLimitAddSong()) {
                    for (Audio audio : playlist.getListAudio()) {
                        if (audio.getId() == id) {
                            countPlaylist++;
                            playlist.getListAudio().add(audio);
                            ((Free) getUserAccount()).getPlaylists().remove(playlist);
                            ((Free) getUserAccount()).getPlaylists().add(playlist);
                            return "The audio file(" + id + ") was successfully entered into the playlist(" + nameOfPlaylist + ")";
                        }
                    }
                }
            }
            if (countPlaylist >= ((Free) getUserAccount()).getLimitAddSong())
                return "You are allowed to add only 10 audio files";
            else
                return "The name of the playlist or the ID of the audio file is wrong";
        } else if (getUserAccount() instanceof Premium) {
            for (Playlist playlist : ((Premium) getUserAccount()).getPlaylists()) {
                if (playlist.getNameOfPlaylist().equals(nameOfPlaylist)) {
                    for (Audio audio : playlist.getListAudio()) {
                        if (audio.getId() == id) {
                            countPlaylist++;
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


}
