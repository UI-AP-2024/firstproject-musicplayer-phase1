package model;
import model.Audio.Audio;
import model.UserAccount.UserAccount;

import java.util.ArrayList;

public class Database
{
    private ArrayList<UserAccount> userAccounts = new ArrayList<UserAccount>();
    private ArrayList<Audio> audio = new ArrayList<Audio>();
    private ArrayList<Report> reports = new ArrayList<Report>();
    private static Database database;
    private Database(ArrayList<UserAccount> userAccounts, ArrayList<Audio> audio, ArrayList<Report> reports)
    {
        this.userAccounts = userAccounts;
        this.audio = audio;
        this.reports = reports;
    }
    public static Database getDatabase(ArrayList<UserAccount> userAccounts, ArrayList<Audio> audio, ArrayList<Report> reports)
    {
        if(database == null)
        {
            database = new Database(userAccounts,audio,reports);
        }
        return database;
    }
    public ArrayList<UserAccount> getUserAccounts() {
        return userAccounts;
    }
    public void setUserAccounts(ArrayList<UserAccount> userAccounts) {
        this.userAccounts = userAccounts;
    }
    public ArrayList<Audio> getAudio() {
        return audio;
    }
    public void setAudio(ArrayList<Audio> audio) {
        this.audio = audio;
    }
    public ArrayList<Report> getReports() {
        return reports;
    }
    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
    public static Database getDatabase() {
        return database;
    }
    public static void setDatabase(Database database) {
        Database.database = database;
    }
}
