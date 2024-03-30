package database;

import model.Audio;
import model.Report;
import model.UserAccount;

import java.util.ArrayList;

public class Database {
    private static Database data;

    private Database() {}

    public static Database getData() {
        return data;
    }
    private ArrayList<UserAccount> allUsers = new ArrayList<>();
    private ArrayList<Audio> allAudios = new ArrayList<>();
    private ArrayList<Report> reports = new ArrayList<>();

}
