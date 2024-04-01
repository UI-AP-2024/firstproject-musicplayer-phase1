package controller;

import model.AccountUser.Listener.Listener;
import model.Database;
import model.Genre;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class UserRegistration {
    private Map<String, Listener> users = new HashMap<>();
    private Database database = new Database();

    //*********************************************

    public void registerUser() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Please enter userName: ");
        String userName = scanner.nextLine();

        if (users.containsKey(userName)) {
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
        Listener newUser = new Listener(userName, password, fullName, email, phoneNumber, birthDate, 0.0, null);
        newUser.setFavoriteGenres(selectFavoriteGenres());
        newUser.setAccountBalance(50.0);

        users.put(userName, newUser);
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

    private List<Genre> selectFavoriteGenres() {
        List<Genre> favoriteGenres = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please select your favorite genres (maximum 4):");
        Genre[] genres = Genre.values();
        for (int i = 0; i < genres.length; i++) {
            System.out.println((i + 1) + ". " + genres[i]);
        }
        System.out.println("Enter the numbers corresponding to your favorite genres (separated by commas):");
        String input = scanner.nextLine();
        String[] numbers = input.split(",");
        for (String number : numbers) {
            int index = Integer.parseInt(number.trim()) - 1;
            if (index >= 0 && index < genres.length && favoriteGenres.size() < 4) {
                favoriteGenres.add(genres[index]);
            }
        }
        return favoriteGenres;
    }
    //*********************************************

        public void login(String username, String password) {
            if (checkCredentials(username, password)) {
                showUserPanel(username);
            } else {
                System.out.println("Invalid username or password");
            }
        }

    private void showUserPanel(String username) {
    }

    private boolean checkCredentials(String username, String password) {
        for (Map.Entry<String, Listener> entry : users.entrySet()) {
            Listener user = entry.getValue();
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    }