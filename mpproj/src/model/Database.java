package model;

import java.util.ArrayList;

public class Database {
    private static Database database;

    public static Database getDataBase() {
        if (database == null)
            database = new Database();
        return database;
    }
    private ArrayList<UserAccount> userAccounts;
    private ArrayList<Audio> audio;
    private ArrayList<Report> reports;

    private Database() {
        this.userAccounts = new ArrayList<>();
        this.audio = new ArrayList<>();
        this.reports = new ArrayList<>();
    }

    public static void setDatabase(Database database) {
        Database.database = database;
    }

    public void setUserAccounts(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }

    public void setAudio(ArrayList<Audio> audio) {
        this.audio = audio;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }

    public ArrayList<UserAccount> getUserAccounts() {
        return userAccounts;
    }

    public ArrayList<Audio> getAudio() {
        return audio;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }
}
