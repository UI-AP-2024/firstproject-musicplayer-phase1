package model.DataBase;

import model.Audio.Audio;
import model.Report;
import model.UserAccount.UserAccount;

import java.util.ArrayList;

public class DataBase {

    private ArrayList<UserAccount> users;
    private ArrayList<Audio> audios;
    private ArrayList<Report> reports;
    private static DataBase dataBase;

    public static DataBase getDataBase() {
        if (dataBase == null)
            dataBase = new DataBase();
        return dataBase;
    }

    public ArrayList<UserAccount> getUsers() {
        return users;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public ArrayList<Report> getReports() {
        return reports;
    }

    public void setUsers(ArrayList<UserAccount> users) {
        this.users = users;
    }

    public void setAudios(ArrayList<Audio> audio) {
        this.audios = audio;
    }

    public void setReports(ArrayList<Report> reports) {
        this.reports = reports;
    }
}
