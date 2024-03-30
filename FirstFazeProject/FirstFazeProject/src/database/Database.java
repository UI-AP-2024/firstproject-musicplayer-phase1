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
    ArrayList<UserAccount> allUsers = new ArrayList<>();
    ArrayList<Audio> allAudios = new ArrayList<>();
    ArrayList<Report> reports = new ArrayList<>();

}
