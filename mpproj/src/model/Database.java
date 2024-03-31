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
}
