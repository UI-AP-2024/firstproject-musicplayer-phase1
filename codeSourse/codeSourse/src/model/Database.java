package model;

import model.AccountUser.AccountUser;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;
import java.util.ArrayList;

public class Database {
    private static Database instance;
    private ArrayList<AccountUser> users;
    private ArrayList<Audio> audiofiles;
    private ArrayList<Report> reports;

    private Database() {
        this.users = new ArrayList<>();
        this.audiofiles = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public static Database getInstance() {
        if (instance == null) {
            synchronized (Database.class) {
                if (instance == null) {
                    instance = new Database();
                }
            }
        }
        return instance;
    }

    public ArrayList<AccountUser> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<AccountUser> users) {
        this.users = users;
    }

    public ArrayList<Audio> getAudiofiles() {
        return audiofiles;
    }

    public void setAudiofiles(ArrayList<Audio> audiofiles) {
        this.audiofiles = audiofiles;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}