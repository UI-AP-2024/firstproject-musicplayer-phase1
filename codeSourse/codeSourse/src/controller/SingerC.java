package controller;

import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.Album;
import model.Audio.Audio;
import model.Audio.Music;
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
    public static boolean isValidUserEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9._%+-]+@(gmail|email|yahoo)\\.com$";
        return email.matches(emailRegex);
    }

    public static boolean isValidPassword(String password) {
        String passwordRegex = "^(?=.*[a-z])(?=.*[0-9])(?=.*[A-Z])[A-Za-z0-9.@_-]{8,16}$";
        return password.matches(passwordRegex);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        String phoneRegex = "^09[0-9]{9}$";
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

        public static void publishMusic(Singer singer, String title, Genre genre, int albumId, String audioLink, String cover, String lyrics) {
            Music audio = new Music(generateUniqueId(), title, singer.getFullName(), 0, 0, new Date(), genre, audioLink, cover, lyrics);
            Database.getInstance().getAudiofiles().add(audio);
            Album album = findAlbumById(singer, albumId);
            if (album != null) {
                album.addSong(title);
            } else {
                System.out.println("Album not found!");
            }
        }


        private static Album findAlbumById(Singer singer, int albumId) {
            List<Album> albums = singer.getAlbums();
            for (Album album : albums) {
                if (album.getUniqeId() == albumId) {
                    return album;
                }
            }
            return null;
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
            return;

        }
        String fullName = commands[4];
        String email = commands[5];
        if (!isValidUserEmail(email)) {
            System.out.println("Invalid email.");
            return;

        }
        String phoneNumber = commands[6];
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phoneNumber.");
            return;

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
        Database.getInstance().getUsers().add(newUser);

        singer.put(userName, newUser);

        System.out.println("Signed up successfully!");
        Panels.showFirstMeneu();

    }
}

