package controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import model.AccountUser.AccountUser;
import model.AccountUser.Artist.Artist;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import model.Report;


public class AdminC {
    private UserRegistration userRegistration;

    public AdminC(UserRegistration userRegistration) {
        this.userRegistration = userRegistration;
    }

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
        List<Listener> users = new ArrayList<>(userRegistration.getUsers().values());
        for (Listener user : users) {
            if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}




