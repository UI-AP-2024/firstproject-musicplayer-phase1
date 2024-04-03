package controller;

import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.Album;
import model.Audio.Audio;
import model.Database;
import model.Genre;
import view.Panels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class SingerC {

    private static Map<String, Singer> singer = new HashMap<>();

    //*********************************************

    public Map<String, Singer> getUsers() {
        return singer;
    }


    //*********************************************
    private static boolean isValidUserEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@(\\S+)$";
        return email.matches(emailRegex);
    }

    private static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,20}$";
        return password.matches(passwordRegex);
    }

    private static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
        return phoneNumber.matches(phoneRegex);
    }

    //*********************************************

    public static int createAlbum(Singer singer, String albumName) {
        int uniqueId = generateUniqueId();
        Album album = new Album(uniqueId, albumName, singer.getFullName());
        singer.getAlbums().add(album);
        return uniqueId;
    }

    private static int generateUniqueId() {

        return (int) (Math.random() * 1000) + 1;
    }

    public static void publishAudio(Singer singer, String audioFileName, String albumName, String tite, String audioLink, String cover) {
        Audio audio = new Audio(0, audioFileName, singer.getFullName(), 0, 0, new Date(), null, audioLink, cover);
        Database.getDatabase().getAudiofiles().add(audio);
        Album album = findAlbumByName(singer, albumName);
        if (album != null) {
            album.addSong(audioFileName);
        } else {
            System.out.println("Album not found!");
        }
    }


    private static Album findAlbumByName(Singer singer, String albumName) {
        List<Album> albumss = singer.getAlbums();
        for (Album album : albumss) {
            if (album.equals(albumName)) {
                return album;
            }
        }
        return null;
    }

    public static void signUpS(String[] commands) {

        String userName = commands[2];
        if (singer.containsKey(userName)) {
            System.out.println("Error: userName already exists.");
            return;
        }
        String password = commands[3];
        if (!isValidPassword(password)) {
            System.out.println("Invalid password.");
        }
        String fullName = commands[4];
        String email = commands[5];
        if (!isValidUserEmail(email)) {
            System.out.println("Invalid email.");
        }
        String phoneNumber = commands[6];
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phoneNumber.");
        }
        String dateOfBirth = commands[7];
        Date birthDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = dateFormat.parse(dateOfBirth);
        } catch (ParseException e) {
            System.out.println("Invalid birthDate format.");
            return;
        }
        String biography = commands[8];
        Singer newUser = new Singer(userName, password, fullName, email, phoneNumber, birthDate, 0.0, biography);
        Database.getDatabase().getUsers().add(newUser);

        singer.put(userName, newUser);

        System.out.println("Signed up successfully!");
        Panels.showFirstMeneu();

    }
}

