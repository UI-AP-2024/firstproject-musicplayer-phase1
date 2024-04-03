package controller;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.TypeOfArtist.Podcaster;
import model.AccountUser.Artist.TypeOfArtist.Singer;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Database;
import model.Genre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class PodcasterC {

    private Map<String, Podcaster> podcaster = new HashMap<>();

    //*********************************************

    public Map<String, Podcaster> getUsers() {
        return podcaster;
    }

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter userName: ");
        String userName = scanner.nextLine();

        if (podcaster.containsKey(userName)) {
            System.out.println("Error: userName already exists.");
            return;
        }

        //*********************************************

        System.out.print("Please enter fullName: ");
        String fullName = scanner.nextLine();

        //*********************************************

        System.out.print("Please enter birthDate (yyyy-MM-dd): ");
        String birthDateStr = scanner.nextLine();
        Date birthDate;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            birthDate = dateFormat.parse(birthDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid birthDate format.");
            return;
        }

        //*********************************************

        System.out.print("Please enter email: ");
        String email = scanner.nextLine();
        if (!isValidUserEmail(email)) {
            System.out.println("Invalid email.");
            return;
        }

        //*********************************************

        System.out.print("Please enter password: ");
        String password = scanner.nextLine();
        if (!isValidPassword(password)) {
            System.out.println("Invalid password.");
            return;
        }

        //*********************************************

        System.out.print("Please enter phoneNumber: ");
        String phoneNumber = scanner.nextLine();
        if (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phoneNumber.");
            return;
        }

        //*********************************************
        System.out.print("Please enter biography: ");
        String biography = scanner.nextLine();

        //*********************************************

        Podcaster newUser = new Podcaster(userName, password, fullName, email, phoneNumber, birthDate, 0.0, biography);
        Database.getDatabase().getUsers().add(newUser);

        podcaster.put(userName, newUser);
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


    //************************************************************************




    public void publishPodcast(Podcaster podcaster, int uniqueId, String title, int playCount, int likes, Date releaseDate, Genre genre, String audioLink, String cover) {
        Audio podcast = new Audio(uniqueId, title, podcaster.getFullName(), playCount, likes, releaseDate, genre, audioLink, cover);

        podcaster.getPodcasts().add(title);

        System.out.println("Podcast '" + title + "' published successfully by " + podcaster.getFullName() + ".");
    }
}
