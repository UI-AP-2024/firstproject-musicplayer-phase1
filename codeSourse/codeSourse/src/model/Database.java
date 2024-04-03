package model;

import model.AccountUser.AccountUser;
import model.AccountUser.Listener.Listener;
import model.Audio.Audio;

import java.util.*;

public class Database {
    private static Database database;
    private  static ArrayList<AccountUser> users;
    private static ArrayList<Audio> audiofiles;
    private static ArrayList<Report> reports;

    //*********************************************
    public Database() {
        this.users = new ArrayList<>();
        this.audiofiles = new ArrayList<>();
        this.reports = new ArrayList<>();

    }
    //*********************************************

    public static Database getDatabase() {
        if (database == null) {
            database = new Database();
        }
        return database;
    }

    //*********************************************

    public ArrayList<AccountUser> getUsers(){
        return users;
    }
    public void setUsers(ArrayList<AccountUser> users){
        this.users = users;
    }
    public ArrayList<Audio> getAudiofiles() {
        return audiofiles;
    }
    public void setAudiofiles(ArrayList<Audio> audiofiles){
        this.audiofiles = audiofiles;
    }
    public ArrayList<Report> getReports(){
        return reports;
    }
    public void setReports(ArrayList<Report> reports){
        this.reports = reports;
    }




}