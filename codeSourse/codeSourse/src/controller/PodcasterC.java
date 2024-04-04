package controller;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Audio.Podcast;
import model.Database;
import model.Genre;
import view.Panels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PodcasterC {

    private static Map<String, Podcaster> podcaster = new HashMap<>();

    //*********************************************

    public Map<String, Podcaster> getUsers() {
        return podcaster;
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

    //************************************************************************

        public static void publishPodcast(Podcaster podcaster, int uniqueId, String title, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover,String caption) {
            Podcast podcast = new Podcast(uniqueId, title, podcaster.getFullName(), playCount, likes, releaseDate, genre, audioLink, cover,caption);
            Database.getInstance().getAudiofiles().add(podcast);

            System.out.println("Podcast '" + title + "' published successfully by " + podcaster.getFullName() + ".");
        }

    public static void signUpLP(String[] commands) {

        String userName = commands[2];
        if (podcaster.containsKey(userName)) {
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
        Podcaster newUser = new Podcaster(userName, password, fullName, email, phoneNumber, birthDate, 0.0, biography);
        Database.getInstance().getUsers().add(newUser);

        podcaster.put(userName, newUser);

        System.out.println("Signed up successfully!");
        Panels.showFirstMeneu();

    }
}
