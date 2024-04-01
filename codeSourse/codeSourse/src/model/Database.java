package model;

import model.AccountUser.AccountUser;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;

import java.util.*;

public class Database {
    private static Database instance;
    private List<AccountUser> users;
    private List<Audio> audiofiles;
    private List<Report> reports;

    //*********************************************
    public Database() {
        users = new ArrayList<>();
        audiofiles = new ArrayList<>();
        reports = new ArrayList<>();

    }
    //*********************************************

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    //*********************************************

    public void addUser(Listener user){
        users.add(user);
    }

}