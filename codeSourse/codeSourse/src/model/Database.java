package model;

import model.AccountUser.AccountUser;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;

import java.util.*;

public class Database {
    private static Database instance;
    private List<AccountUser> users;
    private static List<Audio> audiofiles;
    private static List<Report> reports;

    //*********************************************
    public Database() {
        users = new ArrayList<>();
        audiofiles = new ArrayList<Audio>();
        reports = new ArrayList<>();

    }
    //*********************************************

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public static void addreport(Report userReport) {
        reports.add(userReport);
    }


    //*********************************************

    public void addUser(Listener user){
        users.add(user);
    }

    public void addrAudio(Audio audio) {
        audiofiles.add(audio);
    }



}